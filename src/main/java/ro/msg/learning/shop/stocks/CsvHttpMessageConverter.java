package ro.msg.learning.shop.stocks;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class CsvHttpMessageConverter<T> extends AbstractGenericHttpMessageConverter<List<T>> {
    private final CsvHelper csvHelper;

    public CsvHttpMessageConverter(CsvHelper csvHelper) {
        super(new MediaType("text", "csv"));
        this.csvHelper = csvHelper;
    }

    @Override
    protected void writeInternal(@NotNull List<T> objects, Type type, @NotNull HttpOutputMessage httpOutputMessage) throws IOException {
        Class<T> myClass = toBeanType(type);
        csvHelper.toCsv(myClass, objects, httpOutputMessage.getBody());
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return List.class.isAssignableFrom(clazz);
    }

    @Override
    protected List<T> readInternal(Class<? extends List<T>> clazz, HttpInputMessage inputMessage) throws IOException {
        Class<T> myClass = toBeanType(clazz.getGenericSuperclass());
        return csvHelper.fromCsv(myClass, inputMessage.getBody());
    }

    @Override
    public List<T> read(Type type, Class<?> clazz, HttpInputMessage inputMessage) throws IOException {
        Class<T> myClass = toBeanType(clazz.getGenericSuperclass());
        return csvHelper.fromCsv(myClass, inputMessage.getBody());
    }

    @SuppressWarnings("unchecked")
    private Class<T> toBeanType(Type type) {
        return (Class<T>) ((ParameterizedType) type).getActualTypeArguments()[0];
    }

}
