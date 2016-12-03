package p.minn.controller;

import java.nio.channels.SocketChannel;

import p.minn.client.Client;
import p.minn.controller.BaseController;
import p.minn.handshake.WebSocketHandShake;
import p.minn.packet.websocket.JsonPacket;
import p.minn.packet.websocket.JsonWrapper;
import p.minn.utils.BaseConstants;

/**
 * @author minn
 * @QQ:394286006
 * 
 */
public class Controller extends BaseController<JsonPacket,JsonWrapper> {
 
	public Controller() {
		super();
		hsclient=new WebSocketHandShake(this);
		hsclient.start();
	}

	public void broadcast(int group, JsonPacket packet,int clientid) throws Exception {
		for (Object client : groups.get(group).values()) {
			if(clientid!=((Client) client).clientId)
			((Client) client).addPacket(packet);
		}

	}

	public void rejectlogin(Client client) {
	  logger.info("login one group number:" + BaseConstants.ONE_GROUP_NUMBER + "!");
	}

	public Client createClient(String uuid,SocketChannel socket) {
		int id = getClientId();
		Client client = new Client(uuid,socket, this, id);
		return client;
	}

}
