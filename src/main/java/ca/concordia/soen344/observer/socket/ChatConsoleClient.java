package ca.concordia.soen344.observer.socket;

import java.io.IOException;

public class ChatConsoleClient {

    private final static String SERVER_ADDRESS = "127.0.0.1";
    private final static int SERVER_PORT = 2222;

    public static void main(String[] args) {
        try {
            ChatAccess access = new ChatAccess();
            Thread thread = new Thread(new ChatConsole(access));
            thread.start();

            access.initSocket(SERVER_ADDRESS, SERVER_PORT);
        } catch (IOException ex) {
            System.out.println("Cannot connect to " + SERVER_ADDRESS + ":" + SERVER_PORT);
            ex.printStackTrace();
        } catch (Exception ex) {
            System.out.println("Error in client application");
        }
    }

}
