package com.example.demo;

import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadBalancerConfiguration {
	@Bean
	public ServiceInstanceListSupplier discoveryClientServiceInstanceListSupplier(
			ConfigurableApplicationContext context) {
		System.out.println("Configuring Load balancer to prefer same instance");
		return ServiceInstanceListSupplier.builder().withBlockingDiscoveryClient().withSameInstancePreference()
				.build(context);
	}
	
//	@Bean
//    public ServerList<Server> ribbonServerList() {
//        // return new ConfigurationBasedServerList();
//        StaticServerList<Server> staticServerList = new StaticServerList<>((new Server("localhost", 8001)),
//                new Server("localhost", 8000));
//        return staticServerList;
//    }
}