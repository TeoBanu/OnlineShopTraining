package ro.msg.learning.shop;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ro.msg.learning.shop.stocks.CsvHelper;
import ro.msg.learning.shop.stocks.CsvHttpMessageConverter;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void extendMessageConverters (List<HttpMessageConverter<?>> converters) {
        converters.add(new CsvHttpMessageConverter<>(new CsvHelper()));
    }
}
