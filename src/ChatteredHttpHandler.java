import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

import static java.net.HttpURLConnection.HTTP_OK;

public class ChatteredHttpHandler implements HttpHandler {
    @Override
    public void handle ( HttpExchange exchange ) throws IOException
    {
        String response = "Server is running.";
        List<String> chat = new LinkedList<String>() ;
        chat.add(response);
        switch(exchange.getRequestMethod()){
            case "GET":
                ByteBuffer buffer = StandardCharsets.UTF_8.encode(response) ;
                byte [] bytes = new byte [buffer.remaining()];
                buffer.get(bytes);
                exchange.getResponseHeaders().set("Content - Type ","text / html ; charset =UTF -8");
                try {
                    exchange.sendResponseHeaders (HTTP_OK,bytes.length) ;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                exchange.getResponseBody().write(bytes) ;
                exchange.close () ;
                break;
            case "POST":



        }
       /* for (int i = 0; i < chat.size(); i++){

        }*/
        // create utf 8 buffer


    }


}
