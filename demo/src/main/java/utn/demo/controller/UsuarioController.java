package utn.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import utn.demo.model.Usuario;
import utn.demo.repositories.UsuarioRepository;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UsuarioController {

    private static final String USUARIO_NOT_FOUND = "No se encontro el usuario id: %s";

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping("")
    public void addUser(@RequestBody Usuario u, @RequestHeader Map<String, String> header) {
        u.setBrowser(header.get("user-agent"));
        usuarioRepository.save(u);
    }

    @GetMapping("")
    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public Usuario getById(@PathVariable Integer id) {
        return usuarioRepository.findById(id).orElseThrow(() ->
                new HttpClientErrorException(HttpStatus.BAD_REQUEST, String.format(USUARIO_NOT_FOUND, id)));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        usuarioRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable Integer id, @RequestBody Usuario usuario){
        Usuario u = getById(id);
        u.setNombre(usuario.getNombre());
        u.setApellido(usuario.getApellido());
        usuarioRepository.saveAndFlush(u);
    }

}
