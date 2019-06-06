package utn.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import utn.demo.model.Comentario;
import utn.demo.model.Publicacion;
import utn.demo.repositories.ComentarioRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comment")
public class ComentarioController {

    private static final String COMMENT_NOT_FOUND = "No existe el comentario id: %s";

    @Autowired
    ComentarioRepository comentarioRepository;
    @Autowired
    PublicacionController publicacionController;

    @PostMapping("/{id}") //id publicacion
    public void addComent(@RequestBody Comentario c, @PathVariable Integer id){
        Publicacion p = publicacionController.getById(id);
        c.setPublicacion(p);
        c.setOwner(p.getUsuario().getNombre() + " " + p.getUsuario().getApellido());
        comentarioRepository.save(c);
    }

    @GetMapping("")
    public List<Comentario> getAll() {
        return comentarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public Comentario getById(@PathVariable Integer id) {
        return comentarioRepository.findById(id).orElseThrow(() ->
                new HttpClientErrorException(HttpStatus.BAD_REQUEST, String.format(COMMENT_NOT_FOUND, id)));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        comentarioRepository.deleteById(id);
    }

    @Scheduled(fixedDelay = 10000)
    public void deleteComments() {
        List<Comentario> comments = getAll();

        LocalDate date = LocalDate.now().minus(30, ChronoUnit.DAYS);
        for(Comentario c: comments) {
            LocalDate commentDate = LocalDate.parse(c.getFecha());
            getAll().removeIf(x -> (
                commentDate.isBefore(date)
            ));
        }
    }

}
