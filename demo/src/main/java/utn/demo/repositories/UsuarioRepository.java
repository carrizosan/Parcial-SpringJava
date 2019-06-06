package utn.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import utn.demo.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
