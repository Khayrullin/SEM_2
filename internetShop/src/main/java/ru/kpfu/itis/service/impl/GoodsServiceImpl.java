package ru.kpfu.itis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.form.GoodsCreationForm;
import ru.kpfu.itis.model.Good;
import ru.kpfu.itis.model.GoodOnHub;
import ru.kpfu.itis.model.Hub;
import ru.kpfu.itis.repository.GoodOnHubRepository;
import ru.kpfu.itis.repository.GoodsRepository;
import ru.kpfu.itis.repository.HubRepository;
import ru.kpfu.itis.service.GoodsService;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsRepository goodsRepository;
    @Autowired
    GoodOnHubRepository goodOnHubRepository;
    @Autowired
    HubRepository hubRepository;

    @Secured({ "ROLE_ADMIN"})
    @Override
    public void saveNewGood(GoodsCreationForm form) {
        Good newGood = new Good();
        newGood.setDescription(form.getDescription());
        newGood.setName(form.getName());
        newGood.setCategory(form.getCategory());
        newGood.setPrice(form.getPrice());
        Good existedGood = goodsRepository.findOneByName(form.getName());
        if(existedGood != null){
            if(existedGood.getName().equals(newGood.getName()) &&
                    existedGood.getCategory().equals(newGood.getCategory()) &&
                    existedGood.getPrice() == newGood.getPrice() &&
                    existedGood.getDescription().equals(newGood.getDescription())){
                newGood = existedGood;
            }
        }
        Hub hub = hubRepository.findOneById(form.getHubId());
        GoodOnHub goodOnHub = new GoodOnHub();
        goodOnHub.setGood(newGood);
        goodOnHub.setHub(hub);
        goodOnHub.setMaxCount(form.getMaxCount());
        goodsRepository.save(newGood);
        goodOnHubRepository.save(goodOnHub);

    }
    @Secured({"ROLE_ADMIN"})
    @Override
    public void changeGood(Good goodToChange, String newName, String newDescription) {
        Good good = goodsRepository.findOneByName(goodToChange.getName());
        good.setName(newName);
        good.setDescription(newDescription);
        goodsRepository.save(good);
    }

    @Override
    public List<Good> getAllDescriptionOfGoods() {
        return goodsRepository.findAll();
    }

    @Override
    public List<Good> getGoodsByName(String name) {
        return goodsRepository.findAllByName(name);
    }

    @Override
    public List<Good> getGoodsByCategory(String category) {
        return goodsRepository.findAllByCategory(category);
    }


    @Override
    public Good getGoodById(String goodId) {
        return goodsRepository.findOneById(Long.parseLong(goodId));
    }
}
