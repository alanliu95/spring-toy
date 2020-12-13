package com.alan.thread;

import com.alan.commonUtil.ProcessResult;

import java.util.concurrent.*;

public class RpcProcess<T> {
    public static final int DEFAULT_MAX_DELAY_TIME = 3000;
    int time_out_max;
    String msg;
    Future<T> future;


    public RpcProcess(ExecutorService executor, Callable<T> task, String msg, int time_out_max) {
        this.time_out_max = time_out_max;
        this.msg = msg;
        future = executor.submit(task);
    }

    public RpcProcess(ExecutorService executor, Callable<T> task, String msg) {
        this.time_out_max = DEFAULT_MAX_DELAY_TIME;
        this.msg = msg;
        future = executor.submit(task);
    }

    ProcessResult<T> get() {
        T result;
        try {
            result = future.get(time_out_max, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | TimeoutException e) {
            e.printStackTrace();
            return ProcessResult.fail(msg);
        }catch (ExecutionException e){
            e.printStackTrace();
            return ProcessResult.fail("ExecutionException");
        }
        return ProcessResult.success(result);
    }
}
