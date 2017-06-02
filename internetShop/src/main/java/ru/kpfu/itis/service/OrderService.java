package ru.kpfu.itis.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.form.GoodInOrderForm;
import ru.kpfu.itis.form.RestGoodInOrderPojo;
import ru.kpfu.itis.form.RestGoodOnHubPojo;
import ru.kpfu.itis.model.*;
import ru.kpfu.itis.model.enums.Status;

import java.util.List;

@Service
public interface OrderService {

    void createNewOrder(User user, GoodInOrderForm goodInOrderForm);

    List<Order> getAllOrders(User user,Status status);

    List<GoodInOrder> getAllOrders(String id);

    void changeStatus(Order order, Status status);

    void saveNewCountOfGood(Good good, User user, int count);

    Order getExistedOrder(User user, Status status);

    List<GoodInOrder> getAllRecruitingGoods(User user, Status status);

    Page<GoodInOrder> listAllByPage(Pageable pageable);


    void saveAllRest(List<RestGoodInOrderPojo> list);
}
