package my.rinat.wsclient;

import lombok.extern.slf4j.Slf4j;
import my.rinat.country.gen.GetCountryResponse;
import my.rinat.wsclient.boundary.CountryClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner lookup(CountryClient client) {
        return args -> {
            String country = "Spain";
            if (args.length > 0) {
                country = args[0];
            }
            GetCountryResponse response = client.getCountry(country);
            log.info("{} is the capital of {}", response.getCountry().getCapital(), response.getCountry().getName());
        };
    }
}
