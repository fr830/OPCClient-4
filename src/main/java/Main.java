import org.jinterop.dcom.common.JIException;
import org.openscada.opc.lib.common.AlreadyConnectedException;
import org.openscada.opc.lib.common.ConnectionInformation;
import org.openscada.opc.lib.da.AddFailedException;
import org.openscada.opc.lib.da.Group;
import org.openscada.opc.lib.da.Item;
import org.openscada.opc.lib.da.Server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.concurrent.Executors;

public class Main {

	public static void main(String[] args) throws IllegalArgumentException, Exception, JIException, AlreadyConnectedException {
		long start = System.currentTimeMillis();
		System.out.println(System.currentTimeMillis() - start);
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;

//		OPC
		ConnectionInformation ci = new ConnectionInformation();
//		中间那台电脑（首战+热力站） 192.168.168.55 qqgr2 1234
//		左面那台电脑（部分热力站）   192.168.168.60 qqgr3 1234
		ci.setHost("192.168.168.55");
		ci.setDomain("");
		ci.setUser("qqgr2");
		ci.setPassword("1234");
		ci.setProgId("PCAuto.OPCServer");
		ci.setClsid("3fb14190-def2-4bba-998a-719c49c74de1");
//		new a group
		Server server = new Server(ci, Executors.newSingleThreadScheduledExecutor());
		server.connect();
//		add items into the new group
		Group group1 = server.addGroup("group1");
		try {
//		    database prepare
			conn = DBManager.getConnection();
			String sql = "select name from test";
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
//			flag for count
			int flag = 0;
			System.out.println(System.currentTimeMillis() - start);
			System.out.println(new Date(System.currentTimeMillis()));
			while (rs.next() && flag < 4000) {
				try {
					Item item = group1.addItem(rs.getString("name") + ".PV");
					flag++;
                    System.out.println(new Date(System.currentTimeMillis()));
					System.out.println("NO." + flag++ + "  ItemName:" + item.getId() + ",value:" + item.read(false).getValue());
				} catch (AddFailedException e) {
				}
			}
			System.out.println(new Date(System.currentTimeMillis()));
			System.out.println(System.currentTimeMillis() - start);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.closeAll(conn, st, rs);
		}
		server.dispose();

	}
}
