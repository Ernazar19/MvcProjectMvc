package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "bookings")
@Getter @Setter
@NoArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking_gen")
    @SequenceGenerator(name = "booking_gen",sequenceName = "booking_seq",allocationSize = 1)
    private Long id;
    @OneToOne(cascade = {DETACH, MERGE, REFRESH})
    private House house;
    @ManyToOne(cascade = {DETACH, MERGE, REFRESH})
    private Customer customer;

}