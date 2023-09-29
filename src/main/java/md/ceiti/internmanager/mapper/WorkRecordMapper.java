package md.ceiti.internmanager.mapper;

import lombok.RequiredArgsConstructor;
import md.ceiti.internmanager.dto.WorkRecordDto;
import md.ceiti.internmanager.entity.WorkRecord;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkRecordMapper {

    private final ModelMapper mapper;

    public WorkRecord toWorkRecord(WorkRecordDto workRecordDto) {
        return mapper.map(workRecordDto, WorkRecord.class);
    }

    public WorkRecordDto toWorkRecordDto(WorkRecord workRecord) {
        return mapper.map(workRecord, WorkRecordDto.class);
    }
}
