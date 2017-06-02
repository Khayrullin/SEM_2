package ru.kpfu.itis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.model.Good;

import java.util.List;


@Repository
public interface GoodsRepository extends JpaRepository<Good, Long> {

    Good findOneByName(String name);

    List<Good> findAllByName(String name);

    Good findOneById(long id);

    List<Good> findAllByCategory(String category);




}
