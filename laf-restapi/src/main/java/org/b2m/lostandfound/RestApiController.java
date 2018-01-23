package org.b2m.lostandfound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;



@RestController
@SpringBootApplication
/**
 * Ta klasa korzystając z tagu {@code @RestController} linkuje inicjalizację serwisu Rest na port 8080 na który można
 * zadawać zapytania dotyczące rzeczy znajdujących się w bazie danych całej usługi. Została tutaj wykorzystana metoda GET
 * która pozwala wysyłać do użytkownika odpowiedzi na jego zapytania.
 */
public class RestApiController {
    List<ItemInRepository> findDaoList ;
    private final Service Service;
    /**
     * Jedyny konstruktor w tej klasie który przypisuje serwis z którego mają być pobierane odpowiedzi
     * @param service Serwis który zostaje przypisany do prywatnego pola charakteryzującego serwis dla Rest Api
     */
    @Autowired
    public RestApiController(org.b2m.lostandfound.Service service) {
        Service = service;
    }

    /**
     * Ta metoda służy do zwrócenia przedmiotów pasujących do podanego opisu w wybranym mieście.
     * Zapytania dokonujemy poprzez {@code http://localhost:8080/request?city=cityName&desc=description}
     * @param cityName Parametr podany w url mówiący o nazwie miasta
     * @param descName Parametr podany w url mówiący o opisie przedmiotu
     * @return Lista przedmiotów pasujaca do zadanego zapytania
     */
    @CrossOrigin(origins = "http://localhost:63342")
    @RequestMapping(value = "/request",
    params = {"city","desc"},
    method = RequestMethod.GET)
    public List<ItemInRest> request(
            @RequestParam(value = "city") String cityName,
            @RequestParam(value = "desc") String descName) {
        findDaoList = new ArrayList<>();
        findDaoList = Service.findItems(descName,cityName);
        List<ItemInRest> itemInRestList = new LinkedList<>();
        for (ItemInRepository itemInRepository: findDaoList) {
            itemInRestList.add(new ItemInRest(itemInRepository));
        }
        return itemInRestList;
    }

    /**
     * Ta motoda służy do zwrócenia wszystkich przedmiotów z określonego miasta.
     * Zapytanie wykonujemy poprzez {@code http://localhost:8080/request?city=cityName}
     * @param cityName Parametr podany w url mówiący o nazwie miasta
     * @return Lista przedmiotów pasujaca do zadanego zapytania
     */
    @CrossOrigin(origins = "http://localhost:63342")
    @RequestMapping(value = "/request",
            params = {"city"},
            method = RequestMethod.GET)
    public List<ItemInRest> request(
            @RequestParam(value = "city") String cityName) {
        findDaoList = new ArrayList<>();
        findDaoList = Service.returnAllItemsFromOffice(cityName);
        List<ItemInRest> itemInRestList = new LinkedList<>();
        for (ItemInRepository itemInRepository: findDaoList) {
            itemInRestList.add(new ItemInRest(itemInRepository));
        }
        return itemInRestList;
    }


    /**
     * Ta metoda służy do zwrócenia wszystkich przedmiotów znajdujacych sie z bazie.
     * Zapytania dokunujemy poprzez {@code http://localhost:8080/request}
     * @return Lista przedmiotów pasujaca do zadanego zapytania
     */
    @CrossOrigin(origins = "http://localhost:63342")
    @RequestMapping(value = "/request",
            method = RequestMethod.GET)
    public List<ItemInRest> request() {
        findDaoList = new ArrayList<>();
        findDaoList = Service.returnAllItems();
        List<ItemInRest> itemInRestList = new LinkedList<>();
        for (ItemInRepository itemInRepository: findDaoList) {
            itemInRestList.add(new ItemInRest(itemInRepository));
        }
        return itemInRestList;
    }
}