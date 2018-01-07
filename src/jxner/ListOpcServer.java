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
//		ServerList serverList = new ServerList("192.168.168.55","qqgr2","1234","");//�м�����ء���վ�Ͳ��ֶ���վ������Դ����Ϊ2
		ServerList serverList = new ServerList("192.168.168.60","qqgr3","1234","");//��ߵ����ء�����վ������Դ����Ϊ3

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
