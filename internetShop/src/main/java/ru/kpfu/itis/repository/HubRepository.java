package ru.kpfu.itis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.model.Hub;


@Repository
public interface HubRepository extends JpaRepository<Hub, Long> {

    Hub findOneById(long id);







}
