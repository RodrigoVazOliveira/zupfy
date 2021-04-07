package br.com.zupfy.services;

import br.com.zupfy.dtos.FiltrarAlbumDTO;
import br.com.zupfy.models.Album;
import br.com.zupfy.repositories.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    public Album cadastrarAlbum(Album album) {
        return albumRepository.save(album);
    }

    public Album buscarAlbumPorId(Integer idAlbum) {
        Optional<Album> album = albumRepository.findById(idAlbum);

        if (!album.isPresent()) {
            throw new RuntimeException("Album não existe no banco de dados!");
        }

        return album.get();
    }

    public Iterable<Album> obterTodosAlbums() {
        return albumRepository.findAll();
    }

    public Iterable<Album> obterAlbunsPorNome(FiltrarAlbumDTO filtrarAlbumDTO) {
        if (filtrarAlbumDTO.getNome() == null) {
            return obterTodosAlbums();
        }
        return albumRepository.findByNome(filtrarAlbumDTO.getNome());
    }
}
