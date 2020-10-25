package com.hoult.zk.onoffline;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimerSerice extends Thread {

    private int port=0;

    public TimerSerice(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        //通过socker与client进行交流，启动serversocker监听请求
        try {
            final ServerSocket serverSocket = new ServerSocket(port);

            while(true) {
                final Socket socket = serverSocket.accept();
                final OutputStream out = socket.getOutputStream();
                String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                out.write(date.getBytes());
            }

            } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
