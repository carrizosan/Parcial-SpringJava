package utn.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import utn.demo.model.Comentario;
import utn.demo.model.Publicacion;
import utn.demo.model.Usuario;
import utn.demo.repositories.ComentarioRepository;
import utn.demo.repositories.PublicacionRepository;
import utn.demo.repositories.UsuarioRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;


//EJERCICIO 3
@Service
public class UsuariosXPublicacionXComentarioService {


    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    ComentarioRepository comentarioRepository;
    @Autowired
    PublicacionRepository publicacionRepository;


    @Async("threadPoolTaskExecutor")
    public CompletableFuture<List<Usuario>> getUsuarios() {
        List<Usuario> list = null;
        try {
            Thread.sleep(2000);
            list = usuarioRepository.findAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture(list);
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<List<Comentario>> getComentarios() {
        List<Comentario> list = null;
        try {
            Thread.sleep(2000);
            list = comentarioRepository.findAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture(list);
    }

    @Async("threadPoolTaskExecutor")
    public CompletableFuture<List<Publicacion>> getPublicaciones() {
        List<Publicacion> list = null;
        try {
            Thread.sleep(2000);
            list = publicacionRepository.findAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture(list);
    }
}
