package ca.concordia.soen344.observer.socket;

public class ChatConsoleClient {

    public static void main(String[] args) {
        Thread thread = new Thread(new ChatConsole());
        thread.start();
    }

}
