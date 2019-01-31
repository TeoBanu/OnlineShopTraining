package ro.msg.learning.shop.stocks;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;

import java.io.IOException;
import java.lang.reflect.Type;

public class CsvMessageConverter extends AbstractGenericHttpMessageConverter {
    @Override
    protected void writeInternal(Object o, Type type, HttpOutputMessage httpOutputMessage) throws IOException {
        throw new UnsupportedOperationException("not yet implemented");
    }

    @Override
    protected Object readInternal(Class aClass, HttpInputMessage httpInputMessage) throws IOException {
        return null;
    }

    @Override
    public Object read(Type type, Class aClass, HttpInputMessage httpInputMessage) throws IOException {
        return null;
    }
}
