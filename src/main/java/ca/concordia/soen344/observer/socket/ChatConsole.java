package ca.concordia.soen344.observer.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ChatConsole implements Runnable {
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public void run() {
        try {
            while (true) {
                String input = br.readLine();
                System.out.println("echo: " + input);
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
}
