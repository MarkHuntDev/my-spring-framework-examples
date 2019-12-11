package my.rinat.client.infrastructure.test;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.rinat.client.domain.country.CountryWsClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

@Slf4j
@RequiredArgsConstructor
@RestController
public class KickController {

    private final CountryWsClient client;

    @RequestMapping("/kick")
    public void kick() throws URISyntaxException {
        String name = "Poland";
        client.getCountry(name);
    }
}
