package my.rinat.wsserver.boundary;

import lombok.RequiredArgsConstructor;
import my.rinat.country.gen.GetCountryRequest;
import my.rinat.country.gen.GetCountryResponse;
import my.rinat.wsserver.domain.country.Countries;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class CountryEndpoint {

    private final Countries countries;

    @PayloadRoot(namespace = "http://www.rinat.my/country/gen", localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
        GetCountryResponse response = new GetCountryResponse();
        response.setCountry(countries.findCountry(request.getName()));
        return response;
    }
}