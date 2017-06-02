package ru.kpfu.itis.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.kpfu.itis.form.RestGoodInOrderPojo;
import ru.kpfu.itis.form.RestGoodOnHubPojo;
import ru.kpfu.itis.model.GoodInOrder;
import ru.kpfu.itis.model.GoodOnHub;
import ru.kpfu.itis.service.HubService;
import ru.kpfu.itis.service.OrderService;

import java.util.List;


@RestController
@RequestMapping(value = "/rest")
public class ApiController {

    @Autowired
    HubService hubService;
    @Autowired
    OrderService orderService;


    @RequestMapping(value = "/hub", method = RequestMethod.GET)
    Page<GoodOnHub> list(Pageable pageable) {
        Page<GoodOnHub> persons = hubService.listAllByPage(pageable);
        return persons;
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    Page<GoodInOrder> orders(Pageable pageable) {
        Page<GoodInOrder> persons = orderService.listAllByPage(pageable);
        return persons;
    }


    @RequestMapping(value = "/saveMe", method = RequestMethod.POST)
    void changeGoodOnHub(@RequestBody List<RestGoodOnHubPojo> goodOnHubList) {
        System.out.println(goodOnHubList.size());
        if (goodOnHubList.size() != 0) {
            hubService.saveAllRest(goodOnHubList);
        }
    }

    @RequestMapping(value = "/saveMe2table", method = RequestMethod.POST)
    void changeGoodinOrder(@RequestBody List<RestGoodInOrderPojo> goodInOrderPojos) {
        System.out.println(goodInOrderPojos.size());
        if (goodInOrderPojos.size() != 0) {
            orderService.saveAllRest(goodInOrderPojos);
        }
    }


}

