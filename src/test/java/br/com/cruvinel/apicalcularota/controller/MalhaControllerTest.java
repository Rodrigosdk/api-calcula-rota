package br.com.cruvinel.apicalcularota.controller;

import br.com.cruvinel.apicalcularota.model.Malha;
import br.com.cruvinel.apicalcularota.repository.MalhaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.tomcat.jni.Address;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest()
@AutoConfigureMockMvc
public class MalhaControllerTest {

    private static final String MALHAS_ENDPOINT="/v1/malhas";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MalhaRepository malhaRepository;

    @SneakyThrows
    @Test
    void deveCriarUmaMalha(){
        Malha malha = getMalha();

        this.mockMvc
                .perform(post(MALHAS_ENDPOINT)
                        .content(asJsonString(malha))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.origem").value(malha.getOrigem()));
    }

    @SneakyThrows
    @Test
    void deveBuscarMalhaPorId() {
        //criar malha
        this.mockMvc
                .perform(post(MALHAS_ENDPOINT)
                        .content(asJsonString(getMalha()))
                        .contentType(MediaType.APPLICATION_JSON));

        Malha malha = malhaRepository.findAll().get(0);

        //consultar malha
        this.mockMvc
                .perform(get(MALHAS_ENDPOINT.concat("/{id}"), malha.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.origem").value(malha.getOrigem()));
    }


    @SneakyThrows
    @Test
    void deveConsultaUmaPaginacaoDeMalha() {
        //criar malha
        this.mockMvc
                .perform(post(MALHAS_ENDPOINT)
                        .content(asJsonString(getMalha()))
                        .contentType(MediaType.APPLICATION_JSON));

        Malha malha = malhaRepository.findAll().get(0);

        //listar malha
        this.mockMvc
                .perform(get(MALHAS_ENDPOINT.concat("?page=0&size=10"), malha.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size").value(10));
    }

    @SneakyThrows
    @Test
    void deveDeletarUmaMalha() {
        //criar malha
        this.mockMvc
                .perform(post(MALHAS_ENDPOINT)
                        .content(asJsonString(getMalha()))
                        .contentType(MediaType.APPLICATION_JSON));

        Malha malha = malhaRepository.findAll().get(0);

        //deletar malha
        this.mockMvc
                .perform(delete(MALHAS_ENDPOINT.concat("/{id}"), malha.getId()))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    private Malha getMalha(){
        return Malha.builder()
                .origem("A")
                .destino("B")
                .distancia(BigDecimal.TEN)
                .build();
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
