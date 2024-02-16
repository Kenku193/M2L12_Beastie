package org.example;

public class MoneySender extends Thread{

    public static void main(String[] args) {

        Account w1 = new Account();
        Account w2 = new Account();

        MoneySender moneySender1 = new MoneySender(w1, w2, 10);
        MoneySender moneySender2 = new MoneySender(w2, w1, 15);

        moneySender1.start();
        moneySender2.start();

    }

    private Account from;
    private Account to;
    private Integer amount;

    public MoneySender(Account from, Account to, Integer amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    @Override
    public void run() {
        synchronized (from){ //!!!! поток 1 захватил - w1, а поток 2 захватил - w2
            from.money -= amount; // С КОШЕЛЬКА СПИСАТЬ
        }
        synchronized (to){
            to.money += amount; // НА КОШЕЛЕК ДОБАВИТЬ
            System.out.println("ПЛАТЕЖ ПРОВЕДЕН НА СУММУ " + amount);
        }
    }
}

class Account {
    Double money; // СКОЛЬКО НА СЧЕТУ СЕЙЧАС
}
