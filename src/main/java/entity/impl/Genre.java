package entity.impl;

import entity.IEntity;

/**
 * Created by EgorVeremeychik on 17.04.2016.
 */
public class Genre implements IEntity {
    private Integer id;
    private String name;

    public Genre() {
    }

    public Genre(String name) {
        this.name = name;
    }

    public Genre(Integer genreID, String name) {
        this.id = genreID;
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

    public void setId(Integer genreID) {
        this.id = genreID;
    }
}
