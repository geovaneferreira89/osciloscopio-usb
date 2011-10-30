import java.text.DecimalFormat;

//digital double conversor 
public class Converter{
	public static final int dmax = Emb_SAD.adcMaxValue;
	private static final DecimalFormat aproximador = new DecimalFormat( "0.000" );
	//converte o inteiro em um double que será usado na classe plotter.
	public static double converteDigitalDouble(Canal c,int numero){
		double temp = 0.0;
		temp = (1/Canal.seriesEscalaTensao[c.getEscalaTensao()]);
		if(c.getEscalaTensao()<=Canal.baixaTensao){
			 temp = (Emb_SAD.maxTensao1/(dmax/2))*temp;
		}
		else if(c.getEscalaTensao()>=Canal.altaTensao){
			 temp = (Emb_SAD.maxTensao3/(dmax/2))*temp;
		}
		else{
			 temp = Emb_SAD.maxTensao2*temp/(dmax/2);
		}
		int num2 = numero-(dmax/2);
		double dnum = (num2) * temp;
		return dnum;
	}
	
	public static String converteUnidadeTensao(double tensao){
		if(Math.abs(tensao) < 0.5){
			return aproximador.format(tensao*1000) + " mV";
		}
		return aproximador.format(tensao)+" V";
	}
	public static String converteUnidadeTempo(double tempo){
		if(Math.abs(tempo) < 1 && Math.abs(tempo) > 0.001 ){
			return aproximador.format(tempo*1000) + " ms";
		}
		if(Math.abs(tempo) < 0.001){
			return aproximador.format(tempo*1000*1000) + " us";
		}
		return aproximador.format(Math.abs(tempo))+ " s";
	}
}