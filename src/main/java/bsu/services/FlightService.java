package bsu.services;

import bsu.Util;
import bsu.entities.City;
import bsu.exceptions.AppValidationException;
import bsu.repositories.CityRepository;
import bsu.dto.FlySearchRequest;
import bsu.dto.FlySearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FlightService {
    private final CityRepository cityRepository;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    Double pricePerKilometer = 0.32;
    Double airplaneSpeed = 850.0;

    public List<FlySearchResponse> getFlyight(FlySearchRequest request){


        List<String> names = Arrays.asList(request.getFrom(), request.getTo());
        List<City> data =  cityRepository.findByNameIn(names);

        if (data.isEmpty() || data.size() < 2) {
            throw new AppValidationException("Can't find flight between this destinations");
        }
        List<FlySearchResponse> result = new ArrayList<>();


        Boolean isOneWay = request.getOneWay();
        LocalDateTime fromDate = LocalDateTime.parse(request.getFromDate(), DateTimeFormatter.ISO_DATE_TIME);
        Optional<LocalDateTime> toDate = isOneWay ? Optional.empty() : Optional.of(LocalDateTime.parse(request.getToDate(), DateTimeFormatter.ISO_DATE_TIME));

        Integer passengers = request.getPassengers();
        Double distance =  Util.calculateHaversineDistance(
                data.get(0).getLatitude().doubleValue(),
                data.get(0).getLongitude().doubleValue(),
                data.get(1).getLatitude().doubleValue(),
                data.get(1).getLongitude().doubleValue()
        );

        LocalTime totalTime = Util.calculateFlightTime(distance, airplaneSpeed);
        LocalTime departureTime = LocalTime.now().plusHours(2);
        LocalTime arrivalTime = departureTime
                .plusHours(totalTime.getHour())
                .plusMinutes(totalTime.getMinute());


        FlySearchResponse firstFly = FlySearchResponse.builder()
                .from(request.getFrom())
                .to(request.getTo())
                .passengers(passengers)
                .distance(distance)
                .flightDate(fromDate.toLocalDate())
                .totalTime(totalTime.format(formatter))
                .departureTime(departureTime.format(formatter))
                .arrivalTime(arrivalTime.format(formatter))
                .price(Math.ceil(distance * pricePerKilometer))
                .build();
        result.add(firstFly);

        if(!isOneWay){
            FlySearchResponse secondFly = FlySearchResponse.builder()
                    .from(request.getTo()) //
                    .to(request.getFrom()) //
                    .passengers(passengers) //
                    .distance(distance) //
                    .flightDate(toDate.map(LocalDateTime::toLocalDate).orElse(null)) //
                    .totalTime(totalTime.format(formatter))
                    .departureTime(departureTime.format(formatter)) //
                    .arrivalTime(arrivalTime.format(formatter)) //
                    .price( Math.floor(distance * pricePerKilometer)) //
                    .build();
            result.add(secondFly);
        }

        return result;
    }
}
