package utn.demo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
//EJERCICIO 3
public class UsuariosXPublicacionesXComentarios {

    List<Usuario> usuarioList;
    List<Publicacion> publicacionList;
    List<Comentario> comentarioList;
}
