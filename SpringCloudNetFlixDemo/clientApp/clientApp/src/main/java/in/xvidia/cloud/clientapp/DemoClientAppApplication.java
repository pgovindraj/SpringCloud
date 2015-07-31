package in.xvidia.cloud.clientapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class DemoClientAppApplication {

    @RequestMapping("/")
    public String home() {
        return "Hello World";
    }
    public static void main(String[] args) {
        SpringApplication.run(DemoClientAppApplication.class, args);
    }
}
