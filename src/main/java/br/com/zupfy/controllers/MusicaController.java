package br.com.zupfy.controllers;

import br.com.zupfy.dtos.CadastrarMusicaDTO;
import br.com.zupfy.dtos.MusicaDTO;
import br.com.zupfy.models.Musica;
import br.com.zupfy.services.MusicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("musicas/")
public class MusicaController {

    @Autowired
    private MusicaService musicaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MusicaDTO cadastrarNovaMusica(@RequestBody CadastrarMusicaDTO cadastrarMusicaDTO) {
        Musica musica = musicaService.gravarMusica(cadastrarMusicaDTO.converterCadastrarMusicaDTOParaMusica());
        return MusicaDTO.converterMusicaParaMusicaDTO(musica);
    }

    @GetMapping("{id}/")
    @ResponseStatus(HttpStatus.OK)
    public MusicaDTO obterMusicaPorId(@PathVariable Integer id) {
        try {
            Musica musica = musicaService.buscarMusicaPorId(id);
            return MusicaDTO.converterMusicaParaMusicaDTO(musica);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
