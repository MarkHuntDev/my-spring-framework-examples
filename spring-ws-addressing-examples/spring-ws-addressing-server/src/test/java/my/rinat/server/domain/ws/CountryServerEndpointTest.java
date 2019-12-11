package my.rinat.server.domain.ws;

import lombok.SneakyThrows;
import my.rinat.server.domain.country.Countries;
import my.rinat.server.domain.country.ws.CountryServerEndpoint;
import my.rinat.server.infrastructure.config.WsConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.xml.transform.StringSource;

import javax.xml.transform.Source;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.springframework.ws.test.server.RequestCreators.withSoapEnvelope;
import static org.springframework.ws.test.server.ResponseMatchers.payload;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {WsConfiguration.class, CountryServerEndpoint.class, Countries.class})
class CountryServerEndpointTest {

    @Autowired
    private ApplicationContext applicationContext;

    private MockWebServiceClient mockClient;

    @BeforeEach
    void init() {
        mockClient = MockWebServiceClient.createClient(applicationContext);
    }

    @Test
    void should_successfully_execute_request() {
        mockClient
                .sendRequest(withSoapEnvelope(sourceOf("getCountryRequest.xml")))
                .andExpect(payload(sourceOf("consumeCountryRequest.xml")));
    }

    private static Source sourceOf(String filename) {
        return new StringSource(readString(filename));
    }

    @SneakyThrows
    private static String readString(String pathToFile) {
        return Files.readString(Paths.get("src/test/resources/soap/" + pathToFile));
    }
}