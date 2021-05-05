package br.com.cruvinel.apicalcularota.repository;

import br.com.cruvinel.apicalcularota.model.Malha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MalhaRepository extends JpaRepository<Malha, Long> {



}
