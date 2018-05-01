package ru.dinis.docs.test;


import javax.xml.bind.annotation.XmlRootElement;

/**
 * Create by dinis of 20.04.18.
 */
@XmlRootElement
public class Bean {
    private int id;
    private String name;

    public Bean() {
    }

    public Bean(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
