package p.minn.packet;



import java.nio.channels.SocketChannel;
import java.util.Arrays;

import p.minn.listener.ClientEventListener;
import p.minn.packet.Decoder;
import p.minn.utils.Constants;
import p.minn.utils.ProtocolUtil;


/**
 * @author minn
 * @QQ:394286006
 * 
 */
public class JsonDecoder extends Decoder<JsonPacket,JsonWrapper>
{
	

	public JsonDecoder ( ClientEventListener<JsonPacket,JsonWrapper> evt,SocketChannel socket )
	{
		super(evt,socket);
	}
	
	protected void read(byte[] data ) throws Exception 
	{
	    JsonPacket  packet =new JsonPacket();
		int headerFlag = data[0]& 0x0F;
		packet.bodyType=headerFlag;
        packet.bodySize=ProtocolUtil.getSizeOfPayload(data[1]);
        packet.setBody(ProtocolUtil.unMask(Arrays.copyOfRange(data, 2, Constants.MASK_SIZE_EXT), Arrays.copyOfRange(data, Constants.MASK_SIZE_EXT,Constants.MASK_SIZE_EXT+ packet.bodySize)));
        JsonWrapper wrapper=null;
    	wrapper=new JsonWrapper(packet);
        evt.onEvent(wrapper);
	}
	
	
}
