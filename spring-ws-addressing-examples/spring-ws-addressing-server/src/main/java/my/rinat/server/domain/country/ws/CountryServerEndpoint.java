package my.rinat.server.domain.country.ws;

import my.rinat.consumer.domain.country.ConsumeCountryRequest;
import my.rinat.consumer.domain.country.Country;
import my.rinat.consumer.domain.country.Currency;
import my.rinat.server.domain.country.Countries;
import my.rinat.server.domain.country.GetCountryRequest;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.addressing.server.annotation.Action;

@Endpoint
public class CountryServerEndpoint {

    private final Countries countries;

    public CountryServerEndpoint(Countries countries) {
        this.countries = countries;
    }

    @Action("http://www.rinat.my/server/domain/country/getCountry")
    @ResponsePayload
    public ConsumeCountryRequest getCountry(@RequestPayload final GetCountryRequest request) {
        var consumerResponse = new ConsumeCountryRequest();
        consumerResponse.setCountry(convertFrom(this.countries.findCountry(request.getName())));
        return consumerResponse;
    }

    private static Country convertFrom(my.rinat.server.domain.country.Country country) {
        var converted = new Country();
        converted.setName(country.getName());
        converted.setPopulation(country.getPopulation());
        converted.setCapital(country.getCapital());
        converted.setCurrency(Currency.valueOf(country.getCurrency().name()));
        return converted;
    }
}
