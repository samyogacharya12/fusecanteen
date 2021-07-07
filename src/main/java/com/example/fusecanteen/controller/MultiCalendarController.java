package com.example.fusecanteen.controller;

import com.example.fusecanteen.dto.MultiCalendarDto;
import com.example.fusecanteen.errors.Invalid;
import com.example.fusecanteen.service.MultiCalendarService;
import com.example.fusecanteen.utility.SecurityUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;

@RestController
@RequestMapping("/auth")
public class MultiCalendarController {


    private static final Logger log = LoggerFactory.getLogger(MultiCalendarController.class);


    private final MultiCalendarService multiCalendarService;


    public MultiCalendarController(MultiCalendarService multiCalendarService) {
        this.multiCalendarService = multiCalendarService;
    }


    @PostMapping("/multicalendar")
    public ResponseEntity<MultiCalendarDto> save(@RequestBody MultiCalendarDto multiCalendarDto) {
        log.debug("Generating Multi Calender");
        if (multiCalendarDto.getId() != null) {
            throw new Invalid("New Calendar Cannot already have an id",
                    multiCalendarDto);
        }
        multiCalendarDto.setCreatedDate(ZonedDateTime.now());
        multiCalendarDto.setUpdatedDate(ZonedDateTime.now());
        System.out.println(SecurityUtility.getUserName());
        multiCalendarDto.setCreatedBy(SecurityUtility.getUserName());
        multiCalendarDto.setLastModifiedBy(SecurityUtility.getUserName());
        multiCalendarDto = multiCalendarService.save(multiCalendarDto);
        return ResponseEntity.ok().body(multiCalendarDto);

    }


}
