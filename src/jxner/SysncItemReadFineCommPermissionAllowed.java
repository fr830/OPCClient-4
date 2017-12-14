package jxner;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executors;

import org.jinterop.dcom.common.JIException;
import org.jinterop.dcom.core.JIVariant;
import org.openscada.opc.lib.common.AlreadyConnectedException;
import org.openscada.opc.lib.common.ConnectionInformation;
import org.openscada.opc.lib.da.Group;
import org.openscada.opc.lib.da.Item;
import org.openscada.opc.lib.da.Server;

public class SysncItemReadFineCommPermissionAllowed {

	public static void main(String[] args) throws IllegalArgumentException, Exception, JIException, AlreadyConnectedException{
		ConnectionInformation ci = new ConnectionInformation();
		ci.setHost("192.168.168.60");
//		ci.setHost("192.168.168.55");
		ci.setDomain("");
		ci.setUser("qqgr3");
//		ci.setUser("qqgr2");
		ci.setPassword("1234");
		//权限允许的情况下，可以progid和clsid2选1或者都写（以vlsid为准）
		ci.setProgId("PCAuto.OPCServer");
		ci.setClsid("3fb14190-def2-4bba-998a-719c49c74de1");
//		ci.setClsid("");
		
		Server server = new Server(ci, Executors.newSingleThreadScheduledExecutor());
		
		server.connect();
		
		Group group = server.addGroup();
//		Item item = group.addItem("CV1_FQDQ.PV");
		
//		Map<String, Item> items = group.addItems("CV1_HTZQ.PV","CV1_HTGQ.PV");
		Map<String, Item> items = group.addItems("CV1_HTZQ.PV");

//		dumpItem(item);
		for(Entry<String, Item> temp : items.entrySet()) {
			dumpItem(temp.getValue());
		}
		server.dispose();

	}
	
	private static void dumpItem(Item item) throws JIException{
		System.out.println("L1--> " + "[" + (++count) + "],ItemName:[" + item.getId()
				+ "],value:" + item.read(false).getValue());
		JIVariant value = JIVariant.makeVariant(new Integer(62));
		item.write(value);
		System.out.println("L2--> " + "[" + (++count) + "],ItemName:[" + item.getId()
		+ "],value:" + item.read(false).getValue());
	}
	
	private static int count;

}
