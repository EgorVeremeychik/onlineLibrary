package entity.impl;

import entity.IEntity;

/**
 * Created by EgorVeremeychik on 23.04.2016.
 */
public class Publisher implements IEntity {

    private long id;
    private String name;

    public Publisher() {
    }

    public Publisher(long publisherID, String name) {
        this.id = publisherID;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long publisherID) {
        id = publisherID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
