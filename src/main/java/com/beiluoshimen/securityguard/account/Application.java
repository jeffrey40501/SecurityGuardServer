package com.beiluoshimen.securityguard.account;

import java.io.File;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.beiluoshimen.securityguard.account.json.ResourcesMapper;

/**
 * Spring Rest Architecture is so clear that there is no need to explain too much...
 * Server is mainly based on Spring dispatcherServlet, 
 * which auto correctly route every request to the right corresponding servlet.
 * For more details, see the comments for each "@Annotate".
 * 
 * 至於API接口請參考AccountSvcApi 提供給Retrofit - client端用的
 * @author Hsieh Yu-Hua
 * @date Dec 23, 20149:57:26 PM
 */
//Tell Spring to auto inject any dependency (marked as @AutoWired)
@EnableAutoConfiguration

//Tell Spring to automatically create a MongoDB implementation of our repository
@EnableMongoRepositories

//Tell Spring to turn on WebMVC -- enable the DispatcherServlet
//so that requests can be routed to our Controllers!!!!
//this put our server against SQL injection. (type check!)
@EnableWebMvc
//Tell Spring this class is a configuration class.
@Configuration

//Tell Spring to scan our controller package and sub-packages to find all controllers 
//and other components that are part of our app server.
//Remark:: class that annotated with @Controller is going to be discovered and connected to 
//our dispatcherServlet.
@ComponentScan

//Because we use "List<integer>" in our Account Class ,the json can't understand it.
//if we don't add this, we will get 
//"Jackson with JSON: Unrecognized field, not marked as ignorable" ERROR
@JsonIgnoreProperties(ignoreUnknown = true)

//Include our SecurityConfiguration as part of this configuration 
//so that we can have "HTTPS security" setup by Spring
@Import(SecurityConfiguration.class)
public class Application extends RepositoryRestMvcConfiguration {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	//why we override this ? for more details, see "ResourcesMapper".
	@Override
	public ObjectMapper halObjectMapper(){
		return new ResourcesMapper();
	}
	
//	   This version uses the Tomcat web container and configures it to
//		 support HTTPS. The code below performs the configuration of Tomcat
//		 for HTTPS. Each web container has a different API for configuring
//		 HTTPS. 
//		
//		 The app now requires that you pass the location of the keystore and
//		 the password for your private key that you would like to setup HTTPS
//		 with. In Eclipse, you can set these options by going to:
//		    1. Run->Run Configurations
//		    2. Under Java Applications, select your run configuration for this app
//		    3. Open the Arguments tab
//		    4. In VM Arguments, provide the following information to use the
//		       default keystore provided with the sample code:
//		
//		       -Dkeystore.file=src/main/resources/private/keystore -Dkeystore.pass=changeit
//		
//		    5. Note, this keystore is highly insecure! If you want more securtiy, you 
//		       should obtain a real SSL certificate:
//		
		//       http://tomcat.apache.org/tomcat-7.0-doc/ssl-howto.html
		//
		@Bean
		EmbeddedServletContainerCustomizer containerCustomizer(
				@Value("${keystore.file}") String keystoreFile,
				@Value("${keystore.pass}") final String keystorePass)
				throws Exception {

			
			// This is boiler plate code to setup https on embedded Tomcat
			// with Spring Boot:
			
			final String absoluteKeystoreFile = new File(keystoreFile)
					.getAbsolutePath();

			return new EmbeddedServletContainerCustomizer() {
				@Override
				public void customize(ConfigurableEmbeddedServletContainer container) {
					TomcatEmbeddedServletContainerFactory tomcat = (TomcatEmbeddedServletContainerFactory) container;
					tomcat.addConnectorCustomizers(new TomcatConnectorCustomizer() {

						@Override
						public void customize(Connector connector) {
							connector.setPort(8443);
							connector.setSecure(true);
							connector.setScheme("https");

							Http11NioProtocol proto = (Http11NioProtocol) connector
									.getProtocolHandler();
							proto.setSSLEnabled(true);
							
							// If you update the keystore, you need to change
							// these parameters to match the keystore that you generate
							proto.setKeystoreFile(absoluteKeystoreFile);
							proto.setKeystorePass(keystorePass);
							proto.setKeystoreType("JKS");
							proto.setKeyAlias("tomcat");

						}
					});
				}

			};
		}
		
	 
		

}
