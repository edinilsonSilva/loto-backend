package br.com.loto.domain.specification;

import br.com.loto.api.dto.game.queries.PoolQuery;
import br.com.loto.domain.entity.Pool;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class PoolSpecification {

    public static Specification<Pool> search(PoolQuery request) {

        return (root, query, builder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasText(request.getName()))
                predicates.add(builder.equal(builder.lower(root.get("cpf")), "%" + request.getName().toLowerCase() + "%"));

            query.distinct(true);
            return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
        };
    }
}
