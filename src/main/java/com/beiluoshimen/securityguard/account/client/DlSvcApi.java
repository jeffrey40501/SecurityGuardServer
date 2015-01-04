package com.beiluoshimen.securityguard.account.client;


import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * 
 * 
 * @author Hsieh Yu-Hua
 * @date Jan 1, 201511:42:23 PM
 */
public interface DlSvcApi {
		
	public static final String DL_SVC_PATH = "/download";
	
	
	public static final String DL_100_PATH = DL_SVC_PATH + "/100.zip";
	public static final String DL_101_PATH = DL_SVC_PATH + "/101.zip";
	public static final String DL_102_PATH = DL_SVC_PATH + "/102.zip";
	public static final String DL_103_PATH = DL_SVC_PATH + "/103.zip";
	
	public static final String DL_FREE_COIN = DL_SVC_PATH + "/freeCoin";
	
	//TODO move market code here.... make code cleaner...

	
}
