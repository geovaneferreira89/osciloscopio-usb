import ch.ntb.usb.*;

public class Comunicacao implements Runnable{
	
	private byte[] input;
	private byte[] output;
	private boolean status;	
	@Override
	public void run() {
		while(true){
			try{ 
	            Thread.sleep(10);
	         } catch( InterruptedException e ) {
	             System.out.println("Interrupted Exception caught");
	         }
		}
		
	}
	
	public byte[] read(){
		return null;
	}

	public void write(byte [] b){
		
	}
	
	public boolean getStatus(){
		return status;
	}
	
}