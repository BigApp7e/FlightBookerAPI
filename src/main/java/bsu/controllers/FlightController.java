package bsu.controllers;

import bsu.services.FlightService;
import bsu.dto.FlySearchRequest;
import bsu.dto.FlySearchResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FlightController {
    private final FlightService flightService;


    @PostMapping("/search")
    public ResponseEntity<List<FlySearchResponse>> handlePostRequest(@Valid @RequestBody FlySearchRequest request) {

        log.info("Floght Controller Call changes BBB");

        List<FlySearchResponse> response = flightService.getFlyight(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
