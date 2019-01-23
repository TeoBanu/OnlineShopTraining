package ro.msg.learning.shop.repos;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.datamodel.Product;

@Repository
@Slf4j
public class ProductRepo extends GenericRepo<Product> {

    public ProductRepo() {
        this("products");
    }

    public ProductRepo(String tableName) {
        super(tableName);
    }

    public Product getById(int id) {
        return super.getById(id, (rs, rowNum) -> {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setDescription(rs.getString("description"));
            product.setSupplierId(rs.getInt("supplierId"));
            product.setCategoryId(rs.getInt("categoryId"));
            product.setWeight(rs.getDouble("weight"));
            product.setPrice(rs.getBigDecimal("price"));
            return product;
        });
    }

    public void update(Product product) {
        super.update("update products set name = ?, description = ?, price = ?, weight = ?, categoryId = ?, supplierId = ? where id = ?",
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getWeight(),
                product.getCategoryId(),
                product.getSupplierId(),
                product.getId());
    }

    public void create(Product product) {
        super.create("insert into products (name, description, price, weight, categoryId, supplierId) values (?, ?, ?, ?, ?, ?)",
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getWeight(),
                product.getCategoryId(),
                product.getSupplierId());
    }

}
