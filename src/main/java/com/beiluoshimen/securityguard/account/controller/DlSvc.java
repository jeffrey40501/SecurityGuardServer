package com.beiluoshimen.securityguard.account.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beiluoshimen.securityguard.account.client.DlSvcApi;


@Controller
public class DlSvc {


	@RequestMapping(value = DlSvcApi.DL_SVC_PATH, method = RequestMethod.GET)
	public @ResponseBody DlData dlChatacter(
			@RequestParam int no){
		//we can check if this account has "really bought "this character if 
	//we require the user to submit their username
		
		switch (no) {
		case 100:
			
			return data100;

		case 101:
			
			return data101;
		case 102:
			
			return data102;
		case 103:
			
			return data103;
			
		default:
			//no this character.
			return null;
		}
		
	}

	//In-memory data.
	private static final int no100 = 100;
	private static final int no101 = 101;
	private static final int no102 = 102;
	private static final int no103 = 103;
	
	private static final int size100 = 4255597;
	private static final int size101 = 10000;
	private static final int size102 = 10000;
	private static final int size103 = 10000;

	private static DlData data100;
	private static DlData data101;
	private static DlData data102;
	private static DlData data103;
	
//	private static final File file100 = new File("/Users/Andy/Documents/workspace/SecurityGuardServer/assets/haru.zip");
//	private static final File file101 = new File("/Users/Andy/Documents/workspace/SecurityGuardServer/assets/");
//	private static final File file102 = new File("/Users/Andy/Documents/workspace/SecurityGuardServer/assets/");
//	private static final File file103 = new File("/Users/Andy/Documents/workspace/SecurityGuardServer/assets/");
//
//    private static DataInputStream dis = null;
//	
	//In-memory data.
	static{		
		
//		try {
//			dis = new DataInputStream(new BufferedInputStream(new FileInputStream(file100)));
//			while (dis.available() != 0) {
//				int a;
//				a = dis.read(byte100, 0, size100);
//				System.out.println("READ byte :"+a);
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

		data100 = new DlData(size100, no100, "https://doc-0c-84-docs.googleusercontent.com/docs/securesc/ha0ro937gcuc7l7deffksulhg5h7mbp1/tar6ffhen4esvqtfl67qcduotr6rb7vu/1420120800000/11249192677254634747/*/0B4FzP_2MdAZuWURsNWtLaGxabjA?e=download");
		data101 = new DlData(size101, no101, "");
		data102 = new DlData(size102, no102, "");
		data103 = new DlData(size103, no103, "");	
	}
	
	
}
