package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.form.GoodInOrderForm;
import ru.kpfu.itis.form.GoodsCountForm;
import ru.kpfu.itis.form.GoodsCreationForm;
import ru.kpfu.itis.model.Good;
import ru.kpfu.itis.model.GoodOnHub;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.enums.Status;
import ru.kpfu.itis.service.GoodsService;
import ru.kpfu.itis.service.HubService;
import ru.kpfu.itis.service.OrderService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;


@Controller
@RequestMapping(value = "/goods")
public class GoodsController {


    @Autowired
    HubService hubService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    OrderService orderService;


    @RequestMapping(value = "/adminGoodsPage", method = RequestMethod.GET)
    public String adminGoodsCreationPage(Model model) {
        model.addAttribute("goodform", new GoodsCreationForm());
        model.addAttribute("hubs", hubService.findAllHubs());
        return "adminGoodManipulation";
    }

    @RequestMapping(value = "/adminGoodsPage", method = RequestMethod.POST)
    public String adminGoodCreate(@ModelAttribute("goodform")  GoodsCreationForm form) {
        System.out.println(form.getHubId());
        goodsService.saveNewGood(form);
        return "redirect:/goods/allGoods";
    }

    @RequestMapping(value = "/goodMainPage", method = RequestMethod.GET)
    public String getGoodPage(Model model, @ModelAttribute("goodId") String goodId) {
        model.addAttribute("good", goodsService.getGoodById(goodId));
        return "goodPage";
    }

    @RequestMapping(value = "/allGoods", method = RequestMethod.GET)
    public String allGoodsPage(Model model) {
        GoodInOrderForm goodInOrderForm = new GoodInOrderForm();
        goodInOrderForm.setGoods(new ArrayList<>(goodsService.getAllDescriptionOfGoods()));
        HashSet<String> hashSet = new HashSet<>();
        ArrayList<GoodsCountForm> goodOnHubs = new ArrayList<>();

        for (Good good : goodInOrderForm.getGoods()) {
            hashSet.add(good.getCategory());
            GoodsCountForm goodsCountForm = new GoodsCountForm();
            goodsCountForm.setGood(good);
            int count = 0;
            for (GoodOnHub goodOnHub : hubService.findAllbyGood(good)) {
                count = count + goodOnHub.getMaxCount();
            }
            goodsCountForm.setAllCount(count);
            goodOnHubs.add(goodsCountForm);
        }


        model.addAttribute("goodsOnHub", goodOnHubs);
        model.addAttribute("categories", hashSet);
        model.addAttribute("goodForm", goodInOrderForm);
        return "allGoods";
    }

    @RequestMapping(value = "/addGoodToCart", method = RequestMethod.POST)
    public String addToCart(@ModelAttribute(value = "goodForm") GoodInOrderForm goodInOrderForm) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        orderService.createNewOrder(user, goodInOrderForm);
        return "redirect:/goods/basket";
    }


    @RequestMapping(value = "/basket", method = RequestMethod.GET)
    public String Cart(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("recruiting", orderService.getAllRecruitingGoods(user, Status.RECRUITING));
        return "basket";
    }

    @RequestMapping(value = "/ajax", method = RequestMethod.GET)
    @ResponseBody
    @Transactional
    public String Ajax(@RequestParam("id") String id, @RequestParam("count") String requiredCount) {
        GoodsCountForm goodsCountForm = new GoodsCountForm();
        int reCount = Integer.parseInt(requiredCount);

        goodsCountForm.setGood(goodsService.getGoodById(id));
        int count = 0;
        for (GoodOnHub goodOnHub : hubService.findAllbyGood(goodsService.getGoodById(id))) {
            count = count + goodOnHub.getMaxCount();
        }

        if (reCount <= count) {
            orderService.saveNewCountOfGood(goodsService.getGoodById(id),
                    (User) (SecurityContextHolder
                            .getContext()
                            .getAuthentication()
                            .getPrincipal()),
                             reCount);
        }
        return Integer.toString(count);
    }


    @RequestMapping(value = "/myOrders", method = RequestMethod.GET)
    public String MyOrders(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("orders", orderService.getAllOrders(user,Status.DONE));
        return "myOrders";
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String Order(Model model, @ModelAttribute(value = "id") String id) {
        model.addAttribute("orders", orderService.getAllOrders(id));
        return "detailedOrders";
    }

    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    @Transactional
    public String BuyOrder() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        orderService.changeStatus(orderService.getExistedOrder(user, Status.RECRUITING),Status.FORMATION);

        return "successOrder";
    }


    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String Search(Model model, @ModelAttribute(value = "name") String nameOfGood) {
        if (goodsService.getGoodsByName(nameOfGood).size() == 0) {
            model.addAttribute("error", true);
        }
        ArrayList<GoodsCountForm> goodOnHubs = new ArrayList<>();

        for (Good good : goodsService.getGoodsByName(nameOfGood)) {
            GoodsCountForm goodsCountForm = new GoodsCountForm();
            goodsCountForm.setGood(good);
            int count = 0;
            for (GoodOnHub goodOnHub : hubService.findAllbyGood(good)) {
                count = count + goodOnHub.getMaxCount();
            }
            goodsCountForm.setAllCount(count);
            goodOnHubs.add(goodsCountForm);
        }
        model.addAttribute("goodsOnHub", goodOnHubs);

        GoodInOrderForm goodInOrderForm = new GoodInOrderForm();
        goodInOrderForm.setGoods((ArrayList<Good>) goodsService.getGoodsByName(nameOfGood));
        model.addAttribute("goodForm", goodInOrderForm);
        return "allGoods";
    }


    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public String SearchByCategory(Model model, @ModelAttribute(value = "name") String nameOfCategory) {
        GoodInOrderForm goodInOrderForm = new GoodInOrderForm();
        goodInOrderForm.setGoods((ArrayList<Good>) goodsService.getGoodsByCategory(nameOfCategory));
        ArrayList<GoodsCountForm> goodOnHubs = new ArrayList<>();

        for (Good good : goodInOrderForm.getGoods()) {
            GoodsCountForm goodsCountForm = new GoodsCountForm();
            goodsCountForm.setGood(good);
            int count = 0;
            for (GoodOnHub goodOnHub : hubService.findAllbyGood(good)) {
                count = count + goodOnHub.getMaxCount();
            }
            goodsCountForm.setAllCount(count);
            goodOnHubs.add(goodsCountForm);
        }
        model.addAttribute("goodsOnHub", goodOnHubs);
        model.addAttribute("goodForm", goodInOrderForm);
        return "allGoods";
    }


}






