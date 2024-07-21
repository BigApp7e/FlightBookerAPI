package bsu.dto;

import bsu.validation.IValidation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@IValidation
public class FlySearchRequest {

    @NotBlank(message = "Input field From cannot be empty")
    @NotNull(message = "Input field From cannot be null")
    private String from;

    @NotBlank(message = "Input field To cannot be empty")
    @NotNull(message = "Input field To cannot be null")
    private String to;

    @NotNull(message = "OneWay cannot be null")
    private Boolean oneWay;

    @Min(1)
    private Integer passengers;

    @NotBlank(message = "FromDate cannot be null empty")
    @NotNull(message = "FromDate cannot be null")
    private String fromDate;

    private String toDate;
}
