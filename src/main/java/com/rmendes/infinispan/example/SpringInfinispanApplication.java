package com.rmendes.infinispan.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringInfinispanApplication {
	
//	@Autowired
//	private RemoteCacheManager cacheManager;
//	
	private static final String CACHE_NAME = "DOCMODEL";

	public static void main(String[] args) {
		SpringApplication.run(SpringInfinispanApplication.class, args);
	}
	
	
//	@Bean
//	public CommandLineRunner createCache(ApplicationContext ctx) {
//		return args ->{
//			cacheManager.administration().getOrCreateCache(CACHE_NAME, "default");
//		};
//	}

}
