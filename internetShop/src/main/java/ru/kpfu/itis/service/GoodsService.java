package ru.kpfu.itis.service;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.form.GoodsCreationForm;
import ru.kpfu.itis.model.Good;

import java.util.List;

@Service
public interface GoodsService {

    void saveNewGood(GoodsCreationForm good);

    void changeGood(Good good, String newName, String newDescription);

    List<Good> getAllDescriptionOfGoods();

    Good getGoodById(String goodId);

    List<Good> getGoodsByCategory(String category);

    List<Good> getGoodsByName(String name);


}
