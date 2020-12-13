package com.alan.thread;

import com.alan.commonUtil.ProcessResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProcessTest {
    static protected ExecutorService threadPool = Executors.newFixedThreadPool(5);
    @Autowired
    ObjectMapper jsonMapper;

    @Test
    public void test() throws JsonProcessingException {
        Callable<String> task = () -> {
            Thread.sleep(1000);
            if (System.currentTimeMillis() > 0) throw new IOException();
            return "orderInfo";
        };
        RpcProcess<String> process = new RpcProcess<>(threadPool, task, "orderException");
        ProcessResult<String> result = process.get();
        System.out.println(jsonMapper.writeValueAsString(result));
    }
}
