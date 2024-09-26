package br.com.loto.domain.specification;

import br.com.loto.api.dto.game.queries.ContestQuery;
import br.com.loto.domain.entity.LotteryDraw;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LotteryDrawSpecification {

    public static Specification<LotteryDraw> search(ContestQuery request) {

        return (root, query, builder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (request.getContestNumber() != null)
                predicates.add(builder.equal(root.get("contestNumber"), request.getContestNumber()));

            if (request.getGameId() != null)
                predicates.add(builder.equal(root.get("game").get("id"), request.getGameId()));

            predicates.add(builder.greaterThanOrEqualTo(root.get("drawDate"), LocalDate.now()));
            query.distinct(true);
            return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
        };
    }
}
