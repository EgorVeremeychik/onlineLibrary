package entity.impl;

import entity.IEntity;

import java.util.Date;

/**
 * Created by EgorVeremeychik on 17.04.2016.
 */
public class Author implements IEntity{
    public Author() {
    }

    private long id;
    private String name;
    private Date birthday;

    public Author(String name, Date birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    public Author(long authorID, String name, Date birthday) {
        this.id = authorID;
        this.name = name;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public long getId() {
        return id;
    }

    public void setId(long authorID) {
        this.id = authorID;
    }
}
