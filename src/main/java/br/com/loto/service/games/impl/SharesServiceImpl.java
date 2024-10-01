package br.com.loto.service.games.impl;

import br.com.loto.api.dto.game.request.CreateSharesRequest;
import br.com.loto.api.dto.game.request.UpdateBetRequest;
import br.com.loto.domain.entity.Account;
import br.com.loto.domain.entity.Pool;
import br.com.loto.domain.entity.Shares;
import br.com.loto.domain.entity.SharesFile;
import br.com.loto.domain.repository.ISharesFileRepository;
import br.com.loto.domain.repository.ISharesRepository;
import br.com.loto.exceptions.SharesException;
import br.com.loto.service.account.IAccountValidateService;
import br.com.loto.service.games.IPoolConsultService;
import br.com.loto.service.games.ISharesConsultService;
import br.com.loto.service.games.ISharesService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor(onConstructor_ = @Lazy)
public class SharesServiceImpl implements ISharesService {

    private final ISharesRepository sharesRepository;
    private final ISharesFileRepository sharesFileRepository;
    private final ISharesConsultService sharesConsultService;
    private final IPoolConsultService poolConsultService;
    private final IAccountValidateService accountValidateService;


    @Override
    public List<Shares> create(CreateSharesRequest request, List<MultipartFile> files) {

        Account accountCurrent = accountValidateService.verifyIfAccountCurrenIsAdmin();

        Pool pool = poolConsultService.findByIdWithThow(request.getPoolId());

        if (files == null && files.isEmpty())
            throw new SharesException("Não foi encontrado arquivos que represente as cotas.", 4004);

        List<Shares> shares = new ArrayList<>();

        for (MultipartFile file : files) {

            SharesFile sharesFile = null;

            try {
                sharesFile = sharesFileRepository.save(SharesFile.builder()
                        .createdAt(LocalDateTime.now())
                        .name(file.getOriginalFilename())
                        .contentType(file.getContentType())
                        .size(file.getSize())
                        .data(file.getBytes())
                        .build());
            } catch (IOException e) {
                throw new SharesException("Ocorreu um erro ao tentar gravar a imagem da cota.", 4004);
            }

            shares.add(save(Shares.builder()
                    .createdAt(LocalDateTime.now())
                    .createdBy(accountCurrent)
                    .sharesFile(sharesFile)
                    .pool(pool)
                    .build()));
        }

        return shares;
    }

    @Override
    public Shares update(UpdateBetRequest request, Long sharesId) {
        return null;
    }

    @Override
    public void deleteById(Long sharesId) {
        accountValidateService.verifyIfAccountCurrenIsAdmin();
        Shares shares = sharesConsultService.findByIdWithThow(sharesId);
        sharesRepository.deleteById(shares.getId());
    }

    @Override
    public Shares save(Shares shares) {
        return sharesRepository.save(shares);
    }
}
