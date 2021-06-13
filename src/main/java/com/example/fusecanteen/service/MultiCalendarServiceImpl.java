package com.example.fusecanteen.service;

import com.example.fusecanteen.dto.MultiCalendarDto;
import com.example.fusecanteen.entity.MultiCalendar;
import com.example.fusecanteen.enumconstant.Days;
import com.example.fusecanteen.enumconstant.Months;
import com.example.fusecanteen.mapper.MultiCalendarMapper;
import com.example.fusecanteen.repository.MultiCalendarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class MultiCalendarServiceImpl implements MultiCalendarService {


    private static final Logger log = LoggerFactory.getLogger(MultiCalendarServiceImpl.class);


    private final MultiCalendarMapper multiCalendarMapper;


    private final MultiCalendarRepository multiCalendarRepository;


    public MultiCalendarServiceImpl(MultiCalendarMapper multiCalendarMapper,
                                    MultiCalendarRepository multiCalendarRepository) {
        this.multiCalendarMapper = multiCalendarMapper;
        this.multiCalendarRepository = multiCalendarRepository;
    }


    @Override
    public MultiCalendarDto save(MultiCalendarDto multiCalendarDto) {
        log.debug("Generating Calendar");
        try {
            AtomicReference<LocalDate> todayDate = new AtomicReference<>(
                    multiCalendarDto.getAdDate().minusDays(1));
            AtomicReference<String> bsDate = new AtomicReference<>(multiCalendarDto.getBsDate());
            AtomicReference<MultiCalendarDto> multiCalendarDtoAtomicReference =
                    new AtomicReference<>(new MultiCalendarDto());
            multiCalendarDto.getMultiCalendarMonthList().forEach(multiCalendarMonth -> {
                MultiCalendarDto processMultiCalendar = new MultiCalendarDto();
                switch (multiCalendarMonth.getMonth()) {
                    case "BAISHAKH":
                        processMultiCalendar.setMonth("BAISHAKH");
                        break;

                    case "JESTHA":
                        processMultiCalendar.setMonth("JESTHA");
                        break;

                    case "ASHAD":
                        processMultiCalendar.setMonth("ASHAD");
                        break;

                    case "SHRAWAN":
                        processMultiCalendar.setMonth("SHRAWAN");
                        break;

                    case "BHADRA":
                        processMultiCalendar.setMonth("BHADRA");
                        break;

                    case "ASHOJ":
                        processMultiCalendar.setMonth("ASHOJ");
                        break;

                    case "KARTIK":
                        processMultiCalendar.setMonth("KARTIK");
                        break;

                    case "MANGSIR":
                        processMultiCalendar.setMonth("MANGSIR");
                        break;

                    case "POUSH":
                        processMultiCalendar.setMonth("POUSH");
                        break;

                    case "MAGH":
                        processMultiCalendar.setMonth("MAGH");
                        break;

                    case "FALGUN":
                        processMultiCalendar.setMonth("FALGUN");
                        break;

                    case "CHAITRA":
                        processMultiCalendar.setMonth("CHAITRA");
                        break;

                    default:
                        break;
                }
                processMultiCalendar.setTotalDays(multiCalendarMonth.getTotalDays());
                processMultiCalendar.setAdDate(multiCalendarDto.getAdDate());
                processMultiCalendar.setBsDate(bsDate.get());
                processMultiCalendar.setTodayDate(todayDate.get());
                processMultiCalendar = processDays(processMultiCalendar);
                if (Objects.nonNull(processMultiCalendar.getTodayDate())) {
                    todayDate.set(processMultiCalendar.getTodayDate());
                }
                bsDate.set(processMultiCalendar.getBsDate());
                multiCalendarDtoAtomicReference.set(processMultiCalendar);
            });
            return multiCalendarDtoAtomicReference.get();


        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }


    private MultiCalendarDto processDays(MultiCalendarDto adBsDateDTO) {
        LocalDate lastAdDate = adBsDateDTO.getTodayDate();
        String bsDate = adBsDateDTO.getBsDate();


        Months month = Months.valueOf(adBsDateDTO.getMonth());

        int monthCounter = month.ordinal() + 1;

        String strMonth;

        if (monthCounter <= 9) strMonth = "0" + monthCounter;
        else strMonth = String.valueOf(monthCounter);

        int bsYear = Integer.parseInt(bsDate.substring(0, 4));


        for (int i = 1; i <= adBsDateDTO.getTotalDays(); i++) {

            Days day = Days.valueOf(lastAdDate.getDayOfWeek().name());
            lastAdDate = lastAdDate.plusDays(1);

            String strDay = "";

            if (i <= 9) strDay = "0" + i;
            else strDay = String.valueOf(i);

            bsDate = (bsYear) + "-" + strMonth + "-" + strDay;

            MultiCalendar savingRelate = new MultiCalendar();
            savingRelate.setAdDate(lastAdDate);
            savingRelate.setBsDate(bsDate);
            savingRelate.setMonths(month);
            savingRelate.setCreatedDate(ZonedDateTime.now());
            savingRelate.setUpdatedDate(ZonedDateTime.now());
            savingRelate.setStatus(true);


            savingRelate.setDays(day);
            savingRelate = multiCalendarRepository.save(savingRelate);
            MultiCalendarDto result = multiCalendarMapper.toDto(savingRelate);
            result.setTodayDate(lastAdDate);
            result.setBsDate(bsDate);

            if (i == adBsDateDTO.getTotalDays()) {
                return result;
            }
        }
        return null;
    }


}
