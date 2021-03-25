package br.com.zupfy.services;

import br.com.zupfy.models.Musica;
import br.com.zupfy.repositories.MusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MusicaService {

    @Autowired
    private MusicaRepository musicaRepository;

    public Musica gravarMusica(Musica musica) {
        try {
            Musica obj = musicaRepository.save(musica);
            return obj;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
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

        return musica;
    }
}
