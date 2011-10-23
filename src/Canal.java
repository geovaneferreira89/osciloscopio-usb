import org.jfree.data.xy.XYSeries;

public class Canal {
	
	private XYSeries seriesCH;
	private int numCanal;
	private boolean ativo;
	public double posTempo;
	
	private double tensaoRMS;
	private double tensaoPP;
	private double frequencia;
	
	private boolean amplifica = false;
	private boolean atenua = true;
	private boolean antAliasing = false;
	
	private int escalaTensao;
	public static int escalaTempo;
	public static double [] seriesEscalaTempo = {0.000005,0.00005,0.0005,0.005,0.05,0.5,1}; //EXEMPLO
	public static double [] seriesEscalaTensao = {100.0, 25.0, 1/0.15, 1/0.4, 1/0.8, 1/1.5, 1/3.5, 0.2}; // EXEMPLO
	public static String [] escalaTensaoStr = {"10 mV/div","40 mV/div","150 mV/div","400 mV/div","800 mV/div","1.5 V/div","3.5 V/div","5 V/div"};
	public static String [] escalaTempoStr = {"5 us/div", "50 us/div", "0.5 ms/div", "5 ms/div", "50 ms/div", "0.5 s/div", "1 s/div"};

	// Informam na matrix seriesEscalaTensao e escalaTensaoStr as posições (index) 
	// onde ocorrem atenuacao/amp no sinal respectivamente:
	public final static int baixaTensao = 4;
	public final static int altaTensao = 6;
	
	public Canal(int numCanal){
		seriesCH = new XYSeries("Series "+ numCanal);
		posTempo = -Plotter.rangePlotter;
		escalaTempo = 0;
		escalaTensao = 0;
		
		tensaoPP = 0;
		frequencia = 0;
		tensaoRMS = 0;
		
		ativo = true;
		amplifica = false;
		atenua = true;
		antAliasing = false;
	}
	
	public void atualiza(){
		calcTensaoPP();
		calcTensaoRMS();
		calcFrequencia();
	}
	
	public void configCanal(boolean amplifica, boolean atenua){
		
	}
	
	public void selectAntAliasing(boolean antAliasing){
		this.antAliasing = antAliasing;
	}
	
	public boolean getAntAliasing(){
		return antAliasing;
	}
	
	public void newSeries(){
		seriesCH = new XYSeries("Series "+ numCanal);
	}
	
	public void calcTensaoRMS(){
	}
	
	public void calcTensaoPP(){
		tensaoPP = (seriesCH.getMaxY()-seriesCH.getMinY())/(seriesEscalaTensao[escalaTensao]);
	}
	
	public void calcFrequencia(){
	}
	
	public XYSeries getSeries(){
		return seriesCH;
	}
	
	public double getTensao(double tempo){
		return 0;
	}	
	
	public int getEscalaTensao(){
		return escalaTensao;
	}
	
	public double getTensaoPP(){
		return tensaoPP;
	}
	
	public void setEscalaTempo(int escalaTempo){
		this.escalaTempo = escalaTempo;
	}
	
	public void setEscalaTensao(int escalaTensao){
		this.escalaTensao = escalaTensao;
	}
	
	public void select(boolean ativo){
		this.ativo = ativo;
	}
	
	public boolean isEnable(){
		return ativo;
	}
}
