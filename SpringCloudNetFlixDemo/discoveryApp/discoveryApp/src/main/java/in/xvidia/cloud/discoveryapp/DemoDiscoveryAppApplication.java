package in.xvidia.cloud.discoveryapp;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class DemoDiscoveryAppApplication implements CommandLineRunner {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalancerClient loadBalancer;

    private RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        SpringApplication.run(DemoDiscoveryAppApplication.class, args);
    }


    @Override
    public void run(String... strings) throws Exception {
        discoveryClient.getInstances("demoApp").forEach((ServiceInstance s) -> {
            System.out.println("----- "+ ToStringBuilder.reflectionToString(s));
            System.out.println("--HOST--- "+ s.getHost());
            System.out.println("--PORT--- "+ s.getPort());
            System.out.println("--URI--- "+ s.getUri());

            ServiceInstance s1 = loadBalancer.choose("demoApp");
            ResponseEntity<String> resultStr =restTemplate.getForEntity(s1.getUri(),String.class);
            System.out.println("****** " + resultStr.getBody());


        });
    }
}
