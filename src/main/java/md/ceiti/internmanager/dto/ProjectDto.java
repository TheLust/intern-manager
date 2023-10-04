package md.ceiti.internmanager.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import md.ceiti.internmanager.util.ConstraintViolationMessage;
import md.ceiti.internmanager.util.Field;

@Getter
@Setter
public class ProjectDto {

    private Long id;

    @NotBlank(message = Field.NAME + ConstraintViolationMessage.NOT_BLANK)
    private String name;
}
