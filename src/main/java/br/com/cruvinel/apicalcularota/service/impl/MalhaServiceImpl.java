package br.com.cruvinel.apicalcularota.service.impl;

import br.com.cruvinel.apicalcularota.model.Malha;
import br.com.cruvinel.apicalcularota.repository.MalhaRepository;
import br.com.cruvinel.apicalcularota.service.MalhaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MalhaServiceImpl implements MalhaService {
    private final MalhaRepository malhaRepository;

    public MalhaServiceImpl(MalhaRepository malhaRepository) {
        this.malhaRepository = malhaRepository;
    }

    @Override
    public Malha buscarPorId(Long id) {
        return buscarMalhaPorId(id);
    }

    @Override
    public Page<Malha> listar(Pageable page) {
        return malhaRepository.findAll(page);
    }

    @Override
    public Malha criar(Malha malha) {
        return malhaRepository.save(malha);
    }

    @Override
    public void deletar(Long id) {
        Malha malha = buscarMalhaPorId(id);
        malhaRepository.delete(malha);
    }

    private Malha buscarMalhaPorId(Long id) {
        return malhaRepository.findById(id).orElseThrow(() -> new RuntimeException("Malha não encontrada"));
    }
}
