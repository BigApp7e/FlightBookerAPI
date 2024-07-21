package bsu.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;


@Getter
@Setter
@Builder
public class FlySearchResponse {
    private String from;
    private  String to;
    private  Integer passengers;
    private  Double distance;

    private LocalDate flightDate;
    private String totalTime;

    private String departureTime;
    private String arrivalTime;
    private Double price;
}
