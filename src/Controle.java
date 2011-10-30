import java.text.DecimalFormat;

import javax.swing.JFrame;

import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.XYPlot;

import Testes.*;
public class Controle implements Runnable{
	private boolean singleShot;
	private boolean stop;
	private boolean antAliasing;
	
	private boolean changed;
	
	private Plotter plotter;
	private Comunicacao comunicacao;
	private ProtocoloComunicacao protCom;
	private Trigger trigger;
	private Canal ch1;
	private Canal ch2;
	private Cursor cursor1;
	private Cursor cursor2;
	private FrameProjeto frameProjeto;
	
	private boolean statusPlotar;
	
	//Objetos teste.
	private GeradorDeFuncoes g1;
	private microControlador uc;
	
	public Controle(FrameProjeto frame){
		frameProjeto = frame;
		comunicacao = new Comunicacao();
		protCom = new ProtocoloComunicacao();
		ch1 = new Canal(1);
		ch2 = new Canal(2);
		trigger = new Trigger(false,ch1);
		cursor1 = new Cursor(1);
		cursor2 = new Cursor(2);
		plotter = new Plotter(this);
		
		singleShot = false;
		stop = false;
		statusPlotar = false;
		antAliasing = false;
		
		//Objetos teste.
		g1 = new GeradorDeFuncoes();
		g1.setAmplitude(0.01);
		g1.setFrequencia(1);
		g1.setEstado(GeradorDeFuncoes.SENOIDE);
		uc = new microControlador(g1);
		
	}
	
	public void startAll(){
		
		uc.start();
		Thread t  = new Thread(comunicacao);
		t.start();
		Thread t2  = new Thread(this);
		t2.start();
		Thread t3 = new Thread(plotter);
		t3.start();
	}
	
