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
    @RequestMapping(value = "/request",
    params = {"city","desc"},
    method = RequestMethod.GET)
    public List<ItemDao> request(
            @RequestParam(value = "city") String cityName,
            @RequestParam(value = "desc") String descName) {
        findDaoList = null;
        findDaoList = lostPropertyDao.findByItemDescription(descName,cityName);
        return findDaoList  ;
    }

}