package com.beiluoshimen.securityguard.account;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.savedrequest.NullRequestCache;

import com.beiluoshimen.securityguard.account.client.AccountSvcApi;

//Tell Spgin this is an configuration, too.
@Configuration
//This will setup "Spring Security" to ""intercept"" incoming requests to the Controllers
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//	onAuthenticationSuccess() method is invoked whenever a client successfully logs in,
//	we set the response status as HTTP 200 OK,which let client knows they logged in correctly.
//	we do not redirect clitent like the "default handler" does with a HTTP 302 response. 
//	(we don't redirect the client in order to be more friendlier to mobile clients and Retrofit.)
	private static final AuthenticationSuccessHandler NO_REDIRECT_SUCCESS_HANDLER = new AuthenticationSuccessHandler() {
		@Override
		public void onAuthenticationSuccess(HttpServletRequest request,HttpServletResponse response, Authentication authentication) 
				throws IOException, ServletException {
			response.setStatus(HttpStatus.SC_OK);
		}
	};
	
	// Normally, the logout success handler redirects the client to the login page. We
	// just want to let the client know that it succcessfully logged out and make the
	// response a bit of JSON so that Retrofit can handle it. The handler sends back
	// a 200 OK response and an empty JSON object.
	private static final LogoutSuccessHandler JSON_LOGOUT_SUCCESS_HANDLER = new LogoutSuccessHandler() {
		@Override
		public void onLogoutSuccess(HttpServletRequest request,
				HttpServletResponse response, Authentication authentication)
				throws IOException, ServletException {
			response.setStatus(HttpStatus.SC_OK);
			response.setContentType("application/json");
			response.getWriter().write("{}");
		}
	};
	
	/**
	 * This method is used to inject access control policies into Spring
	 * security to control what resources / paths / http methods clients have
	 * access to.
	 */
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		// By default, Spring inserts a token into web pages to prevent
		// cross-site request forgery attacks. 
		// See: http://en.wikipedia.org/wiki/Cross-site_request_forgery
		//
		// Unfortunately, there is no easy way with the default setup to communicate 
		// these CSRF tokens to a mobile client so we disable them.
		// Don't worry, the next iteration of the example will fix this
		// problem.
		http.csrf().disable();
		// We don't want to cache requests during login
		http.requestCache().requestCache(new NullRequestCache());
		
		// Allow all clients to access the login page and use
		// it to login
		http.formLogin()
			// The default login url on Spring is "j_security_check" ...
		    // which isn't very friendly. We change the login url to
		    // something more reasonable ("/login").
			.loginProcessingUrl(AccountSvcApi.LOGIN_PATH)
			.successHandler(NO_REDIRECT_SUCCESS_HANDLER)
			// Allow everyone to access the login URL
			.permitAll();
		
		// Make sure that clients can logout too!!
		http.logout()
		    // Change the default logout path to /logout
			.logoutUrl(AccountSvcApi.LOGOUT_PATH)
			// Make sure that a redirect is not sent to the client
			// on logout
			.logoutSuccessHandler(JSON_LOGOUT_SUCCESS_HANDLER)
			// Allow everyone to access the logout URL
			.permitAll();
		
		
		//example :
		// Require clients to login and have an account with the "user" role
		// in order to access /acoount
		// http.authorizeRequests().antMatchers("/acoount").hasRole("user");
		
		// Require clients to login and have an account with the "user" role
		// in order to send a POST request to /account
		// http.authorizeRequests().antMatchers(HttpMethod.POST, "/acccount").hasRole("user");
		
		
		//get market models data
		http.authorizeRequests().antMatchers(AccountSvcApi.CHARACTER_PATH).permitAll();
		//register
		http.authorizeRequests().antMatchers(AccountSvcApi.ACCOUNT_SVC_PATH).permitAll();
		
		//the following method must be login first .
		//add coin.
		http.authorizeRequests().antMatchers(AccountSvcApi.COIN_PATH).authenticated();
		//buy character.
		http.authorizeRequests().antMatchers(AccountSvcApi.BUY_PATH).authenticated();
				
		
		
//		http.authorizeRequests().anyRequest().authenticated();
	}

	

	
	/**
	 * 
	 * This method is used to setup the users that will be able to login to the
	 * system. This is a VERY insecure setup that is using two hardcoded users /
	 * passwords and should never be used for anything other than local testing
	 * on a machine that is not accessible via the Internet. Even if you use
	 * this code for testing, at the bare minimum, you should change the
	 * passwords listed below.
	 * 
	 * @param auth
	 * @throws Exception
	 */
		
	@Autowired
	@Qualifier("userDetailsService")
    private LoginService loginService;
	
	@Autowired
	protected void registerAuthentication(
			final AuthenticationManagerBuilder auth) throws Exception {
		//we need Mongo Implementation
		
		//mongodb
		auth.userDetailsService(loginService);
 
		
		//jDBC usage...
//		auth //Builder Design Pattern  
//        .jdbcAuthentication().dataSource(dataSource)   
//        .usersByUsernameQuery("select username,password, enabled from account where username=?")  
//        .authoritiesByUsernameQuery("select username, authority from authorities where username=?");
		
		
		//In memory usage..
//		auth.inMemoryAuthentication()
//				.withUser("test2")
//				.password("123")
//				.authorities("admin","user")
//				.and()
//				.withUser("student")
//				.password("changeit")
//				.authorities("user");
	}
	


}
