package com.rmendes.infinispan.example.api;

import java.io.IOException;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rmendes.infinispan.example.model.DocModel;

@RestController
@RequestMapping("/docmodel")
@EnableCaching
public class FileService {

	@Autowired
	RemoteCacheManager cacheManager;
	

	@GetMapping("/create")
	public String createDocModel(@RequestParam(name = "id") Long id, @RequestParam(name = "name") String name) throws IOException {
		DocModel d = new DocModel();
		d.setId(id);
		d.setName(name);
		RemoteCache<Long, DocModel> cache = cacheManager.administration().getOrCreateCache("DOCMODEL", "default");
		System.out.println(cache);
		cache.put(d.getId(), d);
		return "ok";
	}


	@GetMapping("/retrieve")
	public DocModel retrieveDocModelFromCache(@RequestParam(name = "id") Long id) throws IOException {
		RemoteCache<Long, DocModel> cache = cacheManager.administration().getOrCreateCache("DOCMODEL", "default");
		System.out.println(cache.get(id).toString());
		return cache.get(id);
	}
	
	
//	private RemoteCache<Long, DocModel> getCache(String cacheName) throws IOException{
//		ConfigurationBuilder builder = new ConfigurationBuilder();
//		builder.addServer().host(DG_CLUSTER).port(443)
//		.clientIntelligence(ClientIntelligence.BASIC)
//		.security()
//		.authentication().enable()
//		.username("redhat")
//		.password("redhat")
//		.serverName("datagrid-service")
//		.saslQop(SaslQop.AUTH)
//		.ssl()
//		.sniHostName(DG_CLUSTER)
//		.trustStorePath("src/main/resources/tls.crt");
//		RemoteCacheManager cacheManager= new RemoteCacheManager(builder.build());
//		try {
//			return cacheManager.administration()
//					 .withFlags(CacheContainerAdmin.AdminFlag.PERMANENT).getOrCreateCache(cacheName, "default");
//			//return cacheManager.administration().getOrCreateCache(cacheName, "default");
//		}catch (Exception e) {
//			e.printStackTrace();
//			throw e;
//		}finally {
//			cacheManager.close();
//		}
//
//	}


}
