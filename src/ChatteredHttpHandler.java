import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpPrincipal;

import java.io.IOException;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

import static java.net.HttpURLConnection.HTTP_OK;

public class ChatteredHttpHandler implements HttpHandler {
    @Override
    public void handle ( HttpExchange exchange ) throws IOException
    {

        List<String> chat = new LinkedList<String>() ;
        chat.add("INIT");
        printRequestInfo(exchange);
        switch(exchange.getRequestMethod()) {
            case "GET":

                for( int i= 0; 1< chat.size(); i++){
                    String response = chat.get(i);
                    ByteBuffer buffer = StandardCharsets.UTF_8.encode(response);
                    byte[] bytes = new byte[buffer.remaining()];
                    buffer.get(bytes);
                    exchange.getResponseHeaders().set("Content - Type ", "text / html ; charset =UTF -8");
                    try {
                        exchange.sendResponseHeaders(HTTP_OK, bytes.length);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    exchange.getResponseBody().write(bytes);
                    exchange.close();
                }

                /*ByteBuffer buffer = StandardCharsets.UTF_8.encode(response);
                byte[] bytes = new byte[buffer.remaining()];
                buffer.get(bytes);*/

                break;
            case "POST":
                URI requestURI = exchange.getRequestURI();
                String query = requestURI.getQuery();
                chat.add(query);



        }
        }
        public void printRequestInfo (HttpExchange exchange) {
        System.out.println("-- headers --");
        Headers requestHeaders = exchange.getRequestHeaders();
        requestHeaders.entrySet().forEach(System.out::println);

        System.out.println("-- principle --");
        HttpPrincipal principal = exchange.getPrincipal();
        System.out.println(principal);

        System.out.println("-- HTTP method --");
        String requestMethod = exchange.getRequestMethod();
        System.out.println(requestMethod);

        System.out.println("-- query --");
        URI requestURI = exchange.getRequestURI();
        String query = requestURI.getQuery();
        System.out.println(query);
    }

}
