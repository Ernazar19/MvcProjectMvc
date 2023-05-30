package peaksoft.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import peaksoft.enums.Gender;

import java.time.LocalDate;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "customers")
@Getter
@Setter


public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_gen")
    @SequenceGenerator(name = "customer_gen", sequenceName = "customer_seq", allocationSize = 1)
    private Long id;
    private String name;
    @Column(name = "sure_name")
    private String sureName;
    @Email(message = "email contains @")
    private String email;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @ManyToMany(cascade = {DETACH, MERGE, REFRESH})
    private List<Agency> agencies;
    @OneToMany(mappedBy = "customer", cascade = {DETACH, MERGE, REFRESH,REMOVE})
    private List<Booking> bookings;

}