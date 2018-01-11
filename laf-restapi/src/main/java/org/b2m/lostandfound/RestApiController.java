package org.b2m.lostandfound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@SpringBootApplication
public class RestApiController {
    List<ItemInRepository> findDaoList ;
    private final Service Service;
    @Autowired
    public RestApiController(org.b2m.lostandfound.Service service) {
        Service = service;
    }


    /** To call this request we have should use following path:
       http://localhost:8080/request?city=cityName&desc=description
       This is GET method and  will list all Items suited to your request
       with given city name and description from database
     */
    @CrossOrigin(origins = "http://localhost:63342")
    @RequestMapping(value = "/request",
    params = {"city","desc"},
    method = RequestMethod.GET)
    public List<ItemInRepository> request(
            @RequestParam(value = "city") String cityName,
            @RequestParam(value = "desc") String descName) {
        findDaoList = new ArrayList<>();
        findDaoList = Service.findItems(descName,cityName);
        return findDaoList;
    }
    /** To call this request we have should use following path:
       http://localhost:8080/request?city=cityName
       This is GET method and will list all Items suited to your request
       with given city name from database
     */
    @CrossOrigin(origins = "http://localhost:63342")
    @RequestMapping(value = "/request",
            params = {"city"},
            method = RequestMethod.GET)
    public List<ItemInRepository> request(
            @RequestParam(value = "city") String cityName) {
        findDaoList = new ArrayList<>();
        findDaoList = Service.returnAllItemsFromOffice(cityName);
        return findDaoList;
    }

    @CrossOrigin(origins = "http://localhost:63342")
    @RequestMapping(value = "/request",
            method = RequestMethod.GET)
    public List<ItemInRepository> request() {
        findDaoList = new ArrayList<>();
        findDaoList = Service.returnAllItems();
        return findDaoList;
    }
}