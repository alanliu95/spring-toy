package com.alan.java8.cf;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class WhenCompleteTest {
    public static void main(String[] args) {
        thenCombine();
    }
    public static void thenCombine(){
         CompletableFuture<String> result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (1 == 1) {
                throw new RuntimeException("测试一下异常情况");
            }

            return "hello ";
        }).whenComplete((s, t) -> {
            System.out.println("current result is :" +s);
            if(t != null){
                System.out.println("阶段执行过程中存在异常：");
                t.printStackTrace();
            }
        });

        try {
            System.out.println("final result:"+result.get()); //并不会执行
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
