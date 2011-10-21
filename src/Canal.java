import org.jfree.data.xy.XYSeries;

public class Canal {
	
	private XYSeries seriesCH;
	private int numCanal;
	private boolean ativo;
	
	private double tensaoRMS;
	private double tensaoPP;
	private double frequencia;
	
	private int escalaTensao;
	public static int escalaTempo;
	
	public static double [] seriesEscalaTempo = {1,2,3,4,5,6,7}; //EXEMPLO
	public static double [] seriesEscalaTensao = {1,2,3,4,5}; // EXEMPLO
	public static String [] escalaTensaoStr = {"10 mV/div","50 mV/div","250 mV/div","1 V/div","5 V/div"};
	public static String [] escalaTempoStr = {"5 us/div", "50 us/div", "0.5 ms/div", "5 ms/div", "50 ms/div", "0.5 s/div", "1 s/div"};
	
	public Canal(int numCanal){
		seriesCH = new XYSeries("Series "+ numCanal);
		escalaTempo = 0;
		escalaTensao = 0;
	}
	public void atualizaDataSet(double [] data){
		/*seriesCH1.clear();
		seriesCH2.clear();

		double tempo = 0;
		for(int i = 0 ; i<numAmostras; i++)
		{
			seriesCH1.add(tempo-5,dataCH1[i]);
			seriesCH2.add(tempo-5,dataCH2[i]);
			tempo += intervaloTempo;
		}
		collection.removeAllSeries();
        collection.addSeries(seriesCH1);
        collection.addSeries(seriesCH2);
        plotter.setDataset(collection);*/
        
	}
	public XYSeries getSeries(){
		return seriesCH;
	}
	public double getTensao(double tempo){
		return 0;
	}

	public double getEscalaTensaoDouble(){
		return seriesEscalaTensao[escalaTensao];
	}
	public int getEscalaTensao(){
		return escalaTensao;
	}
	public double getEscalaTempo(){
		return seriesEscalaTempo[escalaTempo];
	}
	public void setEscalaTempo(int escalaTempo){
		this.escalaTempo = escalaTempo;
	}
	public void setEscalaTensao(int escalaTensao){
		this.escalaTensao = escalaTensao;
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
