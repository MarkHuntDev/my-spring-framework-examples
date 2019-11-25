package my.rinat.wsserver.boundary;

import lombok.SneakyThrows;
import my.rinat.wsserver.domain.country.Countries;
import my.rinat.wsserver.infrastructure.WsConfiguration;
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
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.springframework.ws.test.server.RequestCreators.withPayload;
import static org.springframework.ws.test.server.ResponseMatchers.payload;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {WsConfiguration.class, CountryEndpoint.class, Countries.class})
class CountryEndpointTest {

    @Autowired
    private ApplicationContext applicationContext;

    private MockWebServiceClient mockClient;

    @BeforeEach
    void init() {
        mockClient = MockWebServiceClient.createClient(applicationContext);
    }

    @Test
    void testEndpoint() {
        mockClient
                .sendRequest(withPayload(sourceOf("getCountryRequest.xml")))
                .andExpect(payload(sourceOf("getCountryResponse.xml")));
    }

    @SneakyThrows
    private static Source sourceOf(String filename) {
        return new StringSource(readString(filename));
    }

    private static String readString(String pathToFile) throws IOException {
        return Files.readString(Paths.get("src/test/resources/xml/" + pathToFile));
    }
}