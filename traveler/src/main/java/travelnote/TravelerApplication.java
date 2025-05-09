package travelnote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TravelerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravelerApplication.class, args);
    }
}
