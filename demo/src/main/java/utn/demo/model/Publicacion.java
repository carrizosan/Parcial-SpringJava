package utn.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.util.Objects.isNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Publicacion extends BaseClass{

    private String titulo;
    private String foto;
    private Integer likes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    @JsonIgnore
    private Usuario usuario;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "publicacion", orphanRemoval = true)
    private List<Comentario> comentarios;

    @PrePersist
    public void setTime() {
        DateTimeFormatter format1 = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        String dateformated = LocalDate.now().format(format1);
        if (isNull(this.getFecha())) {
            setFecha( dateformated);
        }
    }
}
