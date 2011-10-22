import javax.swing.JFrame;

import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.XYPlot;

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
	}
	
	public void startAll(){
		Thread t  = new Thread(plotter);
		t.start();
		Thread t2  = new Thread(comunicacao);
		t2.start();
		Thread t3  = new Thread(this);
		t3.start();
	}
	
	@Override
	public void run() {
		while(true){
			try{ 
	            Thread.sleep(10);
	         } catch( InterruptedException e ) {
	             System.out.println("Interrupted Exception caught");
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