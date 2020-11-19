import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import static java.net.HttpURLConnection.HTTP_OK;

public class ChatteredHttpHandler implements HttpHandler {
    @Override
    public void handle ( HttpExchange exchange ) throws IOException
    {
        switch(exchange.getRequestMethod()){
            case "GET":

        }
        String response = "Server is running.";
        // create utf 8 buffer
        ByteBuffer buffer = StandardCharsets.UTF_8.encode(response) ;
        byte [] bytes = new byte [buffer.remaining()];
        System.out.println(buffer.toString());
        buffer.get(bytes);
        exchange.getResponseHeaders().set("Content - Type ","text / html ; charset =UTF -8");
        try {
            exchange.sendResponseHeaders (HTTP_OK,bytes.length) ;
        } catch (IOException e) {
            e.printStackTrace();
        }
        exchange.getResponseBody().write(bytes) ;
        exchange.close () ;
    }


}
