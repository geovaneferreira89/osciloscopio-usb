import ch.ntb.usb.*;

public class Comunicacao implements Runnable{
	
	private byte[] input;
	private boolean status;	
	private Device dev = USB.getDevice((short) 0x0483, (short) 0x5750);
	
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
	      // data read from the device
	       input = new byte[4];
	      try 
	      {
			dev.open(1, 0, -1);
			dev.readInterrupt(0x81, input, input.length, 2000, false);
			return input;
	      }
	      catch (USBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void write(byte [] output){
	    try 
	    {
	        dev.open(1, 0, -1);
			dev.writeInterrupt(0x81, output, output.length, 2000, false);
		} 
	    catch (USBException e)
	    {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean getStatus(){
		return status;
	}
	
}