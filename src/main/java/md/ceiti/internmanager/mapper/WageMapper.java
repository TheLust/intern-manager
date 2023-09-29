package md.ceiti.internmanager.mapper;

import lombok.RequiredArgsConstructor;
import md.ceiti.internmanager.dto.WageDto;
import md.ceiti.internmanager.entity.Wage;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WageMapper {

    private final ModelMapper mapper;

    public Wage toWage(WageDto wageDto) {
        return mapper.map(wageDto, Wage.class);
    }

    public WageDto toWageDto(Wage wage) {
        return mapper.map(wage, WageDto.class);
    }
}
