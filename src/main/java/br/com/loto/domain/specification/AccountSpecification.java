package br.com.loto.domain.specification;

import br.com.loto.api.dto.account.query.AccountQuery;
import br.com.loto.domain.entity.Account;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class AccountSpecification {

    public static Specification<Account> search(AccountQuery request) {

        return (root, query, builder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasText(request.getName()))
                predicates.add(builder.equal(builder.lower(root.get("cpf")), "%" + request.getName().toLowerCase() + "%"));

            if (StringUtils.hasText(request.getCpf())) {
                String numberDocumentConv = "%" + request.getCpf().replace(".", "").replace("/", "").replace("-", "") + "%";
                predicates.add(builder.equal(root.get("cpf"), numberDocumentConv));
            }

            if (request.isActive())
                predicates.add(builder.equal(root.get("active"), request.isActive()));

            query.distinct(true);
            return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
        };
    }
}
