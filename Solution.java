package com.javarush.task.task28.task2806;

import java.util.concurrent.*;

/* 
Знакомство с Executors
*/
public class Solution {
    public static void main(String[] args) throws InterruptedException {
        //В методе main создай фиксированный пул из 5 трэдов используя класс Executors.
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        //В цикле отправь на исполнение в пул 10 задач Runnable.
        for (int i = 0; i < 10; i++) {
            int finalI = i+1;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    //У каждой задачи в методе run вызови метод doExpensiveOperation
                    // с порядковым номером задачи начиная с 1, см. пример вывода.
                    doExpensiveOperation(finalI);
                }
            });
        }
        //Запрети добавление новых задач на исполнение в пул (метод shutdown).
        executorService.shutdown();
        //Дай пулу 5 секунд на завершение всех задач (метод awaitTermination и параметр TimeUnit.SECONDS).
        executorService.awaitTermination(5,TimeUnit.SECONDS);

        /* output example
pool-1-thread-2, localId=2
pool-1-thread-1, localId=1
pool-1-thread-3, localId=3
pool-1-thread-1, localId=7
pool-1-thread-1, localId=9
pool-1-thread-4, localId=4
pool-1-thread-5, localId=5
pool-1-thread-2, localId=6
pool-1-thread-1, localId=10
pool-1-thread-3, localId=8
         */
    }

    private static void doExpensiveOperation(int localId) {
        System.out.println(Thread.currentThread().getName() + ", localId="+localId);
    }
}
