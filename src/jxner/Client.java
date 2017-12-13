package jxner;

import org.jinterop.dcom.common.JIException;
import org.openscada.opc.lib.common.ConnectionInformation;
import org.openscada.opc.lib.da.AccessBase;
import org.openscada.opc.lib.da.Server;
import org.openscada.opc.lib.da.SyncAccess;

import java.util.concurrent.Executors;

public class Client {

    public static void main(String[] args) throws Exception {
        // create connection information
        final ConnectionInformation ci = new ConnectionInformation();
        ci.setHost("127.0.0.1");//host
        ci.setDomain("");//domain
        ci.setUser("");
        ci.setPassword("");
        ci.setProgId("PCAuto.OPCServer");
        ci.setClsid("3FB14190-DEF2-4bba-998A-719C49C74DE1"); // if ProgId is not working, try it using the Clsid instead
        final String itemId = "P21_JJT.PV";//items
        // create a new server
        final Server server = new Server(ci, Executors.newSingleThreadScheduledExecutor());

        try {
            // connect to server
            server.connect();
            // add sync access, poll every 500 ms
            final AccessBase access = new SyncAccess(server, 500);
            access.addItem(itemId, (item, state) ->
                    System.out.println("Result: " + state.toString()));
            // start reading
            access.bind();
            // wait a little bit
//            Thread.sleep(10 * 1000);
            Thread.sleep(10 * 1000);
            // stop reading
            access.unbind();
        } catch (final JIException e) {
            System.out.println(String.format("%08X: %s", e.getErrorCode(), server.getErrorMessage(e.getErrorCode())));
            e.printStackTrace();
        }
    }
}