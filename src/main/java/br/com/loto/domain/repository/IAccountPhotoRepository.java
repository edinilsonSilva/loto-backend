package br.com.loto.domain.repository;

import br.com.loto.domain.entity.AccountPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountPhotoRepository extends JpaRepository<AccountPhoto, Long> {

}
