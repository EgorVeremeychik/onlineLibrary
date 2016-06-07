package entity.impl;

import entity.IEntity;

/**
 * Created by EgorVeremeychik on 23.04.2016.
 */
public class Publisher implements IEntity {

    private Integer id;
    private String name;

    public Publisher() {
    }

    public Publisher(Integer publisherID, String name) {
        this.id = publisherID;
        this.name = name;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer publisherID) {
        id = publisherID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
