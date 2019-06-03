package model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
@Entity(name = "STUDENT")
@Table(name = "STUDENT")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
    @ManyToOne(fetch = FetchType.LAZY)
    private Specialty specialty;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "courseNumber", nullable = false)
    private String courseNumber;

    @Column(name = "groupNumber", nullable = false)
    private String groupNumber;

    @Column(name = "ticketNumber", nullable = false, unique = true)
    private String ticketNumber;

    @Column(name = "isExist")
    private boolean existing;

    @Column(name = "rating", nullable = false)
    private Integer rating;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return existing == student.existing &&
                Objects.equals(id, student.id) &&
                Objects.equals(fullName, student.fullName) &&
                Objects.equals(admissionYear, student.admissionYear) &&
                Objects.equals(specialty, student.specialty) &&
                Objects.equals(phoneNumber, student.phoneNumber) &&
                Objects.equals(courseNumber, student.courseNumber) &&
                Objects.equals(groupNumber, student.groupNumber) &&
                Objects.equals(ticketNumber, student.ticketNumber) &&
                Objects.equals(rating, student.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, admissionYear, specialty, phoneNumber, courseNumber, groupNumber, ticketNumber, existing, rating);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", admissionYear='" + admissionYear + '\'' +
                ", specialty=" + specialty +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", courseName=" + courseNumber +
                ", groupName=" + groupNumber +
                ", ticketNumber='" + ticketNumber + '\'' +
                ", existing=" + existing +
                ", rating=" + rating +
                '}';
    }
}