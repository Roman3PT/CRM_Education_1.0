package model.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Data
@Entity(name = "STUDENT")
@Table(name = "STUDENT")
public class Student extends AbstractCRMEducation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fullName", nullable = false)
    private String fullName;

    @Column(name = "admission_year")
    private String admissionYear;

    @JoinColumn(name = "specialty_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Specialty specialty;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "ticketNumber", nullable = false, unique = true)
    private String ticketNumber;

    @Column(name = "isExist")
    private boolean existing;

    @Column(name = "rating", nullable = false)
    private Integer rating;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "student")
    private Set<Event> events;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return existing == student.existing &&
                Objects.equals(id, student.id) &&
                Objects.equals(fullName, student.fullName) &&
                Objects.equals(admissionYear, student.admissionYear) &&
                Objects.equals(specialty, student.specialty) &&
                Objects.equals(phoneNumber, student.phoneNumber) &&
                Objects.equals(ticketNumber, student.ticketNumber) &&
                Objects.equals(rating, student.rating) &&
                Objects.equals(events, student.events);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", admissionYear='" + admissionYear + '\'' +
                ", specialty=" + specialty +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", ticketNumber='" + ticketNumber + '\'' +
                ", existing=" + existing +
                ", rating=" + rating +
                ", events=" + events +
                '}';
    }
}