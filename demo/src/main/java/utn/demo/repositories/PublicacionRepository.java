package utn.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import utn.demo.model.Publicacion;

public interface PublicacionRepository extends JpaRepository<Publicacion, Integer> {
}
