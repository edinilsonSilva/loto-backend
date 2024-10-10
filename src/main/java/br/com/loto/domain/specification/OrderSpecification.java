package br.com.loto.domain.specification;

import br.com.loto.api.dto.game.queries.OrderQuery;
import br.com.loto.domain.entity.Order;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class OrderSpecification {

    public static Specification<Order> search(OrderQuery request) {

        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (request.getAccountId() != null)
                predicates.add(builder.equal(root.get("account").get("id"), request.getAccountId()));

            query.distinct(true);
            return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
        };
    }
}
