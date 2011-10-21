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
	
	public Controle(){
		plotter = new Plotter(this);
		comunicacao = new Comunicacao();
		protCom = new ProtocoloComunicacao();
		trigger = new Trigger();
		ch1 = new Canal();
		ch2 = new Canal();
		cursor1 = new Cursor();
		cursor2 = new Cursor();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
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
	public void atualizaEscalaTensao(int numCanal, int sentido){
		
	}
	public void atualizaEscalaTempo(int sentido){
		
	}
	public void selectCursores(boolean ativo){
		
	}
	public void atualizaPosCursores(int Cursor, double posicao){
		
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
}