package md.ceiti.internmanager.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import md.ceiti.internmanager.enums.Stage;
import md.ceiti.internmanager.util.ConstraintViolationMessage;
import md.ceiti.internmanager.util.Field;

@Getter
@Setter
public class JobDto {

    private Long id;

    @NotBlank(message = Field.NAME + ConstraintViolationMessage.NOT_BLANK)
    private String name;

    @NotNull(message = Field.STAGE + ConstraintViolationMessage.NOT_NULL)
    @Enumerated(EnumType.STRING)
    private Stage stage;

    @NotNull(message = Field.BASE_SALARY + ConstraintViolationMessage.NOT_NULL)
    @Min(value = 0, message = Field.BASE_SALARY + ConstraintViolationMessage.MIN)
    private Double baseSalary;
}
