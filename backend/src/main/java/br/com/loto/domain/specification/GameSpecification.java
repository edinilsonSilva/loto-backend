package br.com.loto.domain.specification;

import br.com.loto.api.dto.game.queries.GameQuery;
import br.com.loto.domain.entity.Game;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class GameSpecification {

    public static Specification<Game> search(GameQuery request) {

        return (root, query, builder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasText(request.getName()))
                predicates.add(builder.equal(builder.lower(root.get("cpf")), "%" + request.getName().toLowerCase() + "%"));

            query.distinct(true);
            return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
        };
    }
}
