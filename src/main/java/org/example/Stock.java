package org.example;

import java.util.ArrayList;
import java.util.List;

public class Stock { // БИРЖА

    public static Integer fee = 0; // КОМИССИЯ ЗА УСЛУГИ
    public static final Object monitor = new Object();


    public static void main(String[] args) {



        List<Task> tasks = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            Task task = new Task();
            tasks.add(task);
            task.start();
            System.out.println(Stock.fee);
        }
        System.out.println("All started");


        for (Task task : tasks) {
            try {
                task.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println(fee);

    }

}

class Task extends Thread {
    @Override
    public void run() {
        try {
            Thread.sleep(1);
            Thread.yield(); // ТОТ ЖЕ sleep НО РАБОТАЕТ 1 ТАКТ ПРОЦЕССОРА
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Все поспали, поели и пошли в туалет!");

        synchronized (Stock.monitor){ // <- Это дверь в туалет с замком, а замок - это Stock.o

            Stock.fee+=getOneFee();

//            try {
//                Stock.monitor.wait();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }

        }

    }

    private Integer getOneFee(){
        return 1;
    }

}