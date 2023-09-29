package md.ceiti.internmanager.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;
import md.ceiti.internmanager.util.ConstraintViolationMessage;
import md.ceiti.internmanager.util.Field;

import java.time.LocalDate;

@Getter
@Setter
public class WorkRecordDto {

    private Long id;

    private AssignmentDto assignment;

    @NotNull(message = Field.DATE + ConstraintViolationMessage.NOT_NULL)
    @PastOrPresent(message = Field.DATE + ConstraintViolationMessage.PAST_OR_PRESENT)
    private LocalDate date;

    @NotNull(message = Field.WORKED_HOURS + ConstraintViolationMessage.NOT_NULL)
    @Min(value = 0, message = Field.WORKED_HOURS + ConstraintViolationMessage.MIN)
    private Double workedHours;
}
