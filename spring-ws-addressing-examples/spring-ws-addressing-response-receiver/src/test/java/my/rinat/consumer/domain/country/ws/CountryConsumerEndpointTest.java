package my.rinat.consumer.domain.country.ws;

import lombok.SneakyThrows;
import my.rinat.consumer.infrastructure.config.WsConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.xml.transform.StringSource;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.springframework.ws.test.server.RequestCreators.withSoapEnvelope;
import static org.springframework.ws.test.server.ResponseMatchers.noFault;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {WsConfiguration.class, CountryConsumerEndpoint.class})
class CountryConsumerEndpointTest {

    @Autowired
    private ApplicationContext context;

    private MockWebServiceClient client;

    @BeforeEach
    void init() {
        client = MockWebServiceClient.createClient(context);
    }

    @Test
    void should_successfully_execute_request() {
        client
                .sendRequest(withSoapEnvelope(new StringSource(request())))
                .andExpect(noFault());
    }

    @SneakyThrows
    private static String request() {
        return Files.readString(Paths.get("src/test/resources/soap/consumeCountryRequest.xml"));
    }
}