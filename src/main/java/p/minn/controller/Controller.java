package p.minn.controller;

import java.nio.channels.SocketChannel;


import p.minn.client.BaseClient;
import p.minn.client.Client;
import p.minn.controller.BaseController;
import p.minn.handshake.HandShake;
import p.minn.packet.JsonPacket;
import p.minn.packet.JsonWrapper;
import p.minn.utils.BaseConstants;

/**
 * @author minn
 * @QQ:394286006
 * 
 */
public class Controller extends BaseController<JsonPacket,JsonWrapper> {
 
	public Controller() {
		super();
		hsclient=new HandShake(this);
		hsclient.start();
	}

	public void broadcast(int group, JsonPacket packet,int clientid) {
		for (Object client : groups.get(group).values()) {
			if(clientid!=((Client) client).clientId)
			((Client) client).addPacket(packet);
		}

	}


	public void loginClien(int id, BaseClient<JsonPacket,JsonWrapper> obj) {
		  synchronized (newClients) {
			    this.newClients.put(id, obj);
		}
		  logger.info("****************user login************");
		  logger.info("******current login user count:" + currentLoginClients() + "*****");
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
