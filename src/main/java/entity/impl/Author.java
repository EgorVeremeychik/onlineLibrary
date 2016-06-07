package entity.impl;

import entity.IEntity;

/**
 * Created by EgorVeremeychik on 17.04.2016.
 */
public class Author implements IEntity {
    public Author() {
    }

    private Integer id;
    private String name;

    public Author(String name) {
        this.name = name;
    }

    public Author(Integer authorID, String name) {
        this.id = authorID;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer authorID) {
        this.id = authorID;
    }
}
