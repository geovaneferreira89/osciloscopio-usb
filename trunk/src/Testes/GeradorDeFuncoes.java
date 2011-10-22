package Testes;

public class GeradorDeFuncoes extends Thread {
	
	public static final int QUADRADA = 0;
    public static final int TRIANGULAR = 1;
	public static final int SENOIDE = 2;
	
	private int estado;
	private int frequencia;
	private double valor;
	private double amplitude;
	private double time;
	public GeradorDeFuncoes(){
		time = 0;
		estado = GeradorDeFuncoes.SENOIDE;
		frequencia = 1;
		amplitude = 0;
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
		double valor = 0;
		valor = amplitude*Math.sin(2*Math.PI*frequencia*time);
		return valor;
	}
	private double getValorTriangular(double time) {
		double valor = 0;
		return 0;
	}
	private double getValorQuadrada(double time) {
		if (  amplitude*Math.sin(2*Math.PI*frequencia*time) > 0){
			valor = amplitude;
		}else
			valor = 0;
		return valor;
	}
	
	@Override
	public void run(){
		long timeElapsed = 0;
		long startTime  = System.currentTimeMillis();
		while(true){
			  timeElapsed = System.currentTimeMillis()- startTime;
			  startTime = System.currentTimeMillis();
			  time += 0.001;
				setValor(getValor(time/1000.0));
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					
					e.printStackTrace();
				}
		}
	}
	
	public int getFrequencia() {
		return frequencia;
	}
	public void setFrequencia(int frequencia) {
		this.frequencia = frequencia;
	}
	public double getValor() {
		return valor;
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

}
