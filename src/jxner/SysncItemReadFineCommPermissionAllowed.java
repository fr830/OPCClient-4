package jxner;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executors;

import org.jinterop.dcom.common.JIException;
import org.openscada.opc.lib.common.AlreadyConnectedException;
import org.openscada.opc.lib.common.ConnectionInformation;
import org.openscada.opc.lib.da.Group;
import org.openscada.opc.lib.da.Item;
import org.openscada.opc.lib.da.Server;

public class SysncItemReadFineCommPermissionAllowed {

	public static void main(String[] args) throws IllegalArgumentException, Exception, JIException, AlreadyConnectedException{
		ConnectionInformation ci = new ConnectionInformation();
		ci.setHost("");
		ci.setDomain("");
		ci.setUser("");
		ci.setPassword("");
		//权限允许的情况下，可以progid和clsid2选1或者都写（以vlsid为准）
		ci.setProgId("");
		ci.setClsid("");
		
		Server server = new Server(ci, Executors.newSingleThreadScheduledExecutor());
		
		server.connect();
		
		Group group = server.addGroup();
		Item item = group.addItem("");
		
		Map<String, Item> items = group.addItems("","","","");
		dumpItem(item);
		for(Entry<String, Item> temp : items.entrySet()) {
			dumpItem(temp.getValue());
		}
		server.dispose();

	}
	
	private static void dumpItem(Item item) throws JIException{
		System.out.println("[" + (++count) + "],ItemName:[" + item.getId()
				+ "],value:" + item.read(false).getValue());
	}
	
	private static int count;

}
