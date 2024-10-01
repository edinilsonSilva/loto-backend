package br.com.loto.domain.repository;

import br.com.loto.domain.entity.SharesFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ISharesFileRepository extends PagingAndSortingRepository<SharesFile, Long>, JpaSpecificationExecutor<SharesFile>, JpaRepository<SharesFile, Long> {

}
