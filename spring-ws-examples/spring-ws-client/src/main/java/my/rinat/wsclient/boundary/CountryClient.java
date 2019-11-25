package my.rinat.wsclient.boundary;

import my.rinat.country.gen.GetCountryRequest;
import my.rinat.country.gen.GetCountryResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class CountryClient extends WebServiceGatewaySupport {
    public GetCountryResponse getCountry(String country) {
        GetCountryRequest request = new GetCountryRequest();
        request.setName(country);
        return (GetCountryResponse) getWebServiceTemplate().marshalSendAndReceive("http://localhost:8080/ws", request);
    }
}
