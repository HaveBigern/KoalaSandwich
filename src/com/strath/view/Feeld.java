/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.strath.view;

import com.github.javaparser.ast.type.Type;

/**
 *
 * @author npb11143
 */
public class Feeld {
    
    private String name;
    
    private Type type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
    
    public Feeld(String name) {
        this.name = name;
    }
    
}
