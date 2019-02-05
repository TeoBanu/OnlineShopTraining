package ro.msg.learning.shop.stocks;

import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Test;
import ro.msg.learning.shop.datamodels.Stock;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;

public class CsvHelperTest {
    @Test
    public void toCsvTest() throws IOException {
        List<Stock> stocks = new ArrayList<>();

        Stock stock1 = new Stock();
        stock1.setQuantity(4);
        stock1.setId(new Stock.StockId(1, 2));
        stocks.add(stock1);

        Stock stock2 = new Stock();
        stock2.setQuantity(3);
        stock2.setId(new Stock.StockId(2, 7));
        stocks.add(stock2);

        CsvHelper helper = new CsvHelper();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        helper.toCsv(Stock.class, stocks, outputStream);
        assertThat(outputStream.size(), not(0));
    }

    @Test
    public void fromCsvTest() throws IOException {
        String csvContent = "1,1,70\n3,1,20\n2,2,12\n3,2,10\n1,3,4\n3,3,1\n1,4,1";
        CsvHelper csvHelper = new CsvHelper();
        InputStream inputStream = new ByteArrayInputStream(csvContent.getBytes());
        List<Stock> stocks = csvHelper.fromCsv(Stock.class, inputStream);
        assertThat(stocks, not(IsEmptyCollection.empty()));
        assertThat(stocks.size(), is(7));
        assertThat(stocks.get(0).getQuantity(), is(70));
    }
}
