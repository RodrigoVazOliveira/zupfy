package br.com.zupfy.services;

import br.com.zupfy.dtos.FiltrarAlbumDTO;
import br.com.zupfy.models.Album;
import br.com.zupfy.repositories.AlbumRepository;
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

@SpringBootTest
public class AlbumServiceTest {

    @MockBean
    private AlbumRepository albumRepository;

    private Album album;

    @Autowired
    private AlbumService albumService;

    private List<Album> albums;

    private FiltrarAlbumDTO filtrarAlbumDTO;

    @BeforeEach
    public void setup() {
        album = new Album();
        album.setId(1);
        album.setNome("sei la");
        album.setAnoLancamento(2005);


        albums = new ArrayList<>();

        for (Integer i = 0; i < 10; i++) {
            Album item = new Album();
            item.setId(i);
            item.setNome("sei la" + i);
            item.setAnoLancamento(2005 + i);

            albums.add(item);
        }

        filtrarAlbumDTO = new FiltrarAlbumDTO();
        filtrarAlbumDTO.setNome("sei la");
    }

    @Test
    public void testarGrvaarNovoAlbum() {
        Mockito.when(albumRepository.save(Mockito.any(Album.class))).thenReturn(album);
        Album novoAlbum = albumService.cadastrarAlbum(album);
        Assertions.assertEquals(novoAlbum, album);
    }

    @Test
    public void testarBuscaDeAlbumPorIdOk() {
        Optional<Album> optionalAlbum = Optional.of(album);
        Mockito.when(albumRepository.findById(Mockito.anyInt())).thenReturn(optionalAlbum);

        Album albumProcurado = albumService.buscarAlbumPorId(1);

        Assertions.assertEquals(albumProcurado, album);
    }

    @Test
    public void testarBuscaDeAlbumPorIdError() {
        Optional<Album> optionalAlbum = Optional.empty();
        Mockito.when(albumRepository.findById(Mockito.anyInt())).thenReturn(optionalAlbum);

        Assertions.assertThrows(RuntimeException.class, () -> {
            albumService.buscarAlbumPorId(1);
        });
    }

    @Test
    public void testarObterAlbuns() {
        Mockito.when(albumRepository.findAll()).thenReturn(albums);

        List<Album> lista = (List<Album>) albumService.obterTodosAlbums();

        Assertions.assertEquals(lista, albums);
    }

    @Test
    public void testarObterAlbumPeloNomeOk() {
        Mockito.when(albumRepository.findByNome(Mockito.anyString())).thenReturn(albums);

        List<Album> lista = (List<Album>) albumService.obterAlbunsPorNome(filtrarAlbumDTO);

        Assertions.assertEquals(lista, albums);
    }

    @Test
    public void testarObterAlbumPeloNomeError() {
        filtrarAlbumDTO.setNome(null);
        Mockito.when(albumRepository.findAll()).thenReturn(albums);
        List<Album> lista = (List<Album>) albumService.obterAlbunsPorNome(filtrarAlbumDTO);

        Assertions.assertEquals(lista, albums);
    }
}
