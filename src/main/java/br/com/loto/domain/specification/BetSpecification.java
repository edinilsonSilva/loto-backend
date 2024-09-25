package br.com.loto.domain.specification;

import br.com.loto.api.dto.game.queries.BetQuery;
import br.com.loto.domain.entity.Bet;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class BetSpecification {

    public static Specification<Bet> search(BetQuery request) {

        return (root, query, builder) -> {

            List<Predicate> predicates = new ArrayList<>();
            query.distinct(true);
            return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
        };
    }
}
