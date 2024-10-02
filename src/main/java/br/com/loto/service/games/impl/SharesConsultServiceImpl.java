package br.com.loto.service.games.impl;

import br.com.loto.api.dto.game.queries.SharesQuery;
import br.com.loto.api.dto.game.response.SharesResponse;
import br.com.loto.api.mappers.SharesMapper;
import br.com.loto.domain.entity.Shares;
import br.com.loto.domain.repository.ISharesRepository;
import br.com.loto.domain.specification.SharesSpecification;
import br.com.loto.exceptions.BetException;
import br.com.loto.service.account.IAccountValidateService;
import br.com.loto.service.games.ISharesConsultService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor_ = @Lazy)
public class SharesConsultServiceImpl implements ISharesConsultService {

    private final ISharesRepository sharesRepository;

    private final IAccountValidateService accountValidateService;

    private final SharesMapper sharesMapper;

    @Override
    public Page<SharesResponse> findAllByParams(SharesQuery query) {

        accountValidateService.verifyIfAccountCurrenIsAdmin();

        Sort.Direction sortDirection = "desc".equalsIgnoreCase(query.getDirection()) ? Sort.Direction.DESC : Sort.Direction.ASC;
        PageRequest pageRequest = PageRequest.of(query.getPage(), query.getLimit(), Sort.by(sortDirection, query.getOrderBy()));
        Page<Shares> pages = sharesRepository.findAll(SharesSpecification.search(query), pageRequest);
        return pages.map(sharesMapper::convertEntityToResponse);
    }

    @Override
    public Page<SharesResponse> findAllByParamsPublic(SharesQuery query) {
        Sort.Direction sortDirection = "desc".equalsIgnoreCase(query.getDirection()) ? Sort.Direction.DESC : Sort.Direction.ASC;
        PageRequest pageRequest = PageRequest.of(query.getPage(), query.getLimit(), Sort.by(sortDirection, query.getOrderBy()));
        Page<Shares> pages = sharesRepository.findAll(SharesSpecification.search(query), pageRequest);
        return pages.map(sharesMapper::convertEntityToResponse);
    }

    @Override
    public Shares findByIdWithThow(Long sharesId) {
        return sharesRepository.findById(sharesId)
                .orElseThrow(() -> new BetException("Cota não encontrada.", 4004));
    }
}
