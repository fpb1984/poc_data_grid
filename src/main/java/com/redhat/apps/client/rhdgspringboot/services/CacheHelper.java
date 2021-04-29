package com.redhat.apps.client.rhdgspringboot.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.io.FileInputStream;

import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class CacheHelper {

	final Logger logger = LoggerFactory.getLogger(CacheHelper.class);
	
	RemoteCacheManager rcm;
	@Value("${hotrod.properties}")
	String propertiesFile;

	RemoteCacheManager getRemoteCacheManager() {
        if (rcm != null) {
            return rcm;
        }
        ConfigurationBuilder b = new ConfigurationBuilder();
        
        Properties p = new Properties();
        try {
        	logger.info(propertiesFile);
        	InputStream input = new FileInputStream(propertiesFile);

            //load a properties file from class path, inside static method
        	
        	logger.info(input.toString());
        	
            p.load(input);
            b.withProperties(p);
            b.addContextInitializers(new com.redhat.apps.client.rhdgspringboot.LibraryInitializerImpl());
        


        } catch (IOException ex2) {
        	 ex2.printStackTrace();
        }
        rcm = new RemoteCacheManager(b.build());
        return rcm;
    }
}