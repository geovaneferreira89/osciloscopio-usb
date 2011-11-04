import java.text.DecimalFormat;

import javax.swing.JFrame;

import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.XYPlot;

import Testes.*;
public class Controle implements Runnable{
	private boolean singleShot;
	private boolean stop;
	private boolean antAliasing;
	private boolean warnEmb;
	
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
		warnEmb = false;
		
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
					if(Canal.changed ){
						plotter.clearSeriesBuffers(3);
						Canal.changed = false;
					}
					if(ch1.isChanged()){
						plotter.clearSeriesBuffers(1);
						ch1.resetConfigMudada();
					}
					if( ch2.isChanged()){
						plotter.clearSeriesBuffers(2);
						ch2.resetConfigMudada();
					}
					if(!statusPlotar){
						if(uc.getStatus()){
			        		ch1.setDataComunicacao(uc.read());
							uc.setStatus(false);
							statusPlotar = true;
						}
						
						atualizaLabelCursores();
						atualizaLabelCanais();
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
	
	public void setCanal(Canal ch, boolean ativo){
		ch.configMudada();
		ch.select(ativo);
	}
	
	public String atualizaEscalaTensao(Canal ch, int sentido){
		if(ch.getEscalaTensao() ==0 && sentido == -1 ){
			ch.setEscalaTensao(0);
		}
		else if(ch.getEscalaTensao() ==Canal.seriesEscalaTensao.length-1 && sentido == 1){
			ch.setEscalaTensao(Canal.seriesEscalaTensao.length-1);
		}
		else{
			ch.configMudada();
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
			warnEmb = true;
			uc.getSAD().setAmplifica(true);
			uc.getSAD().setAtenua(false);
			ch.configCanal(true, false);
		}
		else if(ch.getEscalaTensao()>=Canal.altaTensao){
			warnEmb = true;
			uc.getSAD().setAmplifica(false);
			uc.getSAD().setAtenua(true);
			ch.configCanal(false, true);
		}
		else{
			warnEmb = true;
			uc.getSAD().setAmplifica(false);
			uc.getSAD().setAtenua(false);
			ch.configCanal(false, false);
			
		}
		return Canal.escalaTensaoStr[ch.getEscalaTensao()];
	}
		
	public String atualizaEscalaTempo(int sentido){
		//warnEmb = true;
		int escalaTempoAnterior = Canal.escalaTempo;
		if(Canal.escalaTempo ==0 && sentido == -1 ){
			Canal.escalaTempo = 0;
		}
		else if(Canal.escalaTempo ==Canal.seriesEscalaTempo.length-1 && sentido == 1){
			Canal.escalaTempo = Canal.seriesEscalaTempo.length-1;
		}
		else{
			Canal.changed=true;
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
	public void stopPlotter(){
		if(!stop){
			Canal.changed=true;
		}
	}
	public void singleShotPlotter(){
		
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
		ch.configMudada();
		ch.setOffset(posicao);
	}
	public void atualizaPosicaoOffset(Canal ch, int sentido){
		ch.configMudada();
		double temp = ch.getOffset()+sentido*(Plotter.rangePlotter/250.0);
		if(Math.abs(temp)<Plotter.rangePlotter){
			ch.setOffset(temp);
		}
	}
	public void atualizaLabelCursores(){
		String [] s = {"","","","","","",""};
		if(Cursor.ativo){
			
			double c1ch1 = cursor1.getDados(ch1);
			//double c1ch2 = 
			double c2ch1 = cursor2.getDados(ch2);
			//double c2ch2 = 
			double c21ch1 = c2ch1-c1ch1;
			//double c21ch2 = c2ch2-c1ch2;
			
			double tempo = (cursor2.getPosicao() - cursor1.getPosicao())*Canal.seriesEscalaTempo[Canal.escalaTempo];
			
			s[0] = Converter.converteUnidadeTensao(c1ch1);
			//s[1] = 
			s[2] = Converter.converteUnidadeTensao(c2ch1);
			//s[3] = 
			s[4] = Converter.converteUnidadeTensao(c21ch1);
			//s[5] = 
			s[6] = Converter.converteUnidadeTempo(tempo);
			
		}
		frameProjeto.atualizaCursores(s);
	}
	public void atualizaLabelCanais(){
		String [] s = {"","","","","","",""};
		if(ch1.isEnable()){
			
			s[0] = Converter.converteUnidadeTensao(ch1.calcTensaoRMS());
			s[1] = Converter.converteUnidadeTensao(ch1.calcTensaoPP());
			s[2] = Converter.converteUnidadeFrequencia(ch1.calcFrequencia());
			
		}
		if(ch2.isEnable()){
			//s[3] = Converter.converteUnidadeTensao(ch1.calcTensaoRMS());
			//s[4] = Converter.converteUnidadeTensao(ch1.calcTensaoPP());
			//s[5] = Converter.converteUnidadeFrequencia(ch1.calcFrequencia());
		}
		frameProjeto.atualizaCanais(s);
	}
}