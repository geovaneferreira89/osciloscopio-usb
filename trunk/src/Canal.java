import java.util.Timer;
import java.util.TimerTask;

import jfreedsp.math.Complex;
import jfreedsp.math.DSP;

import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYSeries;

import Testes.GeradorDeFuncoes;
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
	
	private double tRMS;
	private int lastRMS;
	
	// Informam na matrix seriesEscalaTensao e escalaTensaoStr as posições (index) 
	// onde ocorrem atenuacao/amp no sinal respectivamente:
	public final static int baixaTensao = 2;
	public final static int altaTensao = 4;
	
	public final static int realTime = 4;
	
	public static boolean changed = false;
	private boolean changedEsp;

	
	public Canal(int numCanal){
		dataComunicacao = new int[microControlador.bufferuC];
		posTempo = 0;
		escalaTempo = 0;
		changed = false;
		changedEsp = false;
		escalaTensao = seriesEscalaTensao.length-1;
		
		offset = 0;
		
		ativo = true;
		amplifica = false;
		atenua = true;
		
		tRMS = 0;
		lastRMS = 0;
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
			double rms = tRMS;
			for(int i= lastRMS; i < serie.getItemCount(); i++){
				rms += Math.pow((Double) serie.getY(i)*Canal.seriesEscalaTensao[this.getEscalaTensao()],2);
			}
			lastRMS = serie.getItemCount();
			tRMS = rms;
			rms = Math.sqrt(rms/serie.getItemCount());
			return rms;
		}
		return 0.0;
	}
	public void clear_RMS_FREQ(){
		tRMS = 0;
		lastRMS = 0;
	}
	public double calcTensaoPP(){
		if(serie != null && !serie.isEmpty()){
			return (serie.getMaxY()-serie.getMinY())* Canal.seriesEscalaTensao[this.getEscalaTensao()];
		}
		return 0.0;
	}
	
	public double calcFrequencia(){
		if(serie != null  && !serie.isEmpty()){
			double [] amostras = new double[serie.getItemCount()];
		
			for(int i=1; i < serie.getItemCount(); i++){
				amostras[i-1] = (Double) serie.getY(i);
			}
			
			Complex[] dft = DSP.DFT(amostras);
			double max = dft[0].getModule();
			int maxi = 0;
			for (int i = 1; i < dft.length; i ++) {
				double atu = dft[i].getModule();
				if (atu > max) {
					max = atu;
					maxi = i;
				}
			}
			//Freq Amostragem = 1000;
			return (GeradorDeFuncoes.frequenciaAmostragem * maxi) / (2 * dft.length);
		}
		return 0.0;
	}         
	  
	      
	public void setSeries(XYSeries serie){
		this.serie = serie;
	}
	
	public double getTensaoCursor(Cursor c){
		if(serie != null && !serie.isEmpty()){
			double tempo = c.getPosicao();
			if(tempo <= serie.getMinX()|| tempo >= serie.getMaxX()){
				//System.out.println(tempo);
				return 0.0;
			}
			return getTensaoCursorRecursivo(tempo, 0, serie.getItemCount(),20);
		}
		return 0.0;
	}
	
	public double getTensaoCursorRecursivo(double tempo,int posicaoIni, int posicaoFin,int contador){
		int  posicao = posicaoIni + (posicaoFin - posicaoIni)/2;
		if(posicao <= 0 || posicao >= serie.getMaximumItemCount() || 
				(Double)serie.getX(posicao) <= (Double)serie.getMinX() || 
				(Double)serie.getX(posicao) > (Double)serie.getMaxX()){
			return 0.0;
		}
		
		double anteriorY = (Double) serie.getY(posicao-1);
		double proximoY = (Double) serie.getY(posicao);
		double anteriorX = (Double) serie.getX(posicao-1);
		double proximoX = (Double) serie.getX(posicao);
		
		if(anteriorX < tempo && proximoX > tempo){
			double m = (anteriorY-proximoY)/proximoX-proximoY;
			double b = -m*anteriorX +anteriorY;
			return (m*tempo+b) * Canal.seriesEscalaTensao[this.getEscalaTensao()];
		}
		contador --;
		if (contador == -1){
			return 0.0;
		}
			
		if(proximoX>=tempo){
			return getTensaoCursorRecursivo(tempo, posicaoIni , posicao, contador);
		}
		else{
			return getTensaoCursorRecursivo(tempo, posicao+1, posicaoFin, contador);
		}
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
	public XYSeries getSerie(){
		return serie;
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
		posTempo = 0;
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
