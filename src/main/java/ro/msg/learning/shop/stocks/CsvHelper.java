package ro.msg.learning.shop.stocks;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class CsvHelper {
    public <T> List<T> fromCsv(Class<T> aClass, InputStream inputStream) throws IOException {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(aClass);
        MappingIterator<T> it = mapper.readerFor(aClass).with(schema).readValues(inputStream);

        return it.readAll();
    }

    public <T> void toCsv(Class<T> aClass, List<T> objects, OutputStream outputStream) throws IOException {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(aClass);
        new CsvMapper().writerFor(aClass).with(schema).writeValues(outputStream).writeAll(objects);
    }
}
