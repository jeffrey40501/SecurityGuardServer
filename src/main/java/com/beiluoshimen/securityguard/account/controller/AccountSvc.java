package com.beiluoshimen.securityguard.account.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beiluoshimen.securityguard.account.client.AccountSvcApi;
import com.beiluoshimen.securityguard.account.repository.Account;
import com.beiluoshimen.securityguard.account.repository.AccountRepository;

/**
 * 
 * This AccountSvc allows clients to send HTTP POST requests to server
 * clients can send HTTP GET, POST, PUT,etc. requests.
 *
 * We have defined a AccountSvcApi that provides
 * strong typing on both the client and service interface to ensure that we
 * don't send the wrong paraemters, etc.
 * 
 * 
 * @author Hsieh Yu-Hua
 * @date Dec 24, 20148:30:31 PM
 */

@Controller
public class AccountSvc{

	@Autowired
	private AccountRepository accounts;
	
	@RequestMapping(value = AccountSvcApi.ACCOUNT_SVC_PATH, method = RequestMethod.GET)
	public @ResponseBody Collection<Account> findByUsernameAndPassword(
			@RequestParam String username,
			@RequestParam String password) {
		return accounts.findByUsernameAndPassword(username, password);
	}

	@RequestMapping(value = AccountSvcApi.ACCOUNT_SVC_PATH, method = RequestMethod.POST)
	public @ResponseBody boolean addAccount(
			@RequestBody Account v) {
		if (!(accounts.findByUsername(v.getUsername())).isEmpty()) {
			//account name already exist.
			return false;
		}
		accounts.save(v);

		return true;
	}
	
	@RequestMapping(value = AccountSvcApi.COIN_PATH, method = RequestMethod.POST)
	public @ResponseBody boolean addCoin(
			@RequestParam String username,
			@RequestParam String password,
			@RequestParam int coin) {
		//if findByUsernameAndPassword does find something, it must be empty or only one entity 
		//(All username sould be unique)
		Collection<Account> collection =accounts.findByUsernameAndPassword(username, password);
		if(!collection.isEmpty()){
			Account a= collection.iterator().next();
			a.setCoin(a.getCoin()+coin);
			accounts.save(a);
			return true;
		}
		return false;
	}
	
	//show all the characters in the market.
	@RequestMapping(value = AccountSvcApi.CHARACTER_PATH, method = RequestMethod.GET)
	public @ResponseBody Collection<Character> getCharacters(){
		return charlist;
	}
	
	
	//use buy the specific character in the market.
	//return true if buying succeed.
	@RequestMapping(value = AccountSvcApi.BUY_PATH, method = RequestMethod.POST)
	public @ResponseBody boolean buyCharacter(
			@RequestParam String username,
			@RequestParam String password,
			@RequestParam int character){
		Collection<Account> collection = accounts.findByUsernameAndPassword(username, password);
		if (!collection.isEmpty()) {
			Account a = collection.iterator().next();
			Integer price= charPricesMap.get(character);
			if (price==null) {
				//no this character
				return false;
			}else if (a.getCoin()-price<0) {
				//coin not enough
				return false;
			}else if (a.getCharacters().contains(character)) {
				//character already exist
				return false;
			}
			a.setCoin(a.getCoin()-price);
			a.getCharacters().add(character);
			accounts.save(a);
			return true;
		}
		//account not found.
		return false;
	}

	
	
	
	//initial the price of each character.
	private static HashMap<Integer, Integer> charPricesMap;
	static{
		charPricesMap = new HashMap<Integer, Integer>();//(character) no v.s. price
		charPricesMap.put(100, 0);
		charPricesMap.put(101, 0);
		charPricesMap.put(102, 0);
		charPricesMap.put(103, 0);
	}
	
	//initial one list , use to return the info query about characters on the market.
	private static ArrayList<Character> charlist;
	static{
		charlist = new ArrayList<Character>();
		charlist.add(new Character("小叮噹",100, 0, "http://i.imgur.com/wwFDtp4.png", "127.0.0.1", "10001"));
		charlist.add(new Character("妮可妮可尼",101, 0, "http://i.imgur.com/X0AZMXG.png", "127.0.0.1", "10002"));
		charlist.add(new Character("測試3",102, 0, "http://i.imgur.com/wFfq4Xm.png", "127.0.0.1", "10003"));
		charlist.add(new Character("測試4",103, 0, "http://i.imgur.com/wwFDtp4.png", "127.0.0.1", "10004"));
		
	}
	
	
}



