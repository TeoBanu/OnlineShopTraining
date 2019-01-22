package ro.msg.learning.shop.repos;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.datamodel.Product;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class ProductRepo {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Map<String, Object>> getAll() {
        log.info("productRepo.getAll");
        return this.jdbcTemplate.queryForList("select * from products");
    }

    public Product getById(int id) {
        log.info("productRepo.getById");
        Object[] params = new Object[]{id};
        return this.jdbcTemplate.queryForObject("select * from products where id=?", params, (rs, rowNum) -> {
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
        log.info("productRepo.update");
        this.jdbcTemplate.update("update products set name = ?, description = ?, price = ?, weight = ?, categoryId = ?, supplierId = ? where id = ?",
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getWeight(),
                product.getCategoryId(),
                product.getSupplierId(),
                product.getId());
    }

    public void delete(Product product) {
        log.info("productRepo.delete");
        this.jdbcTemplate.update("delete from products where id = ?", product.getId());
    }

    public void create(Product product) {
        log.info("productRepo.insert");
        this.jdbcTemplate.update("insert into products (name, description, price, weight, categoryId, supplierId) values (?, ?, ?, ?, ?, ?)",
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getWeight(),
                product.getCategoryId(),
                product.getSupplierId());
    }

}
