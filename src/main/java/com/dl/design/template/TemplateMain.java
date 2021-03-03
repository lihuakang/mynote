package com.dl.design.template;

public class TemplateMain {
    public static void main(String[] args) {
        AbstractClass tm=new ConcreteClass();
        tm.templateMethod();
    }
}
