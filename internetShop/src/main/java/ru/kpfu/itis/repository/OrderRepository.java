package ru.kpfu.itis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.model.*;
import ru.kpfu.itis.model.enums.Status;

import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {


    List<Order> findAllByUserAndStatus(User user,Status status);

    Order findOneByUserAndStatus(User user, Status status);






}
