package com.example.myproject;

import com.google.common.math.BigDecimalMath;
import com.google.common.util.concurrent.ListenableFutureTask;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TcTransitiveImports {

    private ListenableFutureTask<Double> getListenableTask(int time) { // Has Transitives
        return ListenableFutureTask.create(() -> {
            BigDecimal a = BigDecimal.valueOf(Math.random());
            BigDecimal b = BigDecimal.valueOf(Math.random());

            Thread.sleep(time * 1000);
            System.out.println("SomeOutput " + BigDecimalMath.roundToDouble(a.add(b), RoundingMode.HALF_UP));
            return BigDecimalMath.roundToDouble(a.add(b), RoundingMode.HALF_UP);
        });
    }

    public void useSomeRandomInternalLibrary() {
        System.out.println("Using Some Internal Function with Transitive Dependencies");
        ExecutorService execService = Executors.newSingleThreadExecutor();
        ListeningExecutorService lExecService = MoreExecutors.listeningDecorator(execService); // Has Transitives

        lExecService.submit(getListenableTask(5));
    }
}
