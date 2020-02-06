package ca.concordia.soen344.observer.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ChatConsole implements Runnable {
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private final ChatAccess chatAccess;

	public ChatConsole(ChatAccess chatAccess) {
		this.chatAccess = chatAccess;
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
}
