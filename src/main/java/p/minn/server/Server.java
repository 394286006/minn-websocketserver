package p.minn.server;

import p.minn.controller.Controller;
import p.minn.packet.JsonPacket;
import p.minn.packet.JsonWrapper;

/**
 * @author minn
 * @QQ:394286006
 * 
 */
public class Server extends BaseServer<JsonPacket,JsonWrapper> {

	private static Server server;
	
	public Server(){
		  super();
		  controller=new Controller();
		  controller.start();
	}
	
	public static Server getInstance(){
		if(server==null)
			server=new Server();
		server.start();
		return server;
	}
}
