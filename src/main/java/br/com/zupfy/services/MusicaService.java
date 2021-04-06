package br.com.zupfy.services;

import br.com.zupfy.models.Album;
import br.com.zupfy.models.Banda;
import br.com.zupfy.models.Musica;
import br.com.zupfy.repositories.MusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MusicaService {

    private MusicaRepository musicaRepository;
    private BandaService bandaService;
    private AlbumService albumService;

    @Autowired
    public MusicaService(MusicaRepository musicaRepository, BandaService bandaService, AlbumService albumService) {
        this.musicaRepository = musicaRepository;
        this.bandaService = bandaService;
        this.albumService = albumService;
    }

    public Musica gravarMusica(Musica musica) {
        try {
            Banda banda = bandaService.buscarBandaPeloId(musica.getBanda().getId());
            musica.setBanda(banda);
            verificarAlbum(musica);
            Musica obj = musicaRepository.save(musica);
            return obj;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private void verificarAlbum(Musica musica) {
        Album album = musica.getAlbum();
            if (album.getId() == null) {
            musica.setAlbum(albumService.cadastrarAlbum(album));
        } else {
            musica.setAlbum(albumService.buscarAlbumPorId(album.getId()));
        }
    }

    public List<Musica> obterTodasAsMusicas() {
        return (List<Musica>) musicaRepository.findAll();
    }

    public Musica buscarMusicaPorId(Integer id) {
        Optional<Musica> musica = musicaRepository.findById(id);

        if (!musica.isPresent()) {
            throw new RuntimeException("Música com ID " + id + " não existe!");
        }

        return musica.get();
    }

    public Musica atualizarMusica(Musica musica) {
        if (!musicaRepository.existsById(musica.getId())) {
            throw new RuntimeException("Música com ID " + musica.getId() + " não foi localizada para atualizar!");
        }

        Musica novaMusica = gravarMusica(musica);
        return novaMusica;
    }

}
