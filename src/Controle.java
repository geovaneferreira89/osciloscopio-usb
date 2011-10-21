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
		trigger = new Trigger();
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
			
		}
		
	}
	
	public void conectarUSB(){
	
		
	}
	
	public void warnEmb(){
		changed = true;
	}
	
	public void setTrigger(int numCanal, double tensao){
		
	}
	
	public void disableTrigger(){
		
	}
	
	public void setCanal(int numCanal, boolean ativo){
		
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
			plotter.getPlot().addDomainMarker(cursor1.getValueMarker());
			plotter.getPlot().addDomainMarker(cursor2.getValueMarker());
			
		}
		else{
			cursor1.select(false);
			cursor2.select(false);
			plotter.getPlot().clearDomainMarkers();
		}
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
		plotter.getPlot().clearDomainMarkers();
		plotter.getPlot().addDomainMarker(cursor1.getValueMarker());
		plotter.getPlot().addDomainMarker(cursor2.getValueMarker());
		frameProjeto.getChartPanel().repaint();
		
	}
	
	public void setSingleShot(boolean ativo){
		
	}
	
	public void setAntAliasing(boolean ativo){
		
	}
	
	public void getSingleShot(){
		
	}
	
	public void getAntAliasing(){
		
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