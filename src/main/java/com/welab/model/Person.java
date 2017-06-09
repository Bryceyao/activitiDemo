package com.welab.model;

import java.io.Serializable;

public class Person implements Serializable{
    
    
    /**
     * 
     */
    private static final long serialVersionUID = -116108536806782460L;

    private Long id;
    
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
