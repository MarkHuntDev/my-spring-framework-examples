package my.rinat.client.infrastructure.config;

import my.rinat.client.domain.country.CountryWsClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class WsClientConfig {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("my.rinat.server.domain.country");
        return marshaller;
    }

    @Bean
    public CountryWsClient countryWsClient(Jaxb2Marshaller marshaller) {
        CountryWsClient client = new CountryWsClient();
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
