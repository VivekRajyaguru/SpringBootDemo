package com.example.demo;

import java.util.Arrays;

import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringbootApplication implements CommandLineRunner{
	
	@Autowired
	private ApplicationContext applicationContext;
	
	private Logger logger = LoggerFactory.getLogger(SpringApplication.class);
	
	private int maxUploadSizeinMB = 50 * 1024 *1024; // 50MB

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}
	
	@Bean
	public TomcatEmbeddedServletContainerFactory embeddedTomcat() {
		TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
			factory.addConnectorCustomizers((TomcatConnectorCustomizer) connector -> {
				if(connector.getProtocolHandler() instanceof AbstractHttp11Protocol<?>) {
					((AbstractHttp11Protocol<?>) connector.getProtocolHandler()).setMaxSwallowSize(-1);
				}
			}
		);
		return factory;
	}

	// to log all beans loaded by Application help to debug
	
	@Override
	public void run(String... arg0) throws Exception {
		String[] beansAry = applicationContext.getBeanDefinitionNames();
		Arrays.sort(beansAry);
		for(String name: beansAry) {
			logger.info(name);
		}
		
	}
}
