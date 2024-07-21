package bsu.validation;

import bsu.Util;
import bsu.dto.FlySearchRequest;
import bsu.exceptions.AppValidationException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
public class Validator implements ConstraintValidator<IValidation, FlySearchRequest> {

    @Override
    public boolean isValid(FlySearchRequest request, ConstraintValidatorContext constraintValidatorContext) {

        LocalDateTime fromDate;
        LocalDateTime toDate;

        if(request.getFrom().equalsIgnoreCase(request.getTo())){
            throw new AppValidationException("The destinations can NOT be the same!");
        }

        if(request.getPassengers() <= 0){
            throw new AppValidationException("Passengers should be more than 1!");
        }

        try{
            fromDate = LocalDateTime.parse(request.getFromDate(), DateTimeFormatter.ISO_DATE_TIME);
        }catch (Exception e){
            throw new AppValidationException("Departure data is incorrect format!");
        }

        try{
            toDate = LocalDateTime.parse(request.getToDate(), DateTimeFormatter.ISO_DATE_TIME);
        }catch (Exception e){
            throw new AppValidationException("Arrival data is incorrect format!");
        }

        if(toDate.isBefore(fromDate)){
            throw new AppValidationException("Arrival data can not be before departure date!");
        }

        return true;
    }
}
