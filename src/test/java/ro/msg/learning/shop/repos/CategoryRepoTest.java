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
import ro.msg.learning.shop.datamodel.ProductCategory;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShopApplication.class)
public class CategoryRepoTest {

    private static final String ELECTRONICS_NAME = "electronics";

    private EmbeddedDatabase db;

    private ProductCategory category = new ProductCategory();

    @Autowired
    private CategoryRepo repo;

    @Before
    public void setUp() {
        // creates an H2 in-memory database populated from default scripts
        // classpath:schema.sql and classpath:data.sql
        db = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .generateUniqueName(true)
                .addDefaultScripts()
                .build();
        category.setName(ELECTRONICS_NAME);
        category.setDescription("electronics category description");
    }

    @Test
    public void testRepo() {
        repo.create(category);
        Assert.assertEquals(2, repo.getAll().size());
        Assert.assertEquals(1, repo.getAll().stream().filter(map -> map.get("name").equals(ELECTRONICS_NAME)).count());
        Optional<Integer> optionalId = repo.getAll().stream().filter(map -> map.get("name").equals(ELECTRONICS_NAME)).map(map -> (int) map.get("id")).findAny();
        optionalId.ifPresent(id -> Assert.assertEquals(ELECTRONICS_NAME, repo.getById(id).getName()));

        category.setName("another name");
        repo.update(category);
        Assert.assertEquals(1, repo.getAll().stream().filter(map -> map.get("name").equals(ELECTRONICS_NAME)).count());

        optionalId.ifPresent(id -> repo.delete(id));
        Assert.assertEquals(1, repo.getAll().size());
    }

    @After
    public void tearDown() {
        db.shutdown();
    }
}

