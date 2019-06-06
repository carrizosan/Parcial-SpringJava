package utn.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import utn.demo.model.Comentario;


public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {
}
