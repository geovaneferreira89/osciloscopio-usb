import org.jfree.data.xy.XYSeries;

public class Canal {
	
	private XYSeries seriesCH;
	private boolean ativo;
	private int escalaTensao;
	
	private double tensaoRMS;
	private double tensaoPP;
	private double frequencia;
	
	private static int escalaTempo;
	private static double [] seriesEscalaTempo = {0.1,0.2,0.3};
	private static double [] seriesEscalaTensao = {0.1,0.2,0.3};
	
	public Canal(){
		
	}
	public void atualizaDataSet(double [] data){
		
	}
	public double getTensao(double tempo){
		
		return 0;
	}
	public double getEscalaTensao(){
		return seriesEscalaTensao[escalaTensao];
	}
	public double getEscalaTempo(){
		
		return seriesEscalaTempo[escalaTempo];
	}
	public double calcTensaoRMS(){
		
		return 0;
	}
	public double calcTensaoPP(){
		
		return 0;
	}
	public double calcFrequencia(){
		
		return 0;
	}
	public void select(boolean ativo){
		this.ativo = ativo;
	}
	public boolean isEnable(){
		return ativo;
	}
	
	
}
