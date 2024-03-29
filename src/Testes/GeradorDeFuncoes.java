package Testes;


public class GeradorDeFuncoes extends Thread {
	
	public static final int QUADRADA = 0;
    public static final int TRIANGULAR = 1;
	public static final int SENOIDE = 2;
	
	private int estado;
	private int frequencia;
	private double valor;
	private double amplitude;
	
	private boolean status;
	
	private double tensaoRuido; 
	
	public static final double frequenciaAmostragem = 1000;
	
	public GeradorDeFuncoes(){
		setEstado(GeradorDeFuncoes.SENOIDE);
		frequencia = 1;
		amplitude = 0;
		status = false;
		tensaoRuido = 0;
		this.start();
		
	}
	public double getValor(double time){
		switch(getEstado()){
			case GeradorDeFuncoes.QUADRADA:
				return getValorQuadrada(time);
			case GeradorDeFuncoes.TRIANGULAR:
				return getValorTriangular(time);
			case GeradorDeFuncoes.SENOIDE:
				return getValorSenoide(time);
		}
		return 0;
		
	}
	private double getValorSenoide(double time) {
		double valor = amplitude*Math.sin(2*Math.PI*frequencia*time);
		return valor;
	}
	private double getValorTriangular(double time) {
		return 0;
	}
	private double getValorQuadrada(double time) {
		if (  amplitude*Math.sin(2*Math.PI*frequencia*time) > 0){
			valor = amplitude;
		}else
			valor = 0;
		return valor;
	}

	public void run(){
		double cont = 0;
		while(true){
			synchronized(Monitor.G_S){
				while(!Monitor.G_S.livre){
					try {
						Monitor.G_S.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				if(status == false){
		        	this.setValor(getValor(cont/frequenciaAmostragem));// 1KHz = frequencia de amostragem
		        	cont++;
	    			status = true;
		    		try{
			            Thread.sleep((int)((1000.0/frequenciaAmostragem)));
			         }catch( InterruptedException e ) {
			             System.out.println("Interrupted Exception caught");
			         }
				}
				Monitor.G_S.livre = false;
				Monitor.G_S.notifyAll();
			}
		}
	    
	}
	public int getFrequencia() {
		return frequencia;
	}
	public void setFrequencia(int frequencia) {
		this.frequencia = frequencia;
	}
	public void setRuido (double tensaoRuido){
		this.tensaoRuido = tensaoRuido;
	}
	public double getValor() {
		
		double aleatorio = Math.random();
		if(aleatorio >0.5)
		{
			aleatorio /=2;
			aleatorio = -aleatorio;
		}
		aleatorio *=2;
		return valor+aleatorio*tensaoRuido;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public double getAmplitude() {
		return amplitude;
	}
	public void setAmplitude(double amplitude) {
		this.amplitude = amplitude;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public void setStatus(boolean status){
		this.status = status;
	}
	public boolean getStatus(){
		return status;
	}

}