	@Override
	public void run() {
		while(true){
			//Aplica o protocolo de comunicacao e verifica se é dado ou outra coisa.
			//Se for dado : (o teste envolverá somente o canal1):
			synchronized(Monitor.C_P){
				while(!Monitor.C_P.livre){
					try {
						Monitor.C_P.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				synchronized(Monitor.M_C){
					while(Monitor.M_C.livre){
						try {
							Monitor.M_C.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if(!statusPlotar){
						if(uc.getStatus()){
			        		ch1.setDataComunicacao(uc.read());
							uc.setStatus(false);
							statusPlotar = true;
						}
						atualizaLabelCursores();
					}
					Monitor.M_C.livre = true;
					Monitor.M_C.notifyAll();
				}
				Monitor.C_P.livre = false;
				Monitor.C_P.notifyAll();
			}
		}
	}
	public void setStatusPlotar(boolean sP){
		statusPlotar = sP;
	}
	public boolean getStatusPlotar(){
		return statusPlotar;
	}
	public void conectarUSB(){
		
	}
	
	public void warnEmb(){
		changed = true;
	}
	
	
	public void selectTrigger(boolean t){
		trigger.select(t);
		if(t){
			plotter.addRangeMarker(trigger.getValueMarker());
		}
		else{
			plotter.clearRangeMarker();
		}
		plotter.configRangeMarker();
		frameProjeto.getChartPanel().repaint();
	}
	
	public void atualizaPosTrigger(double posicao){
		trigger.setPosicao(posicao);
		plotter.clearRangeMarker();
		plotter.addRangeMarker(trigger.getValueMarker());
		plotter.configRangeMarker();
		frameProjeto.getChartPanel().repaint();
	}
	public void atualizaPosTrigger(int sentido){
		double temp = trigger.getPosicao()+sentido*(Plotter.rangePlotter/250.0);
		if(Math.abs(temp)<Plotter.rangePlotter){
			trigger.setPosicao(temp);
			plotter.clearRangeMarker();
			plotter.addRangeMarker(trigger.getValueMarker());
			plotter.configRangeMarker();
			frameProjeto.getChartPanel().repaint();
		}
	}
	public void selectCanalTrigger(Canal ch){
		trigger.setCanal(ch);
	}
	
	public void setCanal(int numCanal, boolean ativo){
		if(numCanal == 1 ){
			if(ativo == false){
				plotter.clearSeriesBuffers(1);
			}
			ch1.select(ativo);
		}
		else{
			ch2.select(ativo);
		}
	}
	
	public String atualizaEscalaTensao(Canal ch, int sentido){
		if(ch.getEscalaTensao() ==0 && sentido == -1 ){
			ch.setEscalaTensao(0);
		}
		else if(ch.getEscalaTensao() ==Canal.seriesEscalaTensao.length-1 && sentido == 1){
			ch.setEscalaTensao(Canal.seriesEscalaTensao.length-1);
		}
		else{
			ch.setEscalaTensao((ch.getEscalaTensao() + sentido));
			if(ch==ch1){
				//plotter.clearSeriesBuffers(1);
			}
			if(ch==ch2){
				//plotter.clearSeriesBuffers(2);
			}
		}
		
		//esse metodos vao mudar, na real .
		if(ch.getEscalaTensao()<=Canal.baixaTensao){
			uc.getSAD().setAmplifica(true);
			uc.getSAD().setAtenua(false);
			ch.configCanal(true, false);
		}
		else if(ch.getEscalaTensao()>=Canal.altaTensao){
			uc.getSAD().setAmplifica(false);
			uc.getSAD().setAtenua(true);
			ch.configCanal(false, true);
		}
		else{
			uc.getSAD().setAmplifica(false);
			uc.getSAD().setAtenua(false);
			ch.configCanal(false, false);
			
		}
		return Canal.escalaTensaoStr[ch.getEscalaTensao()];
	}
		
	public String atualizaEscalaTempo(int sentido){
		int escalaTempoAnterior = Canal.escalaTempo;
		if(Canal.escalaTempo ==0 && sentido == -1 ){
			Canal.escalaTempo = 0;
		}
		else if(Canal.escalaTempo ==Canal.seriesEscalaTempo.length-1 && sentido == 1){
			Canal.escalaTempo = Canal.seriesEscalaTempo.length-1;
		}
		else{
			Canal.escalaTempo = Canal.escalaTempo + sentido;
			//plotter.clearSeriesBuffers(3);
		}
		
		if(escalaTempoAnterior == 4 && Canal.escalaTempo == 5){
			plotter.clearSeriesBuffers(3);
		}
		if(escalaTempoAnterior == 5 && Canal.escalaTempo == 4){
			plotter.clearSeriesBuffers(3);
		}
		return(Canal.escalaTempoStr[Canal.escalaTempo]);
	}
	
	public void selectCursores(boolean ativo){
		Cursor.ativo = ativo;
		if(ativo)
		{
			cursor1.select(true);
			cursor2.select(false);
			plotter.addDomainMarker(cursor1.getValueMarker());
			plotter.addDomainMarker(cursor2.getValueMarker());
			
		}
		else{
			cursor1.select(false);
			cursor2.select(false);
			plotter.clearDomainMarker();
		}
		plotter.configDomainMarker();
		frameProjeto.getChartPanel().repaint();
	}
	
	public void ativaCursor(int numCursor){
		if(numCursor == 1){
			cursor1.select(true);
			cursor2.select(false);
		}
		else{
			cursor1.select(false);
			cursor2.select(true);
		}
	}
	
	public void atualizaPosCursores(double posicao){
		double xval = plotter.getPlot().getDomainAxis().java2DToValue(posicao, frameProjeto.getChartPanel().getChartRenderingInfo().getPlotInfo().getDataArea(),plotter.getPlot().getDomainAxisEdge());
		if(cursor1.isEnable()){
			cursor1.setPosicao(xval);
		}
		else if(cursor2.isEnable()){
			cursor2.setPosicao(xval);
		}
		plotter.clearDomainMarker();
		plotter.addDomainMarker(cursor1.getValueMarker());
		plotter.addDomainMarker(cursor2.getValueMarker());
		plotter.configDomainMarker();
		frameProjeto.getChartPanel().repaint();
		
	}
	
	public void setSingleShot(boolean ativo){
		singleShot = ativo;
	}
	public void setStop(boolean ativo){
		stop = ativo;
	}

	public void setAntAliasing(boolean ativo){
		antAliasing = ativo;
	}
	public boolean getAntAliasing(boolean ativo){
		return antAliasing;
	}
	public boolean getSingleShot(){
		return singleShot;
	}
	
	public boolean getStop(){
		return stop;
	}
	
	public XYPlot getPlot(){
		return plotter.getPlot();
	}
	public Cursor getCursor1(){
		return cursor1;
	}
	
	public Cursor getCursor2(){
		return cursor2;
	}
	
	public Canal getCanal1(){
		return ch1;
	}
	
	public Canal getCanal2(){
		return ch2;
	}
	
	public Trigger getTrigger(){
		return trigger;
	}
	
	public FrameProjeto getFrameProjeto(){
		return frameProjeto;
	}
	public void atualizaPosicaoOffset(Canal ch, double posicao){
		ch.setOffset(posicao);
	}
	public void atualizaPosicaoOffset(Canal ch, int sentido){
		double temp = ch.getOffset()+sentido*(Plotter.rangePlotter/250.0);
		if(Math.abs(temp)<Plotter.rangePlotter){
			ch.setOffset(temp);
		}
	}
	public void atualizaLabelCursores(){
		String [] s = {"","","","","","",""};
		if(Cursor.ativo){
			
			double c1ch1 = ch1.getTensao(cursor1.getPosicao()) * Canal.seriesEscalaTensao[ch1.getEscalaTensao()];
			//double c1ch2 = ch2.getTensao(cursor1.getPosicao()) * Canal.seriesEscalaTensao[ch2.getEscalaTensao()];
			
			double c2ch1 = ch1.getTensao(cursor2.getPosicao()) * Canal.seriesEscalaTensao[ch1.getEscalaTensao()];
			//double c2ch2 = ch2.getTensao(cursor2.getPosicao()) * Canal.seriesEscalaTensao[ch2.getEscalaTensao()];
			
			double c21ch1 = c2ch1-c1ch1;
			//double c21ch2 = c2ch2-c1ch2;
			
			double tempo = (cursor2.getPosicao() - cursor1.getPosicao())*Canal.seriesEscalaTempo[Canal.escalaTempo];
			
			s[0] = Converter.converteUnidadeTensao(c1ch1);
			//s[1] = testaUnidadeTensao(c1ch2);
			s[2] = Converter.converteUnidadeTensao(c2ch1);
			//s[3] = testaUnidadeTensao(c2ch2);
			s[4] = Converter.converteUnidadeTensao(c21ch1);
			//s[5] = testaUnidadeTensao(c21ch2);
			s[6] = Converter.converteUnidadeTempo(tempo);
			
		}
		frameProjeto.atualizaCursores(s);
	}
}