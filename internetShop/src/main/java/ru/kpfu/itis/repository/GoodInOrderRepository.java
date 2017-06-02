package ru.kpfu.itis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.model.*;

import java.util.List;

@Repository
public interface GoodInOrderRepository extends JpaRepository<GoodInOrder, Long> {

    List<GoodInOrder> findAllByOrder(Order order);

    GoodInOrder findOneByOrderAndGood(Order order, Good good);


}
