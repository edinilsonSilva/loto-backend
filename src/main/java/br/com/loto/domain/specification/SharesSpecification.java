package br.com.loto.domain.specification;

import br.com.loto.api.dto.game.queries.SharesQuery;
import br.com.loto.domain.entity.Shares;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class SharesSpecification {

    public static Specification<Shares> search(SharesQuery request) {

        return (root, query, builder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (request.getPoolId() != null)
                predicates.add(builder.equal(root.get("pool").get("id"), request.getPoolId()));

            query.distinct(true);
            return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
        };
    }
}
