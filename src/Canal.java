import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYSeries;

import Testes.microControlador;

public class Canal {
	
	XYSeries serie;
	private int numCanal;
	private boolean ativo;
	private double offset;
	
	private boolean amplifica;
	private boolean atenua;
	
	private int escalaTensao;
	public static int escalaTempo;
	public static double [] seriesEscalaTempo = {0.000005,0.00005,0.0005,0.005,0.05,0.5,1}; //EXEMPLO
	public static double [] seriesEscalaTensao = {0.01, 0.04, 0.2, 0.5, 2, 5,10}; // EXEMPLO
	
	public static String [] escalaTensaoStr = {"10 mV/div","40 mV/div","200 mV/div","500 mV/div","2 V/div","5 V/div","10 V/div"};
	public static String [] escalaTempoStr = {"5 us/div", "50 us/div", "0.5 ms/div", "5 ms/div", "50 ms/div", "0.5 s/div", "1 s/div"};
	
	private int [] dataComunicacao;
	private double posTempo;
	
	// Informam na matrix seriesEscalaTensao e escalaTensaoStr as posições (index) 
	// onde ocorrem atenuacao/amp no sinal respectivamente:
	public final static int baixaTensao = 2;
	public final static int altaTensao = 4;
	
	public final static int realTime = 3;
	
	public static boolean changed = false;
	private boolean changedEsp;
	
	public Canal(int numCanal){
		dataComunicacao = new int[microControlador.bufferuC];
		posTempo = -Plotter.rangePlotter;
		escalaTempo = 0;
		changed = false;
		changedEsp = false;
		escalaTensao = seriesEscalaTensao.length-1;
		
		offset = 0;
		
		ativo = true;
		amplifica = false;
		atenua = true;
		
	}
	
	public void setDataComunicacao(int dataComunicacao[]){
		this.dataComunicacao = dataComunicacao;
	}
	public int[] getDataComunicacao(){
		return dataComunicacao;
	}
	
	public void configCanal(boolean amplifica, boolean atenua){
		this.amplifica = amplifica;
		this.atenua = atenua;
	}
	
	public double calcTensaoRMS(){
		if(serie != null && !serie.isEmpty()){
			double rms = 0;
			for(int i=1; i < serie.getItemCount(); i++){
				rms = rms + Math.pow((Double) serie.getY(i),2) + Math.pow((Double) serie.getX(i),2);
			}
			rms = Math.sqrt(rms/serie.getItemCount());
			return rms * Canal.seriesEscalaTensao[this.getEscalaTensao()];
		}
		else
			return 0.0;
	}
	
	public double calcTensaoPP(){
		if(serie != null && !serie.isEmpty()){
			return (serie.getMaxY()-serie.getMinY())* Canal.seriesEscalaTensao[this.getEscalaTensao()];
		}
		return 0.0;
	}
	
	public double calcFrequencia(){
		return 0.0;
	}
	
	public void setSeries(XYSeries serie){
		this.serie = serie;
	}
	
	public double getTensao(double tempo){
		if(serie != null && !serie.isEmpty()){
			double anteriorY;
			double proximoY;
			double anteriorX;
			double proximoX;
			
			for(int i = 1 ; i < serie.getItemCount() ;i++){
				
				anteriorY = (Double) serie.getY(i-1);
				proximoY = (Double) serie.getY(i);
				anteriorX = (Double) serie.getX(i-1);
				proximoX = (Double) serie.getX(i);
				
				if(anteriorX < tempo && proximoX > tempo){
					double m = (anteriorY-proximoY)/proximoX-proximoY;
					double b = -m*anteriorX +anteriorY;
					return (m*tempo+b) * Canal.seriesEscalaTensao[this.getEscalaTensao()];
				}
			}
		}
		return 0.0;
	}	
	
	public int getEscalaTensao(){
		return escalaTensao;
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
	public void setOffset(double offset){
		this.offset = offset;
	}
	public double getOffset(){
		return offset;
	}
	public double getPosTempo(){
		return posTempo;
	}
	public void setPosTempo(double posTempo){
		this.posTempo = posTempo;
	}
	public void clearPosTempo(){
		posTempo = -Plotter.rangePlotter;
	}
	public boolean isChanged(){
		return changedEsp;
	}
	public void configMudada(){
		changedEsp = true;
	}
	public void resetConfigMudada(){
		changedEsp = false;
	}
}
