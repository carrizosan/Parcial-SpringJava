package utn.demo.controller;


import org.hibernate.mapping.UnionSubclass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import utn.demo.model.Publicacion;
import utn.demo.model.Usuario;
import utn.demo.repositories.PublicacionRepository;
import utn.demo.repositories.UsuarioRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;


@RestController
@RequestMapping("/publication")
public class PublicacionController {

    private static final String PUBLICATION_NOT_FOUND = "No se encontro la publicación id: %s";

    @Autowired
    PublicacionRepository publicacionRepository;
    @Autowired
    UsuarioController usuarioController;

    @PostMapping("/{id}") // id user
    public void addPublicacion(@RequestBody Publicacion p, @PathVariable Integer id) {
        Usuario usuario = usuarioController.getById(id);
        p.setUsuario(usuario);
        publicacionRepository.save(p);
    }

    @GetMapping("")
    public List<Publicacion> getAll() {
        return publicacionRepository.findAll();
    }

    @GetMapping("/{id}")
    public Publicacion getById(@PathVariable Integer id) {
        return publicacionRepository.findById(id).orElseThrow(() ->
                new HttpClientErrorException(HttpStatus.BAD_REQUEST, String.format(PUBLICATION_NOT_FOUND, id)));
    }

}
