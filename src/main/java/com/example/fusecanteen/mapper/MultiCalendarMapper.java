package com.example.fusecanteen.mapper;

import com.example.fusecanteen.dto.MultiCalendarDto;
import com.example.fusecanteen.entity.MultiCalendar;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Service
@Mapper(componentModel = "spring")
public interface MultiCalendarMapper extends EntityMapper<MultiCalendarDto, MultiCalendar> {




   MultiCalendarDto toDto(MultiCalendar multiCalendar);


   MultiCalendar toEntity(MultiCalendarDto multiCalendarDto);


}
