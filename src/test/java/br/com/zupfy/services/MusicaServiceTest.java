package br.com.zupfy.services;

import br.com.zupfy.models.Album;
import br.com.zupfy.models.Banda;
import br.com.zupfy.models.Musica;
import br.com.zupfy.repositories.MusicaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class MusicaServiceTest {

    @MockBean
    private MusicaRepository musicaRepository;

    @MockBean
    private BandaService bandaService;

    @MockBean
    private AlbumService albumService;

    @Autowired
    private MusicaService musicaService;

    private Musica musicaTest;
    private Banda bandaTest;
    private Album albumTest;
    private List<Musica> musicas;

    @BeforeEach
    public void setup() {
        bandaTest = new Banda();
        bandaTest.setId(1);
        bandaTest.setNome("Linkin park");
        bandaTest.setNascimento(2001);

        albumTest = new Album();
        albumTest.setId(1);
        albumTest.setNome("meteoro");
        albumTest.setAnoLancamento(2003);

        musicaTest = new Musica();
        musicaTest.setBanda(bandaTest);
        musicaTest.setAlbum(albumTest);
        musicaTest.setNomeMusica("numb");
        musicaTest.setEnderecoMusica("wwwww");
        musicaTest.setDuracao(LocalTime.now());

        musicas = new ArrayList<>();

        for (Integer i = 0; i < 10; i++) {
            Musica musica = new Musica();
            musica.setId(i);
            musica.setBanda(bandaTest);
            musica.setAlbum(albumTest);
            musica.setNomeMusica("numb");
            musica.setEnderecoMusica("wwwww");
            musica.setDuracao(LocalTime.now());
            musicas.add(musica);
        }

    }


    @Test
    public void testarGravarMusicaOk() {
        Mockito.when(bandaService.buscarBandaPeloId(Mockito.anyInt())).thenReturn(bandaTest);
        Mockito.when(musicaRepository.save(Mockito.any(Musica.class))).thenReturn(musicaTest);

        Musica musica = musicaService.gravarMusica(musicaTest);

        Assertions.assertEquals(musica, musicaTest);
    }

    @Test
    public void testarGravarMusicaError() {
        Mockito.when(bandaService.buscarBandaPeloId(Mockito.anyInt())).thenReturn(bandaTest);
        Mockito.when(musicaRepository.save(Mockito.any(Musica.class))).thenReturn(musicaTest);
        musicaTest.setBanda(null);

        Assertions.assertThrows(RuntimeException.class, () -> {
            musicaService.gravarMusica(musicaTest);
        });
    }

    @Test
    public void testarObterListaMusicas() {
        Mockito.when(musicaRepository.findAll()).thenReturn(musicas);
        List<Musica> musicasTest = musicaService.obterTodasAsMusicas();
        Assertions.assertEquals(musicas, musicasTest);
    }

    @Test
    public void testarBuscarMusicaPorIdOk() {
        musicaTest.setId(1);
        Optional<Musica> optionalMusica = Optional.of(musicaTest);
        Mockito.when(musicaRepository.findById(Mockito.anyInt())).thenReturn(optionalMusica);

        Musica musicaEncontrada = musicaService.buscarMusicaPorId(1);

        Assertions.assertEquals(musicaEncontrada, musicaTest);

    }

    @Test
    public void testarBuscarMusicaPorIdError() {
        Optional<Musica> optionalMusica = Optional.empty();
        Mockito.when(musicaRepository.findById(Mockito.anyInt())).thenReturn(optionalMusica);

        Assertions.assertThrows(RuntimeException.class, () -> {
            musicaService.buscarMusicaPorId(2);
        });
    }


}
