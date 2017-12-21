package org.b2m.lostandfound;

import org.b2m.lostandfound.persist.ItemDao;
import org.b2m.lostandfound.persist.LostPropertyDao;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestApiController {
    List<ItemDao> findDaoList ;
    LostPropertyDao lostPropertyDao = new LostPropertyDao();
    /* To call this request we have should use following path:
       http://localhost:8080/request?city="cityName"&desc="description"
       This is GET method and  will list all Items suited to your request
       with given city name and description from database
     */
    @RequestMapping(value = "/request",
    params = {"city","desc"},
    method = RequestMethod.GET)
    public List<ItemDao> request(
            @RequestParam(value = "city") String cityName,
            @RequestParam(value = "desc") String descName) {
        findDaoList = null;
        findDaoList = lostPropertyDao.findByItemDescription(descName,cityName);
        return findDaoList;
    }
    /* To call this request we have should use following path:
       http://localhost:8080/request?city="cityName"
       This is GET method and will list all Items suited to your request
       with given city name from database
     */
    @RequestMapping(value = "/request",
            params = {"city"},
            method = RequestMethod.GET)
    public List<ItemDao> request(
            @RequestParam(value = "city") String cityName) {
        findDaoList = null;
        findDaoList = lostPropertyDao.returnAllItems(cityName);
        return findDaoList;
    }

}