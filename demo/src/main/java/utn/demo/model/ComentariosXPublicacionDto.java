package utn.demo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ComentariosXPublicacionDto {

    @Id // Suponiendo que el titulo de la publicacion sea unique.
    String titulo;
    String nombre;
    String cantidadComentarios;
}
