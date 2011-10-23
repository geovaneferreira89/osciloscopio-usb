import javax.swing.JFrame;

import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.XYPlot;

import Testes.GeradorDeFuncoes;
import Testes.Monitor;
import Testes.microControlador;

public class Controle implements Runnable{
	private boolean singleShot;
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
	
	//Objetos teste.
	private GeradorDeFuncoes g1;
	private microControlador uc;
	
	public Controle(FrameProjeto frame){
		frameProjeto = frame;
		plotter = new Plotter(this);
		comunicacao = new Comunicacao();
		protCom = new ProtocoloComunicacao();
		trigger = new Trigger(false);
		ch1 = new Canal(1);
		ch2 = new Canal(2);
		cursor1 = new Cursor(1);
		cursor2 = new Cursor(2);
		plotter = new Plotter(this);
		
		//Objetos teste.
		g1 = new GeradorDeFuncoes();
		g1.setAmplitude(20);
		g1.setFrequencia(50);
		g1.setEstado(GeradorDeFuncoes.SENOIDE);
		uc = new microControlador(g1);
	}
	
	public void startAll(){
		Thread t  = new Thread(plotter);
		t.start();
		Thread t2  = new Thread(comunicacao);
		t2.start();
		Thread t3  = new Thread(this);
		t3.start();
		
		 uc.start();
	}
	
	@Override
	public void run() {
		while(true){
				//Aplica o protocolo de comunicacao e verifica se é dado ou outra coisa.
				//Se for dado : (o teste envolverá somente o canal1):
				synchronized(Monitor.M_C){
					while(Monitor.M_C.livre){
						try {
							Monitor.M_C.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if(uc.getStatus()){
		        		plotter.atualizaDataSetCanais(uc.read(),null);
						uc.setStatus(false);
					}
					
					//System.out.println(ch1.getTensaoPP());
					Monitor.M_C.livre = true;
					Monitor.M_C.notifyAll();
				}
		}
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
		double temp = trigger.getPosicao()+sentido*(Plotter.rangePlotter/250);
		if(Math.abs(temp)<Plotter.rangePlotter){
			trigger.setPosicao(temp);
			plotter.clearRangeMarker();
			plotter.addRangeMarker(trigger.getValueMarker());
			plotter.configRangeMarker();
			frameProjeto.getChartPanel().repaint();
		}
	}
	public void selectCanalTrigger(int numCanal){
		if(numCanal == 1){
			trigger.config(ch1);
		}
		if(numCanal == 2){
			trigger.config(ch2);
		}
	}
	
	public void setCanal(int numCanal, boolean ativo){
		if(numCanal == 1 ){
			ch1.select(ativo);
		}
		else{
			ch2.select(ativo);
		}
	}
	
	public String atualizaEscalaTensao(int numCanal, int sentido){
		if (numCanal == 1){
			
			if(ch1.getEscalaTensao() ==0 && sentido == -1 ){
				ch1.setEscalaTensao(Canal.seriesEscalaTensao.length-1);
			}
			else{
				ch1.setEscalaTensao((ch1.getEscalaTensao() + sentido)% Canal.seriesEscalaTensao.length);
			}
			
			//esse metodos vao mudar, na real .
			if(ch1.getEscalaTensao()<=Canal.baixaTensao){
				uc.getSAD().setAmplifica(true);
				uc.getSAD().setAtenua(false);
				Emb_SAD.amplificaCH1 = true;
				Emb_SAD.atenuaCH1 = false;
			}
			else if(ch1.getEscalaTensao()>=Canal.altaTensao){
				uc.getSAD().setAmplifica(false);
				uc.getSAD().setAtenua(true);
				Emb_SAD.amplificaCH1 = false;
				Emb_SAD.atenuaCH1 = true;
			}
			else{
				uc.getSAD().setAmplifica(false);
				uc.getSAD().setAtenua(false);
				Emb_SAD.amplificaCH1 = false;
				Emb_SAD.atenuaCH1 = false;
				
			}
			return Canal.escalaTensaoStr[ch1.getEscalaTensao()];
		}
		if (numCanal == 2){
			
			if(ch2.getEscalaTensao() ==0 && sentido == -1 ){
				ch2.setEscalaTensao(Canal.seriesEscalaTensao.length-1);
			}
			else{
				ch2.setEscalaTensao((ch2.getEscalaTensao() + sentido)% Canal.seriesEscalaTensao.length);
			}
			return Canal.escalaTensaoStr[ch2.getEscalaTensao()];
		}
		return "";
	}
	
	public String atualizaEscalaTempo(int sentido){
		if(Canal.escalaTempo==0 && sentido == -1 ){
			Canal.escalaTempo = Canal.escalaTempoStr.length-1;
		}
		else{
			Canal.escalaTempo = (Canal.escalaTempo + sentido)% Canal.escalaTempoStr.length;
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
	
	public void setAntAliasing(boolean ativo){
		antAliasing = ativo;
	}
	
	public boolean getSingleShot(){
		return singleShot;
	}
	
	public boolean getAntAliasing(){
		return antAliasing;
	}
	
	public XYPlot getPlot(){
		return null;
	}
	
	public CombinedDomainXYPlot getCombinedPlot(){
		return plotter.getCombinedPlot();
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
	
}