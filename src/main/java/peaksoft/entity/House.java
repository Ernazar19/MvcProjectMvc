package peaksoft.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import peaksoft.enums.HouseType;
import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "houses")
@Getter @Setter

public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "house_gen")
    @SequenceGenerator(name = "house_gen",sequenceName = "house_seq",allocationSize = 1)
    private Long id;
    @Column(name = "house_type")
    @Enumerated(EnumType.STRING)
    @NonNull()
    private HouseType houseType;

    private String address;
    private int price;
    private int room;
    private String country;
    private String description;
    private String image;
    @Column(name = "is_booked")
    private Boolean isBooked;
    @ManyToOne(cascade = {DETACH, MERGE, REFRESH})
    private Agency agency;
    @OneToOne(mappedBy = "house",cascade = {DETACH, MERGE, REFRESH})
    private Booking booking;
    @Transient
    private  String houseTypes;

}

