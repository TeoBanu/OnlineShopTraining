package ro.msg.learning.shop.repos;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public abstract class GenericRepo<T> {
    private JdbcTemplate jdbcTemplate;

    private String tableName;

    public GenericRepo() { }

    public GenericRepo(String tableName) {
        this.tableName = tableName;
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Map<String, Object>> getAll() {
        log.info("getAll");
        return this.jdbcTemplate.queryForList("select * from " + tableName);
    }

    public T getById(int id, RowMapper<T> rowMapper) {
        log.info("getById");
        Object[] params = new Object[]{id};
        return this.jdbcTemplate.queryForObject("select * from " + tableName + " where id=?", params, rowMapper);
    }

    public void update(String query, Object... params) {
        log.info("update");
        this.jdbcTemplate.update(query, params);
    }

    public void delete(int id) {
        log.info("delete");
        this.jdbcTemplate.update("delete from " + tableName + " where id = ?", id);
    }

    public void create(String query, Object... params) {
        log.info("insert");
        this.jdbcTemplate.update(query, params);
    }
}
