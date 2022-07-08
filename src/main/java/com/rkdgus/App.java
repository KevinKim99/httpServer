package com.rkdgus;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class App {
    public static void main( String[] args ) throws IOException {
        ServerSocket socket = new ServerSocket(9000);
        while(true){
            Socket soc1 = socket.accept();
        

            InputStream is1 = soc1.getInputStream();
            OutputStream os1 = soc1.getOutputStream();
            byte[] data = new byte[500];
    
            is1.read(data);
            String asd = new String(data);
            String[] sAr = asd.split("\n");
            String[] xv = sAr[0].split(" ");
    
            System.out.println(xv[1]);
            String path = xv[1];
            //      /index  /hello
            //     404 Not Found
            File fl = null;
            String resourcesPath = "/Users/kevinkim/Desktop/HTTP Project/httpserver/src/main/resources";
            String statusCode = "200 Ok";
            if(path.equalsIgnoreCase("/index")){
                fl = new File(resourcesPath + "/index.html");
            }
            else if(path.equalsIgnoreCase("/hello")){
                fl = new File(resourcesPath + "/hello.html");
            }
            else{
                fl = new File(resourcesPath + "/body.html");
                statusCode = "404 Not Found";
            }
            FileReader fr = new FileReader(fl);
            BufferedReader br = new BufferedReader(fr);
            String a = "";
            String tmp = "";
            while((tmp = br.readLine()) != null){
                a += tmp + "\n";
            }
           
            is1.read(data);
            String body = a;
            os1.write(("HTTP/1.1 " + statusCode + "\n"+
                    "Connection: close\n"+
                    "Content-Type: text/html\n"+
                    "Content-Length: "+ body.getBytes().length + "\n\n" +
                    body).getBytes());
        }
        

        
    }
}
