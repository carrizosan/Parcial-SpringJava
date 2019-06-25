package utn.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import utn.demo.model.ComentariosXPublicacionDto;

import java.util.List;
@Repository
public interface ComentariosXPublicacionRepository extends JpaRepository<ComentariosXPublicacionDto, String> {

    // QUERY PARA EJERCICIO 2, REPOSITORIO EXCLUSIVO, JPA RECIBE EL DTO
    String NATIVE_QUERY = "Select p.titulo, u.nombre, count(c.id) as cantidadComentarios from publicacion p " +
            "inner join usuario u on p.usuario_id = u.id " +
            "inner join comentario c on p.id = c.publicacion_id " +
            "group by p.titulo, u.nombre";


    @Query(nativeQuery = true, value = NATIVE_QUERY)
    List<ComentariosXPublicacionDto> getPublicacionesDetalladas();
}
