package jxner;

import org.jinterop.dcom.common.JIException;
import org.openscada.opc.dcom.list.ClassDetails;
import org.openscada.opc.lib.common.ConnectionInformation;
import org.openscada.opc.lib.da.AccessBase;
import org.openscada.opc.lib.da.Server;
import org.openscada.opc.lib.da.SyncAccess;
import org.openscada.opc.lib.list.Categories;
import org.openscada.opc.lib.list.Category;
import org.openscada.opc.lib.list.ServerList;

import java.net.UnknownHostException;
import java.util.Collection;
import java.util.concurrent.Executors;

public class Client {
	private static String host = "192.168.168.55";  
	private static String domain = "";  
	private static String progId = "";  
	private static String user = "qqgr2";  
	private static String password = "1234"; 
	protected static void showAllOPCServer(ServerList serverList)   
	        throws JIException, IllegalArgumentException, UnknownHostException {  
	      
	    final Collection<ClassDetails> detailsList = serverList.listServersWithDetails (  
	            new Category[] { Categories.OPCDAServer20 }, new Category[] {} );  
	      
	    for ( final ClassDetails details : detailsList )  
	    {  
	        System.out.println ( String.format ( "Found: %s", details.getClsId () ) );  
	        System.out.println ( String.format ( "\tProgID: %s", details.getProgId () ) );  
	        System.out.println ( String.format ( "\tDescription: %s", details.getDescription () ) );  
	    }  
	}  
    public static void main(String[] args) throws Exception {
    	
    	
    	ServerList serverList = new ServerList(host,user,password,domain);  
    	showAllOPCServer(serverList); 
    	
    	
    	
    	
//        // create connection information
//        final ConnectionInformation ci = new ConnectionInformation();
//        ci.setHost("192.168.168.55");//host
//        ci.setDomain("");//domain
//        ci.setUser("qqgr2");
//        ci.setPassword("1234");
////        ci.setProgId("PCAuto.OPCServer");
//        ci.setClsid("3fb14190-def2-4bba-998a-719c49c74de1"); // if ProgId is not working, try it using the Clsid instead
//        final String itemId = "T11_LGDQ";//items
//        // create a new server
//        final Server server = new Server(ci, Executors.newSingleThreadScheduledExecutor());
//
//        try {
//            // connect to server
//            server.connect();
//            server.getFlatBrowser();
//            // add sync access, poll every 500 ms
//            final AccessBase access = new SyncAccess(server, 500);
//            access.addItem(itemId, (item, state) ->
//                    System.out.println("Result: " + state.toString()));
//            // start reading
//            access.bind();
//            // wait a little bit
////            Thread.sleep(10 * 1000);
//            Thread.sleep(10 * 1000);
//            // stop reading
//            access.unbind();
//        } catch (final JIException e) {
//            System.out.println(String.format("%08X: %s", e.getErrorCode(), server.getErrorMessage(e.getErrorCode())));
//            e.printStackTrace();
//        }
    }
}