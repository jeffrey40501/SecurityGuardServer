package com.beiluoshimen.securityguard.account.client;


import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Query;

import com.beiluoshimen.securityguard.account.controller.DlData;
/**
 * 
 * 
 * @author Hsieh Yu-Hua
 * @date Jan 1, 201511:42:23 PM
 */
public interface DlSvcApi {
	
	public static final String NO_PARAMETER = "no";
	
	public static final String DL_SVC_PATH = "/download";

	@GET(DL_SVC_PATH)
	public DlData dlCharacter(
			@Query(NO_PARAMETER) int no);
	
}
