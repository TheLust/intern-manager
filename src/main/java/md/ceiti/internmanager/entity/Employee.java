package md.ceiti.internmanager.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import md.ceiti.internmanager.enums.Gender;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne
    private Department department;

    @NotNull
    @ManyToOne
    private Job job;

    @OneToMany(mappedBy = "employee")
    private List<Assignment> assignments;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false,
            unique = true)
    private String email;

    @Column(nullable = false,
            unique = true)
    private String phoneNumber;

    @Column(nullable = false)
    private LocalDate dateOfHire;

    @Column(nullable = false)
    private Double bonusSalary;
}
