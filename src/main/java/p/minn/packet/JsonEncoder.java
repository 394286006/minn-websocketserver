package p.minn.packet;

import java.io.BufferedOutputStream;
import java.nio.channels.SocketChannel;

import p.minn.packet.Encoder;
import p.minn.utils.Constants;

/**
 * @author minn
 * @QQ:394286006
 * 
 */
public class JsonEncoder  extends Encoder<JsonPacket>
{

	public JsonEncoder ( SocketChannel socket)
	{
      super(socket);
	}
	

	public void add (JsonPacket packet )
	{
		 try{
		       BufferedOutputStream out = new BufferedOutputStream(socket.socket().getOutputStream());
		       out.write(Constants.SINGLE_FRAME_UNMASKED);
		       out.write(packet.bodySize);
		       out.write(packet.getBody());
		       out.flush();
		      } catch (Exception e) {
		        e.printStackTrace();
		   }
	}

  
    
}
