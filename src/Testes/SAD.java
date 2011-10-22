package Testes;

public class SAD extends Thread{
	private int valor;
	private boolean atenua;
	private boolean amplifica;
	private GeradorDeFuncoes g1;
	
	public SAD(GeradorDeFuncoes g){
		atenua = true;
		amplifica = false;
		g1 = g;
	}
	public double offset(double valor){
		return valor+1.65;
	}
	public int sample(double valor){
		if ( atenua) valor /= 12;
		if ( amplifica) valor *=4;
		valor = offset(valor);
		if ( valor > 3.3) valor = 3.3;
		if ( valor < 0 ) valor = 0;
		
		return (int)(valor*4096.0/3.3);
	}
	@Override
	public void run(){
		while(true){	
			setValor(sample(g1.getValor()));
		}
	}
	
	
	boolean isAtenua() {
		return atenua;
	}
	void setAtenua(boolean atenua) {
		this.atenua = atenua;
	}
	boolean isAmplifica() {
		return amplifica;
	}
	void setAmplifica(boolean amplifica) {
		this.amplifica = amplifica;
	}
	int getValor() {
		return valor;
	}
	void setValor(int valor) {
		this.valor = valor;
	}
	
	
}
