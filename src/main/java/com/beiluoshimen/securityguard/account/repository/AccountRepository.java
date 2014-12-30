package com.beiluoshimen.securityguard.account.repository;

import java.math.BigInteger;
import java.util.Collection;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.beiluoshimen.securityguard.account.client.AccountSvcApi;

/**
 * 
 * An interface for a repository that can store users' accounts
 * @author Hsieh Yu-Hua
 * @date Dec 23, 201410:33:50 PM
 */

//@RepositoryRestResource annotation tells Spring Data Rest to
//expose the AccountRepository through a controller and map it to the  xxxxx
//(path= xxxxxx). 
//Warning: This automatically enables you to do the following:
//1. List all videos by sending a GET request to /video 
//2. Add a video by sending a POST request to /video with the JSON for a video
//3. Get a specific video by sending a GET request to /video/{videoId}
// (e.g., /video/1 would return the JSON for the video with id=1)
//4. Send search requests to our findByXYZ methods to /video/search/findByXYZ
// (e.g., /video/search/findByName?title=Foo)
//
//@RepositoryRestResource(path = AccountSvcApi.ACCOUNT_SVC_PATH)
@Repository
public interface AccountRepository extends MongoRepository<Account, BigInteger>{

	// Find our account...ok
	public Collection<Account> findByUsernameAndPassword(
			// The @Param annotation tells Spring Data Rest which HTTP request
			// parameter it should use to fill in the "title" variable used to
			// search for Accounts.
			@Param(AccountSvcApi.USERNAME_PARAMETER) String username,
			@Param(AccountSvcApi.PASSWORD_PARAMETER) String password
			);
	
	public Collection<Account> findByUsername(
			@Param(AccountSvcApi.USERNAME_PARAMETER) String username);
	
	
	 @Query("update Account a set a.coin = :coin where a.username = :username and a.password = :password") 
	 public int setCoin(
			 @Param(AccountSvcApi.COIN_PARAMETER)int after,
			 @Param(AccountSvcApi.USERNAME_PARAMETER) String username,
			 @Param(AccountSvcApi.PASSWORD_PARAMETER) String password); 
	
}

