package p.minn.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

/**
 * @author minn
 * @QQ:394286006
 * 
 */
public class ProtocolUtil {
  
  public static byte[] getHandShakeResponse(ByteBuffer buff) throws Exception{
    StringBuffer sb=new StringBuffer();
    sb.append("HTTP/1.1 101 Switching Protocols\r\nUpgrade: websocket\r\nConnection: Upgrade\r\nSec-WebSocket-Accept:");
    sb.append(getDigest(buff));
    sb.append("\r\n\r\n");
    return sb.toString().getBytes();
  }
  
  private static String getDigest(ByteBuffer buff) throws IOException, NoSuchAlgorithmException{
    BufferedReader bIn = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(buff.array())));
    String str=null;
    while (!(str = bIn.readLine()).equals("")) {
        String[] strs = str.split(": ");
        if (strs.length == 2) {
          if(strs[0].equals("Sec-WebSocket-Key")){
            str=strs[1];
            break;
          }
        }
    }   
   return new BASE64Encoder().encode(MessageDigest.getInstance("SHA-1").digest((str + Constants.DIGEST_KEY).getBytes()));
  }
  
  public static byte[] unMask(byte[] mask, byte[] data) {
    for (int i = 0; i < data.length; i++) {
        data[i] = (byte) (data[i] ^ mask[i % mask.length]);
    }
    return data;
    }
  
  public static int getSizeOfPayload(byte b) {
     return ((b & 0xFF) - 0x80);
    }
  
  public static String convert2String(byte[] bytes) {
    StringBuilder sb = new StringBuilder();
    for (byte b : bytes) {
        sb.append(String.format("%02X ", b));
    }
     return sb.toString();
    }
}
