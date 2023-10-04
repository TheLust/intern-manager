package md.ceiti.internmanager.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import md.ceiti.internmanager.util.ConstraintViolationMessage;
import md.ceiti.internmanager.util.Field;

@Getter
@Setter
public class WageDto {

    private Long id;

    private ProjectDto project;

    private JobDto job;

    @NotNull(message = Field.PAYMENT_PER_HOUR + ConstraintViolationMessage.NOT_NULL)
    @Min(value = 0, message = Field.PAYMENT_PER_HOUR + ConstraintViolationMessage.MIN)
    private Double paymentPerHour;
}
