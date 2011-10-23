//digital double conversor
public class DDC{
	public static final int dmax = Emb_SAD.adcMaxValue;
	public static double converteDigitalDouble(Canal c,int numero){
		double temp = 0;
		temp = Canal.seriesEscalaTensao[c.getEscalaTensao()];
		if(c.getEscalaTensao()<=Canal.baixaTensao){
			 temp = (Emb_SAD.maxTensao1/(dmax/2))*temp;
		}
		else if(c.getEscalaTensao()>=Canal.altaTensao){
			 temp = (Emb_SAD.maxTensao3/(dmax/2))*temp;
		}
		else{
			 temp = Emb_SAD.maxTensao2*temp/(dmax/2);
		}
		return ((numero-(dmax/2))*temp);
	}
	
}