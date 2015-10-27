/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.strath.view;

/**
 *
 * @author npb11143
 */
public class Methud {
    
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Methud(String name) {
        this.name = name + "()";
    }
    
}
