package utn.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utn.demo.model.Comentario;
import utn.demo.model.Publicacion;
import utn.demo.model.Usuario;
import utn.demo.model.UsuariosXPublicacionesXComentarios;
import utn.demo.services.UsuariosXPublicacionXComentarioService;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/allContent")
//EJERCICIO 3
public class UsuariosXPublicacionesXComentariosController {

    @Autowired
    UsuariosXPublicacionXComentarioService service;

    @GetMapping("")
    public ResponseEntity<?> getAsync() {
        CompletableFuture<List<Usuario>> users = service.getUsuarios();
        CompletableFuture<List<Comentario>> comments = service.getComentarios();
        CompletableFuture<List<Publicacion>> publications = service.getPublicaciones();
        UsuariosXPublicacionesXComentarios response = new UsuariosXPublicacionesXComentarios();

        response.setUsuarioList(users.join());
        response.setComentarioList(comments.join());
        response.setPublicacionList(publications.join());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

}
