package md.ceiti.internmanager.dto;

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
    private Double paymentPerHour;
}
