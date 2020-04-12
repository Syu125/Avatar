package com.gmail.avatar;

import java.util.ArrayList;

public class AvatarComponents {
    ArrayList<Element> elements;

    public AvatarComponents() {

        elements = new ArrayList<Element>();
    }
    public AvatarComponents(ArrayList<Element>a) {
        elements = a;
    }
    public void addElement(Element e) {
        elements.add(e);
    }
    public ArrayList<Element> getElements(){
        return elements;
    }
}
