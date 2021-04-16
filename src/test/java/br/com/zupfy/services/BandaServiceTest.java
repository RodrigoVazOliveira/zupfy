package br.com.zupfy.services;

import br.com.zupfy.models.Banda;
import br.com.zupfy.repositories.BandaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

;

@SpringBootTest
public class BandaServiceTest {

    @MockBean
    private BandaRepository bandaRepository;

    @Autowired
    private BandaService bandaService;

    private Banda banda;
    private List<Banda> bandas;

    @BeforeEach
    public void setup() {
        banda = new Banda();
        banda.setId(1);
        banda.setNome("Linkin Park");
        banda.setNascimento(2003);

        bandas = new ArrayList<>();
        for (Integer i = 0; i < 100; i++) {
            Banda item = new Banda();
            item.setId(i);
            item.setNome("nome " + i);
            item.setNascimento(2015 - i);
            bandas.add(item);
        }
    }
    @Test
    public void salvarBandaOkTest() {
        Mockito.when(bandaRepository.save(Mockito.any(Banda.class))).thenReturn(banda);
        Banda bandaNova = bandaService.salvarBanda(banda);
        Assertions.assertEquals(bandaNova, banda);
    }

    @Test
    public void salvarBandaNoOkTest() {
        Mockito.when(bandaRepository.save(null));
        Assertions.assertThrows(RuntimeException.class, () -> {
            bandaService.salvarBanda(banda);
        });
    }

    @Test
    public void retornarTodasAsBandasOK() {
        Mockito.when(bandaRepository.findAll()).thenReturn(bandas);
        List<Banda> lista = bandaService.retornarTodasAsBandas();
        Assertions.assertEquals(lista, bandas);
    }

    @Test
    public void buscarBandaPeloIdOk() {
        Optional<Banda> optionalBanda = Optional.of(banda);
        Mockito.when(bandaRepository.findById(Mockito.anyInt())).thenReturn(optionalBanda);
        Banda bandaPesquisada = bandaService.buscarBandaPeloId(1);
        Assertions.assertEquals(bandaPesquisada, banda);
    }

    @Test
    public void buscarBandaPeloIdError() {
        Optional<Banda> optionalBanda = Optional.empty();
        Mockito.when(bandaRepository.findById(Mockito.anyInt())).thenReturn(optionalBanda);
        Assertions.assertThrows(RuntimeException.class, () -> {
            bandaService.buscarBandaPeloId(1);
        });
    }

    @Test
    public void atualizarBandaOk() {
        Mockito.when(bandaRepository.existsById(Mockito.anyInt())).thenReturn(true);
        Mockito.when(bandaRepository.save(Mockito.any(Banda.class))).thenReturn(banda);
        Banda bandaAtualizada = bandaService.atualizarBanda(banda);
        Assertions.assertEquals(bandaAtualizada, banda);
    }

    @Test
    public void atualizarBandaError() {
        Mockito.when(bandaRepository.existsById(Mockito.anyInt())).thenReturn(false);
        Assertions.assertThrows(RuntimeException.class, () -> {
            bandaService.atualizarBanda(banda);
        });
    }

    @Test
    public void atualizarParcialBandaOk() {
        Optional<Banda> optionalBanda = Optional.of(banda);
        Mockito.when(bandaRepository.findById(Mockito.anyInt())).thenReturn(optionalBanda);
        Mockito.when(bandaRepository.existsById(Mockito.anyInt())).thenReturn(true);
        Mockito.when(bandaRepository.save(Mockito.any(Banda.class))).thenReturn(banda);
        Banda bandaAtualizada = bandaService.atualizarParcialBanda(banda);
        Assertions.assertEquals(bandaAtualizada, banda);
    }

    @Test
    public void atualizarParcialBandaErrorBuscaPorId() {
        Optional<Banda> optionalBanda = Optional.of(banda);
        Mockito.when(bandaRepository.findById(Mockito.anyInt())).thenReturn(optionalBanda);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            bandaService.atualizarParcialBanda(banda);
        });
        Assertions.assertEquals("Banda não existe", exception.getMessage());
    }

    @Test
    public void atualizarParcialBandaError() {
        Optional<Banda> optionalBanda = Optional.empty();
        Mockito.when(bandaRepository.findById(Mockito.anyInt())).thenReturn(optionalBanda);
        Mockito.when(bandaRepository.existsById(Mockito.anyInt())).thenReturn(false);
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            bandaService.atualizarParcialBanda(banda);
        });
        Assertions.assertEquals("Banda não existe", exception.getMessage());
    }

/*    @Test
    public void deletarBandaOk() {
        Mockito.doNothing().when(bandaRepository).deleteById(Mockito.anyInt());
        Mockito.verify(bandaRepository).findById(1);
    }*/
}
