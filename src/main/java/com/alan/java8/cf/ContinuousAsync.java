package com.alan.java8.cf;

import java.util.concurrent.*;

public class ContinuousAsync {
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        ExecutorService executorService = new ThreadPoolExecutor(2, 2,
                5000L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        CompletableFuture<ProcessResult<Integer>> future = CompletableFuture.supplyAsync(() -> Task.add(1, 2))
                .whenComplete((r, e) -> {
                    Task.selfAdd(r);
                    System.out.println("当前结果" + r);
                    if (e != null) e.printStackTrace();
                });
//        .exceptionally(throwable -> {//throwable.printStackTrace();
//            System.out.println("发生异常");
//        return new ProcessResult<>(0);});
        System.out.println(future.get());
    }
}

class Task {
    public static ProcessResult<Integer> add(int a, int b) {
//        if(a==1)
//            throw new RuntimeException("a=1");
        return new ProcessResult<>(a + b);
    }

    public static ProcessResult<Integer> selfAdd(ProcessResult<Integer> r) {
        if (r != null)
            throw new RuntimeException("空指针");
        r.setResult(r.getResult() + 1);
        return r;
    }
}

class ProcessResult<T> {
    T result;

    public ProcessResult(T t) {
        this.result = t;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ProcessResult{" +
                "result=" + result +
                '}';
    }
}
