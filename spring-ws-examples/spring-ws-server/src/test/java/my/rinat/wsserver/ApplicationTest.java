package my.rinat.wsserver;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTest {

    @Autowired
    private ConfigurableApplicationContext context;

    @Test
    void testApplication() {
        Assertions.assertTrue(context.isActive());
    }
}