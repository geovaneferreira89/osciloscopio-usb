package Testes;

public class SAD extends Thread{
	private int valor;
	private boolean atenua;
	private boolean amplifica;
	private GeradorDeFuncoes g1;
	private boolean status;
	
	
	
	public SAD(GeradorDeFuncoes g){
		atenua = true;
		amplifica = false;
		g1 = g;
		status = false;
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
			synchronized(Monitor.S_M){
				while(!Monitor.S_M.livre){
					try {
						Monitor.S_M.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				synchronized(Monitor.G_S){
					while(Monitor.G_S.livre){
						try {
							Monitor.G_S.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if(status==false){
						if(g1.getStatus()){
							setValor(sample(g1.getValor()));
							g1.setStatus(false);
							status = true;
						}
					}
					Monitor.G_S.livre = true;
					Monitor.G_S.notifyAll();
				}
				Monitor.S_M.livre = false;
				Monitor.S_M.notifyAll();
			}
		}
	}
	
	
	public boolean isAtenua() {
		return atenua;
	}
	public void setAtenua(boolean atenua) {
		this.atenua = atenua;
	}
	public boolean isAmplifica() {
		return amplifica;
	}
	public void setAmplifica(boolean amplifica) {
		this.amplifica = amplifica;
	}
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
