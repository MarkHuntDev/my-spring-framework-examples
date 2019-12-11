package my.rinat.server.infrastructure.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.WsConfigurationSupport;
import org.springframework.ws.soap.addressing.server.AnnotationActionEndpointMapping;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition;

@Configuration
public class WsConfiguration extends WsConfigurationSupport {

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        var servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    @Bean("country-server-ws")
    public SimpleWsdl11Definition countryServer() {
        return new SimpleWsdl11Definition(new ClassPathResource("wsdl/country-server-ws.wsdl"));
    }

    @Bean
    @Override
    public AnnotationActionEndpointMapping annotationActionEndpointMapping() {
        var mapping = super.annotationActionEndpointMapping();
        mapping.setMessageSender(new HttpComponentsMessageSender());
        return mapping;
    }
}