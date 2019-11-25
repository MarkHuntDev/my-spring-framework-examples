package my.rinat.wsclient.boundary;

import lombok.SneakyThrows;
import my.rinat.country.gen.Currency;
import my.rinat.wsclient.infrastructure.ClientConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ws.test.client.MockWebServiceServer;
import org.springframework.xml.transform.StringSource;

import javax.xml.transform.Source;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.ws.test.client.RequestMatchers.payload;
import static org.springframework.ws.test.client.ResponseCreators.withPayload;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ClientConfiguration.class)
class CountryClientTest {

    @Autowired
    private CountryClient client;

    private MockWebServiceServer mockServer;

    @BeforeEach
    void setUp() {
        mockServer = MockWebServiceServer.createServer(client);
    }

    @Test
    void testClient() {
        mockServer
                .expect(payload(sourceOf("getCountryRequest.xml")))
                .andRespond(withPayload(sourceOf("getCountryResponse.xml")));

        var response = client.getCountry("Poland");

        assertThat(response.getCountry().getName()).isEqualTo("Poland");
        assertThat(response.getCountry().getPopulation()).isEqualTo(38186860);
        assertThat(response.getCountry().getCapital()).isEqualTo("Warsaw");
        assertThat(response.getCountry().getCurrency()).isEqualTo(Currency.PLN);

        mockServer.verify();
    }

    @SneakyThrows
    private static Source sourceOf(String filename) {
        return new StringSource(readString(filename));
    }

    private static String readString(String pathToFile) throws IOException {
        return Files.readString(Paths.get("src/test/resources/xml/" + pathToFile));
    }
}