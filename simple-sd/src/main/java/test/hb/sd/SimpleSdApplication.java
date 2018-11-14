package test.hb.sd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SimpleSdApplication {
    public static void main(String[] args) {
        SpringApplication.run(SimpleSdApplication.class, args);
    }
}
