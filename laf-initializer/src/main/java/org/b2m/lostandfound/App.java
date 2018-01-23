package org.b2m.lostandfound;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/**
 * Klasa App służy do inicjalizacji całego serwisu, Po jej wywołaniu zostają najpierw dodane biura rzeczy znalezionych,
 * a następnie zostaje wystawiony Rest Api oraz scheduler do regularnego sprawdzania czy dane w biurach nie zostały
 * zaktualizowane. Tag {@code @SpringBootApplication} pozwala łatwo wystawić RestApi przy pomocy Spring Boot.
 */
public class App {
    public static void main(String[] args) {
        /* Add offices to database */
        LostPropertyOffice wawOffice = new LostPropertyOffice("Warszawa", "Warszawa", "01-000", "123456789","waw@waw.pl");
        LostPropertyOffice krkOffice = new LostPropertyOffice("Kraków", "Kraków", "06-111", "111222333", "krk@krk.pl");
        LostPropertyOffice gdOffice = new LostPropertyOffice("Gdańsk", "Gdańsk", "05-056", "567654567", "gd@gd.com.pl");

        Service service = new Service();
        if (service.findLostPropertyOffice("Warszawa") == null)
            service.addLostPropertyOffice(wawOffice);
        if (service.findLostPropertyOffice("Kraków") == null)
            service.addLostPropertyOffice(krkOffice);
        if (service.findLostPropertyOffice("Gdańsk") == null)
            service.addLostPropertyOffice(gdOffice);
        /*Start scheduler*/
        Scheduler scheduler = new Scheduler();
        scheduler.update();


        /*Start REST Api */
        SpringApplication.run(RestApiController.class, args);
    }
}
