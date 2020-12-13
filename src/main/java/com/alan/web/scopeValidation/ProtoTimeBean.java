package com.alan.web.scopeValidation;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component()
@Scope("prototype")
public class ProtoTimeBean implements ProtoTimeService{
    private LocalDateTime time=LocalDateTime.now();
    @Override
    public LocalDateTime getTime() {
        return time;
    }
}
