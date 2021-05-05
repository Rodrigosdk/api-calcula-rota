package br.com.cruvinel.apicalcularota.service;

import br.com.cruvinel.apicalcularota.model.Malha;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MalhaService {
    Malha buscarPorId(Long id);

    Page<Malha> listar(Pageable page);

    Malha criar(Malha malha);

    void deletar(Long id);
}
