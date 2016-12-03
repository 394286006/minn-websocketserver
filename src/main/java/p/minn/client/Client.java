package p.minn.client;

import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;

import p.minn.controller.Controller;
import p.minn.packet.websocket.JsonDecoder;
import p.minn.packet.websocket.JsonEncoder;
import p.minn.packet.websocket.JsonPacket;
import p.minn.packet.websocket.JsonWrapper;
import p.minn.utils.WebSocketConstants;
import p.minn.utils.DateUtil;

/**
 * @author minn
 * @QQ:394286006
 * 
 */
public class Client extends BaseClient<JsonPacket, JsonWrapper> {

  public Client(String uuid,SocketChannel socket, Controller clientController, int clientId) {
    super(uuid,socket, clientId, clientController);
    decoder = new JsonDecoder(this, socket);
    encoder = new JsonEncoder(socket);
  }    

  public void broadCast(JsonPacket packet) throws Exception {
    evt.broadcast(group, packet, this.clientId);
  }

  public void onEvent(JsonWrapper wrapper,JsonPacket packet) throws Exception {
    if (wrapper.getMethod().equals("msg")) {
      msg(wrapper);
    }
  }
  
  

  private void msg(JsonWrapper wrapper) throws Exception {
    long last=System.currentTimeMillis();
    long current=last;
    while (true) {
      current=System.currentTimeMillis();
      if(current-last>1000){
        JsonWrapper newwrapper=wrapper.clone();
        JsonPacket newpacket = new JsonPacket(this.clientId);
        newwrapper.setStatus(WebSocketConstants.CONNECTING);
        newwrapper.setGroup(group);
        newwrapper.setClientId(this.clientId);
        newwrapper.getData().clear();
        newwrapper.getData().put("msg", DateUtil.toDateTime(current));
        newpacket.setBody(newwrapper);
        this.getEncoder().add(newpacket);
        last=current;
      }
    }
  }

  @Override
  public void existsMessage(double responseId) throws Exception {
    // TODO Auto-generated method stub
    Map<String,Object> data=new HashMap<String,Object>();
    Map<String,Object> connect=new HashMap<String,Object>();
    connect.put("msg", "用户已登录");
    data.put("data", connect);
    data.put("uuid", uuid);
    data.put("status", WebSocketConstants.CONNECTED);
    JsonPacket newpacket = new JsonPacket(this.clientId);
    newpacket.setBody(data);
    this.getEncoder().add(newpacket);
  }

  public void onErrorPacketHandler(String valueOf) {
    // TODO Auto-generated method stub
    
  }

  public void onControllerEvent(JsonPacket packet) throws Exception {
    // TODO Auto-generated method stub
    
  }


}
