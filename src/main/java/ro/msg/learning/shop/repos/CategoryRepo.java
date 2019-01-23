package ro.msg.learning.shop.repos;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.datamodel.ProductCategory;

@Repository
@Slf4j
public class CategoryRepo extends GenericRepo<ProductCategory> {

    public CategoryRepo() {
        this("categories");
    }

    public CategoryRepo(String tableName) {
        super(tableName);
    }

    public ProductCategory getById(int id) {
        return super.getById(id, (rs, rowNum) -> {
            ProductCategory category = new ProductCategory();
            category.setId(rs.getInt("id"));
            category.setName(rs.getString("name"));
            category.setDescription(rs.getString("description"));
            return category;
        });
    }

    public void update(ProductCategory category) {
        super.update("update categories set name = ?, description = ? where id = ?",
                category.getName(),
                category.getDescription(),
                category.getId());
    }

    public void create(ProductCategory category) {
        super.create("insert into categories (name, description) values (?, ?)",
                category.getName(),
                category.getDescription());
    }

}
