package ro.msg.learning.shop.odata;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.odata.jpa.OdataJpaServlet;

@Configuration
public class OdataConfig {
    @Bean
    public ServletRegistrationBean<OdataJpaServlet> odataServlet(OdataJpaServlet servlet) {
        return new ServletRegistrationBean<>(servlet, "/odata/*");
    }
}
