package utn.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.util.Objects.isNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Comentario extends BaseClass{

    @JsonIgnore
    private String owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publicacion_id", referencedColumnName = "id")
    @JsonIgnore
    private Publicacion publicacion;

    @PrePersist
    public void setTime() {
        DateTimeFormatter  format1 = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        String dateformated = LocalDate.now().format(format1);
        if (isNull(this.getFecha())) {
            setFecha( dateformated);
        }
    }
}
