package model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Setter
@Getter
@Entity(name = "SPECIALTY")
@Table(name = "SPECIALTY")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Specialty extends AbstractCRMEducation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Override
    public String toString() {
        return "Specialty{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Specialty specialty = (Specialty) o;
        return Objects.equals(id, specialty.id) &&
                Objects.equals(name, specialty.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}