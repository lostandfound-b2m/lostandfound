package org.b2m.lostandfound;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;

@SpringBootApplication
public class App {

    public static void main( String[] args ) throws IOException {
        //Service service = new Service();
        //Retriever retrieverGd = new GdRetriever();
        //service.addItems(service.simpleGetList(retrieverGd));
        Scheduler scheduler = new Scheduler();
        scheduler.update();
    }
}
