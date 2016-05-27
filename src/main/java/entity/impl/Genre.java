package entity.impl;

import entity.IEntity;

/**
 * Created by EgorVeremeychik on 17.04.2016.
 */
public class Genre implements IEntity {
    private long id;
    private String name;

    public Genre() {
    }

    public Genre(String name) {
        this.name = name;
    }

    public Genre(int genreID, String name) {
        this.id = genreID;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long genreID) {
        this.id = genreID;
    }
}
