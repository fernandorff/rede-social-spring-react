package com.redesocial.service._core;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
public class NowService {

    public LocalDate getDate() {
        return LocalDate.now();
    }

    public LocalDateTime getDateTime() {
        return LocalDateTime.now();
    }

}
