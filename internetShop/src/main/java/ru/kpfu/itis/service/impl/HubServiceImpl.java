package ru.kpfu.itis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.form.RestGoodOnHubPojo;
import ru.kpfu.itis.model.Good;
import ru.kpfu.itis.model.GoodOnHub;
import ru.kpfu.itis.model.Hub;
import ru.kpfu.itis.repository.GoodOnHubRepository;
import ru.kpfu.itis.repository.HubRepository;
import ru.kpfu.itis.service.HubService;

import java.util.List;


@Service
public class HubServiceImpl implements HubService {

    @Autowired
    GoodOnHubRepository goodOnHubRepository;
    @Autowired
    HubRepository hubRepository;


    @Override
    public void changeGoodCount(Good good, Hub hub, int count) {
        GoodOnHub goodOnHub = goodOnHubRepository.findOneByGoodAndHub(good, hub);
        goodOnHub.setMaxCount(count);
        goodOnHubRepository.save(goodOnHub);
    }

    @Override
    public List<GoodOnHub> findAllbyGood(Good good) {
        List<GoodOnHub> list = goodOnHubRepository.findAllByGood(good);
        return list;
    }

    @Override
    public void saveAllRest(List<RestGoodOnHubPojo> list) {
        for (RestGoodOnHubPojo restPoj : list){
            GoodOnHub g = goodOnHubRepository.findOne(restPoj.getId());
            g.setMaxCount(Integer.parseInt(restPoj.getMaxCount()));
            goodOnHubRepository.save(g);
        }
    }

    @Override
    public GoodOnHub findGood(Good good, Hub hub) {
        GoodOnHub goodOnHub = goodOnHubRepository.findOneByGoodAndHub(good, hub);
        return goodOnHub;

    }

    @Override
    public List<Hub> findAllHubs() {
        return hubRepository.findAll();
    }


    @Override
    public Page<GoodOnHub> listAllByPage(Pageable pageable) {
        return goodOnHubRepository.findAll(pageable);
    }


}