package my.rinat.consumer.domain.country.ws;

import lombok.extern.slf4j.Slf4j;
import my.rinat.consumer.domain.country.ConsumeCountryRequest;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.addressing.server.annotation.Action;

@Slf4j
@Endpoint
public class CountryConsumerEndpoint {
    @Action("http://www.rinat.my/server/domain/country/getCountryResponse")
    @ResponsePayload
    public void consumeCountry(@RequestPayload final ConsumeCountryRequest request) {
        var country = request.getCountry();
        log.info("Country {} with the capital {}, population {}, and currency {}", country.getName(),
                country.getCapital(), country.getPopulation(), country.getCurrency());
    }
}
