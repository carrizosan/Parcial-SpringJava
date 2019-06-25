package utn.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utn.demo.model.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {
}
