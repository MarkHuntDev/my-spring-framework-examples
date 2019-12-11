package my.rinat.client.domain.country;

import my.rinat.server.domain.country.GetCountryRequest;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.addressing.client.ActionCallback;
import org.springframework.ws.soap.addressing.core.EndpointReference;

import java.net.URI;
import java.net.URISyntaxException;

public class CountryWsClient extends WebServiceGatewaySupport {
    public void getCountry(String name) throws URISyntaxException {
        GetCountryRequest request = new GetCountryRequest();
        request.setName(name);
        var callback = new ActionCallback(new URI("http://www.rinat.my/server/domain/country/getCountry"));
        callback.setReplyTo(new EndpointReference(new URI("http://localhost:8282/ws")));
        getWebServiceTemplate().marshalSendAndReceive("http://localhost:8181/ws", request, callback);
    }
}
