package store.zor.referral;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ReferralApp {
    public static void main(String[] args) {
        SpringApplication.run(ReferralApp.class, args);
    }
}
