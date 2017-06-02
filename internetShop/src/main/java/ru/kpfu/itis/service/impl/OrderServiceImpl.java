package ru.kpfu.itis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.form.GoodInOrderForm;
import ru.kpfu.itis.form.RestGoodInOrderPojo;
import ru.kpfu.itis.model.*;
import ru.kpfu.itis.model.enums.Status;
import ru.kpfu.itis.registration.MailMail;
import ru.kpfu.itis.repository.*;
import ru.kpfu.itis.service.OrderService;

import java.util.ArrayList;
import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    GoodOnHubRepository goodOnHubRepository;
    @Autowired
    GoodInOrderRepository goodInOrderRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    GoodsRepository goodsRepository;

    @Secured({"ROLE_BUYER", "ROLE_ADMIN"})
    @Override
    public void createNewOrder(User user, GoodInOrderForm goodInOrderForm) {
        List<GoodInOrder> arrayList = new ArrayList<>();
        List<GoodInOrder> arrayList2 = new ArrayList<>();

        if (getExistedOrder(user, Status.RECRUITING) != null) {
            Order order = getExistedOrder(user, Status.RECRUITING);
            arrayList.addAll(getAllRecruitingGoods(user, Status.RECRUITING));
            for (Good good : goodInOrderForm.getGoods()) {
                if (good.isAdded()) {
                    boolean exist = false;

                    for (GoodInOrder goodInOrder : arrayList) {
                        if (goodInOrder.getGood().getId() == good.getId()) {
                            goodInOrder.setCount(goodInOrder.getCount() + 1);
                            arrayList2.add(goodInOrder);
                            exist = true;
                        }
                    }
                    if (!exist) {
                        GoodInOrder goodInOrder = new GoodInOrder();
                        goodInOrder.setCount(1);
                        goodInOrder.setGood(goodsRepository.findOneById(good.getId()));
                        arrayList2.add(goodInOrder);
                    }
                }
            }
            order.setGoodInOrderList(arrayList2);
            orderRepository.save(order);
            for (GoodInOrder goodInOrder : arrayList2) {
                goodInOrder.setOrder(orderRepository.findOne(order.getId()));
                goodInOrderRepository.save(goodInOrder);
            }


        } else {
            Order order = new Order();
            order.setStatus(Status.RECRUITING);
            order.setUser(user);
            for (Good good : goodInOrderForm.getGoods()) {
                if (good.isAdded()) {
                    GoodInOrder goodInOrder = new GoodInOrder();
                    goodInOrder.setCount(1);
                    goodInOrder.setGood(goodsRepository.findOneById(good.getId()));
                    arrayList.add(goodInOrder);
                }
            }
            order.setGoodInOrderList(arrayList);
            orderRepository.save(order);
            for (GoodInOrder goodInOrder : arrayList) {
                goodInOrder.setOrder(orderRepository.findOne(order.getId()));
                goodInOrderRepository.save(goodInOrder);
            }
        }
    }

    @Secured({"ROLE_BUYER", "ROLE_ADMIN"})
    @Override
    public List<Order> getAllOrders(User user, Status status) {
        return orderRepository.findAllByUserAndStatus(user, status);
    }

    @Override
    public List<GoodInOrder> getAllOrders(String id) {
        return goodInOrderRepository.findAllByOrder(orderRepository.findOne(Long.parseLong(id)));
    }

    @Secured({"ROLE_BUYER", "ROLE_ADMIN"})
    @Override
    public List<GoodInOrder> getAllRecruitingGoods(User user, Status status) {
        List<GoodInOrder> goodInOrders = new ArrayList<>();
        Order order = orderRepository.findOneByUserAndStatus(user, status);
        goodInOrders.addAll(goodInOrderRepository.findAllByOrder(order));

        return goodInOrders;
    }

    @Secured({"ROLE_BUYER", "ROLE_ADMIN"})
    @Override
    public void saveNewCountOfGood(Good good, User user, int count) {
        Order order = orderRepository.findOneByUserAndStatus(user, Status.RECRUITING);
        GoodInOrder goodInOrder = goodInOrderRepository.findOneByOrderAndGood(order, good);
        goodInOrder.setCount(count);
        goodInOrderRepository.save(goodInOrder);

    }

    @Secured({"ROLE_BUYER", "ROLE_ADMIN"})
    @Override
    public Order getExistedOrder(User user, Status status) {
        return orderRepository.findOneByUserAndStatus(user, status);

    }

    @Override
    public Page<GoodInOrder> listAllByPage(Pageable pageable) {
        return goodInOrderRepository.findAll(pageable);
    }

    @Override
    public void saveAllRest(List<RestGoodInOrderPojo> list) {
        for (RestGoodInOrderPojo restPoj : list) {
            GoodInOrder g = goodInOrderRepository.findOne(restPoj.getId());
            g.setCount(Integer.parseInt(restPoj.getMaxCount()));
            Status status;
            switch (restPoj.getStatus()) {
                case "FORMATION":
                    status = Status.FORMATION;
                    break;
                case "DONE":
                    status = Status.DONE;
                    break;
                case "SENDING":
                    status = Status.SENDING;
                    break;
                case "RECRUITING":
                    status = Status.RECRUITING;
                    break;
                default:
                    status = g.getOrder().getStatus();
            }
            if (!g.getOrder().getStatus().equals(status)) {
                changeStatus(g.getOrder(), status);
                goodInOrderRepository.save(g);
                break;
            }
            goodInOrderRepository.save(g);
        }
    }

    @Secured({"ROLE_BUYER", "ROLE_ADMIN"})
    @Override
    public void changeStatus(Order order, Status status) {
        List<GoodInOrder> list = goodInOrderRepository.findAllByOrder(order);
        for (GoodInOrder goodInOrder : list) {
            int count = goodInOrder.getCount();
            for (GoodOnHub goodOnHub : goodOnHubRepository.findAllByGood(goodInOrder.getGood())) {
                count = count - goodOnHub.getMaxCount();
                if (count < 0) {
                    goodOnHub.setMaxCount(-count);
                } else {
                    goodOnHub.setMaxCount(0);
                }
                goodOnHubRepository.save(goodOnHub);
            }
        }
        ApplicationContext context =
                new ClassPathXmlApplicationContext("file:src/main/webapp/WEB-INF/Spring-Mail.xml");

        MailMail mm = (MailMail) context.getBean("mailMail");
        mm.sendMail("from@no-spam.com",
                order.getUser().getEmail(),
                "Your order",
                "Hi, your order is on the stage - " + status.toString());
        order.setStatus(status);
        orderRepository.save(order);
    }
}