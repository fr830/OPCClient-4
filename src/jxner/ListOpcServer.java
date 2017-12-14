package jxner;

import java.net.UnknownHostException;
import java.util.Collection;

import org.jinterop.dcom.common.JIException;
import org.openscada.opc.dcom.list.ClassDetails;
import org.openscada.opc.lib.list.Categories;
import org.openscada.opc.lib.list.Category;
import org.openscada.opc.lib.list.ServerList;

public class ListOpcServer {
	
	public static void main(String[] args) throws IllegalArgumentException,
			UnknownHostException,JIException {
//		ServerList serverList = new ServerList("192.168.168.55","qqgr2","1234","");//中间的力控【首站和部分二级站】数据源设置为2
		ServerList serverList = new ServerList("192.168.168.60","qqgr3","1234","");//左边的力控【二级站】数据源设置为3

//		ServerList serverList = new ServerList("127.0.0.1","","","");

		
		Collection<ClassDetails> classDetails = serverList.
				listServersWithDetails(new Category[] {
					Categories.OPCDAServer10,Categories.OPCDAServer20,
					Categories.OPCDAServer30},new Category[] {});
		
		for(ClassDetails cds : classDetails) {
			System.out.println(cds.getProgId() + "===" + cds.getDescription()+"  |  "+cds.getClsId());
		}	
		
	}

}
