package Testes;

public class microControlador extends Thread {
		private int in[];
		private int out[];
		public static final int bufferuC = 30;
		GeradorDeFuncoes g1;
		private boolean status; 
		
		private SAD sad;
		
		public microControlador(GeradorDeFuncoes g1){
			in = new int[bufferuC];
			out = new int[bufferuC];
			status = false;
			this.g1 = g1;
			sad = new SAD(g1);
			sad.start();
		}
		public void startuC(){
			this.start();
		}
		public void run(){
			int pos = 0;
			while(true){
				synchronized(Monitor.M_C){
					while(!Monitor.M_C.livre){
						try {
							Monitor.M_C.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					synchronized(Monitor.S_M){
						while(Monitor.S_M.livre){
							try {
								Monitor.S_M.wait();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						if(status == false){
							if(sad.getStatus()){
				        		in[pos] = sad.getValor();
				        		pos ++;
				        		if(pos == bufferuC){
									status = true;
									pos = 0;
								}
								sad.setStatus(false);
							}
						}
						Monitor.S_M.livre = true;
						Monitor.S_M.notifyAll();
					}
					Monitor.M_C.livre = false;
					Monitor.M_C.notifyAll();
				}
			}
		}
		public int[] read(){
			return in;
		}
		public boolean getStatus(){
			return status;
		}
		public void setStatus(boolean status){
			this.status = status;
		}
		public SAD getSAD(){
			return sad;
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
		
}
