package ca.concordia.soen344.observer.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Observable;
import java.util.Observer;

public class ChatConsole implements Runnable, Observer {
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final ChatAccess chatAccess;

    public ChatConsole(ChatAccess chatAccess) {
        this.chatAccess = chatAccess;
        chatAccess.addObserver(this);
    }

    @Override
    public void run() {
        try {
            while (true) {
                String input = br.readLine();
                chatAccess.send(input);
                if ("/quit".equals(input)) {
                    System.exit(0);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(arg.toString());
    }
}
