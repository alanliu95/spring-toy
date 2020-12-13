package com.alan.web.scopeValidation;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.format.DateTimeFormatter;

@RestController
public class CurrentTimeController implements ApplicationContextAware {
    @Autowired
    private SingleTimeService timeService;
//    @Autowired
//    private ProtoTimeService protoTimeService;
    @GetMapping("/scope/single")
    public String getS(){
        return timeService.getTime().format(DateTimeFormatter.ISO_DATE_TIME);
    }
    @GetMapping("/scope/proto")
    public String getP(){
        ProtoTimeService protoTimeService=applicationContext.getBean(ProtoTimeService.class);
        return protoTimeService.getTime().format(DateTimeFormatter.ISO_DATE_TIME);
    }
    private ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }
}
