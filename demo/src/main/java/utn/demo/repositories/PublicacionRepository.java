package utn.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import utn.demo.model.Publicacion;

import java.util.List;

@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion, Integer> {


    // QUERY PARA EJERCICIO 1, SIN REPOSITORIO EXCLUSIVO, USA EL MISMO DE PUBLICACION.
    String NATIVE_QUERY = "Select p.titulo, u.nombre, count(c.id) as cantidadComentarios from publicacion p " +
                        "inner join usuario u on p.usuario_id = u.id " +
                        "inner join comentario c on p.id = c.publicacion_id " +
                        "group by p.titulo, u.nombre";


    @Query(nativeQuery = true, value = NATIVE_QUERY)
    List<ComentariosXPublicacion> getPublicacionesDetalladas();


}
