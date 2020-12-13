package com.alan.web.scopeValidation;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Scope("singleton")
public class SingleTimeBean implements SingleTimeService{
    public static final LocalDateTime time=LocalDateTime.now();
    @Override
    public LocalDateTime getTime() {
        return time;
    }
}
