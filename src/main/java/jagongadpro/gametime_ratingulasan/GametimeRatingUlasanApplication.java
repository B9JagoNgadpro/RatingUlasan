package jagongadpro.gametime_ratingulasan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class GametimeRatingUlasanApplication {

    public static void main(String[] args) { SpringApplication.run(GametimeRatingUlasanApplication.class, args);}

}
