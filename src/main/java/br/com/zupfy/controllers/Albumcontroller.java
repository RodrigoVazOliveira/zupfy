package br.com.zupfy.controllers;

import br.com.zupfy.models.Album;
import br.com.zupfy.services.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
}
