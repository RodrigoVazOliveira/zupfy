package br.com.zupfy.controllers;

import br.com.zupfy.dtos.AlbumMusicaDTO;
import br.com.zupfy.dtos.FiltrarAlbumDTO;
import br.com.zupfy.models.Album;
import br.com.zupfy.services.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("albums/")
public class Albumcontroller {

    @Autowired
    private AlbumService albumService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Album cadastrarAlbum(@RequestBody Album album) {
        return albumService.cadastrarAlbum(album);
    }

    @GetMapping("{id}/")
    @ResponseStatus(HttpStatus.OK)
    public AlbumMusicaDTO buscarPorId(@PathVariable Integer id) {
        try {
            Album album = albumService.buscarAlbumPorId(id);
            return AlbumMusicaDTO.converterAlbumParaAlbumMusicaDTO(album);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping
    public Iterable<AlbumMusicaDTO> obterTodosAlbuns(@ModelAttribute FiltrarAlbumDTO filtrarAlbumDTO) {
        Iterable<Album> albums = albumService.obterAlbunsPorNome(filtrarAlbumDTO);
        return AlbumMusicaDTO.converterListaAlbumParaListaAlbumMusicaDTO(albums);
    }
}