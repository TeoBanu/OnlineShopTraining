package ro.msg.learning.shop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.datamodel.Stock;
import ro.msg.learning.shop.dtos.OrderProductDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface StockRepo extends JpaRepository<Stock, Stock.StockId>, StockRepoCustom {
    List<Stock> findByIdLocationId(int locationId);
}

interface StockRepoCustom {
    List<Integer> selectSatisfactoryLocations(List<OrderProductDto> products);
}

class StockRepoImpl implements StockRepoCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Integer> selectSatisfactoryLocations(List<OrderProductDto> products) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Integer> query = criteriaBuilder.createQuery(Integer.class);
        Root<Stock> root = query.from(Stock.class);
        List<Predicate> predicates = new ArrayList<>();
        products.forEach(product ->
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("id").get("productId"), product.getId()),
                        criteriaBuilder.greaterThanOrEqualTo(root.get("quantity"), product.getQuantity())))
        );
        Expression<Integer> locationId = root.get("id").get("locationId");
        Expression<Boolean> having = criteriaBuilder.equal(criteriaBuilder.count(locationId), products.size());
        query.select(locationId).where(criteriaBuilder.or(predicates.toArray(new Predicate[0])))
        .groupBy(locationId)
                .having(having);
        return em.createQuery(query).getResultList();
    }
}


