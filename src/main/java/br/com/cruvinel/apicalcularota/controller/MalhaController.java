package br.com.cruvinel.apicalcularota.controller;

import br.com.cruvinel.apicalcularota.model.Malha;
import br.com.cruvinel.apicalcularota.service.MalhaService;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@Log4j2
@RestController
@RequestMapping("/v1/malhas")
public class MalhaController {

    private final MalhaService malhaService;

    public MalhaController(MalhaService malhaService) {
        this.malhaService = malhaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        log.info("buscar por id {}", id);
        Malha malha = malhaService.buscarPorId(id);
        log.info("Malha {}", malha);
        return ResponseEntity.ok(malha);
    }

    @GetMapping
    public ResponseEntity<?> listar(Pageable pageable) {
        log.info("listar");
        Page<Malha> lista = malhaService.listar(pageable);
        log.info("listar, total: {}", lista.getContent().size());
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody @Valid Malha malha) {
        log.info("criar {}", malha);
        Malha malhaResult = malhaService.criar(malha);
        log.info("malha criada {}", malhaResult);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(malhaResult.getId())
                .toUri();
        return ResponseEntity.created(uri).body(malhaResult);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        log.info("deletar por id: {}", id);
        malhaService.deletar(id);
        log.info("malha deletada. id: {}", id);
        return ResponseEntity.noContent().build();
    }
}
