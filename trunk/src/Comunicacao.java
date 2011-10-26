import ch.ntb.usb.*;

public class Comunicacao implements Runnable{
	
	private byte[] input;
	private byte[] output;
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
	      byte[] readData = new byte[4];
	      try 
	      {
			dev.open(1, 0, -1);
			dev.readInterrupt(0x81, readData, readData.length, 2000, false);
			return readData;
	      }
	      catch (USBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void write(byte [] b){
	    try 
	    {
	        dev.open(1, 0, -1);
			dev.writeInterrupt(0x81, b, b.length, 2000, false);
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