package p.minn.packet;

import java.util.Map;

import p.minn.common.utils.UtilCommon;
import p.minn.packet.Packet;

/**
 * @author minn
 * @QQ:394286006
 * 
 */
public final class JsonPacket extends Packet {

  public JsonPacket() {
    super();
  }
  
  public JsonPacket(int clientId) {
    super(clientId);
  }
  
  public String toString(){
    String message = new String(this.getBody());
    return message;
  }

  public void setBody(byte[] body) {
    this.bodySize=body.length;
    this.body = body;
  }
  
  public void setBody(JsonWrapper wrapper){
      String str =UtilCommon.gson2Str(wrapper);
      byte[] msg=str.getBytes();
      setBody(msg);
  }
  public void setBody(Map<String,Object> wrapper){
    String str =UtilCommon.gson2Str(wrapper);
    byte[] msg=str.getBytes();
    setBody(msg);
}

  
}
