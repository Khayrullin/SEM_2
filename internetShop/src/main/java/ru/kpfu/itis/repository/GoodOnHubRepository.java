package ru.kpfu.itis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.model.Good;
import ru.kpfu.itis.model.GoodOnHub;
import ru.kpfu.itis.model.Hub;

import java.util.List;


@Repository
public interface GoodOnHubRepository extends JpaRepository<GoodOnHub, Long> {

    GoodOnHub findOneByGoodAndHub(Good good, Hub hub);

    List<GoodOnHub> findAllByGood(Good good);




}
