package com.fundamentosplatzi.springboot.fundamentos.bean;

public class MyBeanExampleImplement implements MyBeanExample{
    @Override
    public void printBeanExample() {
        System.out.println("Hola desde la implementacion de un bean propio");

    }
}
