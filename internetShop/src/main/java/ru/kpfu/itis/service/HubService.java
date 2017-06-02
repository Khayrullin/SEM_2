package ru.kpfu.itis.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.form.RestGoodOnHubPojo;
import ru.kpfu.itis.model.Good;
import ru.kpfu.itis.model.GoodOnHub;
import ru.kpfu.itis.model.Hub;

import java.util.List;

@Service
public interface HubService {


    void changeGoodCount(Good good,Hub hub, int count);


    List<GoodOnHub> findAllbyGood(Good good);

    void saveAllRest(List<RestGoodOnHubPojo> list);


    GoodOnHub findGood(Good good, Hub hub);

    List<Hub>  findAllHubs();



    Page<GoodOnHub> listAllByPage(Pageable pageable);



}
