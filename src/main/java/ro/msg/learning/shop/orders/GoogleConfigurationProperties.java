package ro.msg.learning.shop.orders;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "google")
@Getter
@Setter
public class GoogleConfigurationProperties {
    private String uri;
    private String key;
}
