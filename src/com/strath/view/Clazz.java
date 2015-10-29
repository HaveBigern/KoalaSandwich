/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.strath.view;

import java.util.ArrayList;

/**
 *
 * @author npb11143
 */
public class Clazz {
    
    private final String name;
    
    private String implementsStrings, extendsStrings;
    
    private int wmc, noc, dit;
    
    private ArrayList<String> coupledClasses;

    public ArrayList<String> getCoupledClasses() {
        return coupledClasses;
    }

    public void setCoupledClasses(ArrayList<String> coupledClasses) {
        this.coupledClasses = coupledClasses;
    }

    public int getDit() {
        return dit;
    }

    public void setDit(int dit) {
        this.dit = dit;
    }

    public int getNoc() {
        return noc;
    }

    public void setNoc(int noc) {
        this.noc = noc;
    }
    
    private ArrayList<String> extendsNames, implementsNames;

    public ArrayList<String> getExtendsNames() {
        return extendsNames;
    }

    public void setExtendsNames(ArrayList<String> extendsNames) {
        this.extendsNames = extendsNames;
    }

    public ArrayList<String> getImplementsNames() {
        return implementsNames;
    }

    public void setImplementsNames(ArrayList<String> implementsNames) {
        this.implementsNames = implementsNames;
    }

    public int getWmc() {
        return wmc;
    }

    public void setWmc(int wmc) {
        this.wmc = wmc;
    }

    public String getImplementsString() {
        return implementsStrings;
    }

    public void setImplementsString(String implementsStrings) {
        this.implementsStrings = implementsStrings;
    }

    public String getExtendsString() {
        return extendsStrings;
    }

    public void setExtendsString(String extendsStrings) {
        this.extendsStrings = extendsStrings;
    }
   
    public String getName() {
        return name;
    }
    
    private ArrayList<Feeld> fields;
    
    private ArrayList<Methud> methods;

    public ArrayList<Feeld> getFields() {
        return fields;
    }

    public void setFields(ArrayList<Feeld> fields) {
        this.fields = fields;
    }

    public ArrayList<Methud> getMethods() {
        return methods;
    }

    public void setMethods(ArrayList<Methud> methods) {
        this.methods = methods;
    }
    
    public Clazz(String name) {
        this.name = name;
    }
    
}
