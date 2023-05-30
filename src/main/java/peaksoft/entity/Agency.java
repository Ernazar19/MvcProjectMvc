package peaksoft.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.Setter;
import java.util.List;
import static jakarta.persistence.CascadeType.*;
@Entity
@Table(name = "agencies")
@Getter
@Setter
@NoArgsConstructor
public class Agency {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "agency_gen")
    @SequenceGenerator(name = "agency_gen", sequenceName = "agency_seq", allocationSize = 1)
    private Long id;
    private String name;
    private String country;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "image_link")
    private String imageLink;
    @Email(message = "email contains @")
    private String email;
    @OneToMany(mappedBy = "agency", cascade = {DETACH, MERGE, REFRESH, REMOVE})
    private List<House> houses;
    @ManyToMany(mappedBy = "agencies", cascade = {DETACH, MERGE, REFRESH,REMOVE})
    private List<Customer> customers;
}
