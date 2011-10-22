package Testes;

public class microControlador extends Thread {
		private int in[];
		private int out[];
		private int valor;
		private SAD sad;
		public microControlador(GeradorDeFuncoes g){
			setValor(0);
			setIn(new int[50]);
			setOut(new int[50]);
			sad = new SAD(g);
			sad.start();
		}
		@Override
		public void run(){
			int valor = 0;
			while(true){
					valor = sad.getValor();
					System.out.println(valor);
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		}
		public int[] getOut() {
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
		
}
