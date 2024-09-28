package br.com.loto.domain.specification;

import br.com.loto.api.dto.game.queries.ContestQuery;
import br.com.loto.domain.entity.LotteryDraw;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LotteryDrawPublicSpecification {

    public static Specification<LotteryDraw> search(ContestQuery request) {

        return (root, query, builder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (request.getContestNumber() != null)
                predicates.add(builder.equal(root.get("contestNumber"), request.getContestNumber()));

            if (request.getGameId() != null)
                predicates.add(builder.equal(root.get("game").get("id"), request.getGameId()));

            // Combina as duas condições: drawDate >= hoje ou nextDrawDate >= hoje
            Predicate drawDateMaiorOuIgual = builder.greaterThanOrEqualTo(root.get("drawDate"), LocalDate.now());
            Predicate nextDrawDateMaiorOuIgual = builder.greaterThanOrEqualTo(root.get("nextDrawDate"), LocalDate.now());

            // Combina as duas condições em um OR
            predicates.add(builder.or(drawDateMaiorOuIgual, nextDrawDateMaiorOuIgual));

            query.distinct(true);
            return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
        };
    }
}
