package model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Setter
@Getter
@Entity(name = "COMPANY")
@Table(name = "COMPANY")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Company extends AbstractCRMEducation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "address")
    private String address;

    @Column(name = "isBool")
    private boolean isBool;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "company")
    @JsonIgnore
    private Set<Event> events;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "company")
    @JsonIgnore
    private Set<Contact> contacts;

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", isBool=" + isBool +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return isBool == company.isBool &&
                Objects.equals(id, company.id) &&
                Objects.equals(name, company.name) &&
                Objects.equals(login, company.login) &&
                Objects.equals(password, company.password) &&
                Objects.equals(address, company.address) &&
                Objects.equals(events, company.events) &&
                Objects.equals(contacts, company.contacts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, login, password, address, isBool, events, contacts);
    }
}
