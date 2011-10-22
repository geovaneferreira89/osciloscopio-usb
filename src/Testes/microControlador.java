package Testes;

public class microControlador extends Thread {
		private double in[];
		private double out[];
		public static final int bufferuC = 30;
		GeradorDeFuncoes g1;
		private boolean status; 
		
		private SAD sad;
		
		public microControlador(GeradorDeFuncoes g1){
			in = new double[bufferuC];
			out = new double[bufferuC];
			status = false;
			this.g1 = g1;
			sad = new SAD(g1);
			//sad.start();
			
		}
		public void startuC(){
			this.start();
		}
		public void run(){
			int pos = 0;
			//int valor = 0;
			while(true){
				if(status == false){
					//valor = sad.getValor();
					if(g1.getStatus()){
		        		in[pos] = g1.getValor();
						g1.setStatus(false);
		        		pos ++;
					}
					if(pos == bufferuC){
						status = true;
						pos = 0;
					}
				}
			}
		}
		public double[] read(){
			return in;
		}
		public void setAmostras(double [] amostras){
			out = amostras;
		}
		public boolean getStatus(){
			return status;
		}
		public void setStatus(boolean status){
			this.status = status;
		}
		/*
		 * 		public int[] getOut() {
			return out;
		}
		public void setOut(int out[]) {
			this.out = out;
		}
		public int[] getIn() {
			return in;
		}
		public void setIn(int in[]) {
			this.in = in;
		}
	
		public int getValor() {
			return valor;
		}
		public void setValor(int valor) {
			this.valor = valor;
		}
		 */
		
}
