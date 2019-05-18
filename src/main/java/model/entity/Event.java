package model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
@Entity(name = "EVENT")
@Table(name = "EVENT")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Event extends AbstractCRMEducation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Company company;

    @JoinColumn(name = "type_event_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private EventType type;

    @JoinColumn(name = "student_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Student student;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(id, event.id) &&
                Objects.equals(company, event.company) &&
                Objects.equals(type, event.type) &&
                Objects.equals(student, event.student);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, company, type, student);
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", company=" + company +
                ", type=" + type +
                ", student=" + student +
                '}';
    }
}
