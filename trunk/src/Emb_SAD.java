// Essa classe possui alguns atributos que envolvem o microcontrolador e SAD.
public class Emb_SAD{
	public static final int adcMaxValue = 4096;
	public static final double maxTensao1 = 0.825;
	public static final double maxTensao2 = 1.65;
	public static final double maxTensao3 = 20;
	
	//arrumar para deixar esses métodos não estáticos.
	public static boolean amplificaCH1 = false;
	public static boolean atenuaCH1 = true;
	public static boolean antAliasingCH1 = false;
	
	public static boolean amplificaCH2 = false;
	public static boolean atenuaCH2 = true;
	public static boolean antAliasingCH2 = false;
	
	
	//Utilizar qndo o protocolo for decidido
	//public static final int bufferOut_uC = x;
	
}