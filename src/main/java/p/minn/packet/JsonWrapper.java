package p.minn.packet;

import java.util.HashMap;
import java.util.Map;

import p.minn.common.utils.UtilCommon;

/**
 * @author minn
 * @QQ:394286006
 * 
 */
public class JsonWrapper {
 
  private int group=-1;
  
  private String uuid;
  
  private int clientId=-1;
  
  private String method=null;
  
  private String invokeMethod=null;
  
  private String status=null;
  
  private Map<String,Object> data=null;

  public JsonWrapper() {
    super();
    
  }
  
  public JsonWrapper(int group, int clientId, String method, String invokeMethod, String status,
      Map<String, Object> data) {
    super();
    this.group = group;
    this.clientId = clientId;
    this.method = method;
    this.invokeMethod = invokeMethod;
    this.status = status;
    this.data = data;
  }



  public JsonWrapper(JsonPacket packet) {
    super();
      Map map=(Map) UtilCommon.gson2T(new String(packet.body),Map.class);
      this.method=UtilCommon.getString(map.get("method"));
      this.invokeMethod=UtilCommon.getString( map.get("invokeMethod"));
      this.status=UtilCommon.getString(map.get("status"));
      this.clientId=Double.valueOf(UtilCommon.getString(map.get("clientId"))).intValue();
      this.uuid=UtilCommon.getString(map.get("uuid"));
      this.data=(Map) UtilCommon.gson2T(map.get("data").toString(),Map.class);
      packet.setClientId(this.clientId);
   
  }
  
  public JsonWrapper(byte[] body) {
    super();
      Map map=(Map) UtilCommon.gson2T(new String(body),Map.class);
      this.status=UtilCommon.getString(map.get("status"));
      this.uuid=UtilCommon.getString(map.get("uuid"));
      this.method=UtilCommon.getString(map.get("method"));
      this.invokeMethod=UtilCommon.getString( map.get("invokeMethod"));
      this.clientId=Double.valueOf(UtilCommon.getString(map.get("clientId"))).intValue();
      this.data=(Map) UtilCommon.gson2T(map.get("data").toString(),Map.class);
  }

  public Map<String, Object> getData() {
    return data;
  }
  

  public String getMethod() {
    return method;
  }

  public String getInvokeMethod() {
    return invokeMethod;
  }

  public int getClientId() {
    return clientId;
  }

  public String getStatus() {
    return status;
  }

  public void setClientId(int clientId) {
    this.clientId = clientId;
  }

  public int getGroup() {
    return group;
  }

  public void setGroup(int group) {
    this.group = group;
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public void setData(Map<String, Object> data) {
    this.data = data;
  }

  public JsonWrapper clone(){
    return new JsonWrapper(this.group,this.clientId,this.method,this.invokeMethod,this.status,new HashMap<String,Object>());
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public void setMethod(String method) {
    this.method = method;
  }

  public void setInvokeMethod(String invokeMethod) {
    this.invokeMethod = invokeMethod;
  }
 
  
}
