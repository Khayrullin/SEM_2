package ru.kpfu.itis.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by habar on 09.05.2017.
 */
@Entity
@Table(name = "token")
public class Token {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String uuid;
    private Date deleteDate;
    @ManyToOne
    private User user;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Date getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}