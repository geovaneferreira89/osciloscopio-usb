package Testes;

public class GeradorDeFuncoes extends Thread {
	
	public static final int QUADRADA = 0;
    public static final int TRIANGULAR = 1;
	public static final int SENOIDE = 2;
	
	private int estado;
	private int frequencia;
	private double valor;
	private double amplitude;
	
	public GeradorDeFuncoes(){
		setEstado(GeradorDeFuncoes.SENOIDE);
		frequencia = 1;
		amplitude = 0;
		this.start();
	}
	public double getValor(long time){
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
	private double getValorSenoide(long time) {
		double valor = 0;
		valor = amplitude*Math.sin(2*Math.PI*frequencia*time);
		return valor;
	}
	private double getValorTriangular(long time) {
		double valor = 0;
		return 0;
	}
	private double getValorQuadrada(long time) {
		// TODO Auto-generated method stub
		return 0;
	}
	public void run(){
		this.setValor(getValor(System.currentTimeMillis()/1000));
		
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
		amplitude = amplitude;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}

}
