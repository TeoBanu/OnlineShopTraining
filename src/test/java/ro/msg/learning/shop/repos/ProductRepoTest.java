package ro.msg.learning.shop.repos;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.junit4.SpringRunner;
import ro.msg.learning.shop.ShopApplication;
import ro.msg.learning.shop.datamodel.Product;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShopApplication.class)
public class ProductRepoTest {
    private static final int WATCH_ID = 1;

    private static final String WATCH_NAME = "smart watch";

    private EmbeddedDatabase db;

    private Product watch = new Product();

    @Autowired
    private ProductRepo repo;

    @Before
    public void setUp() {
        // creates an H2 in-memory database populated from default scripts
        // classpath:schema.sql and classpath:data.sql
        db = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .generateUniqueName(true)
                .addDefaultScripts()
                .build();
        watch.setId(WATCH_ID);
        watch.setName(WATCH_NAME);
        watch.setDescription("smart description for smart watch");
        watch.setPrice(BigDecimal.valueOf(10.32));
        watch.setWeight(20.2);
        watch.setCategoryId(1);
        watch.setSupplierId(1);
    }

    @Test
    public void testRepo() {
        repo.create(watch);
        Assert.assertEquals(1, repo.getAll().size());
        Assert.assertEquals(WATCH_NAME, repo.getAll().get(0).get("name"));
        Assert.assertEquals(WATCH_NAME, repo.getById(WATCH_ID).getName());

        watch.setName("another name");
        repo.update(watch);
        Assert.assertNotEquals(WATCH_NAME, repo.getAll().get(0).get("name"));

        repo.delete(watch);
        Assert.assertTrue(repo.getAll().isEmpty());
    }

    @After
    public void tearDown() {
        db.shutdown();
    }
}
