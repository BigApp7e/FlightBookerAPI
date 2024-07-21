package bsu.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@Table(name = "capital_cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String country;
    private String city;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String airportCode;
}
