package com.gmail.avatar;

import java.util.ArrayList;

public class AvatarComponents {
    ArrayList<Element> elements;
    public AvatarComponents(){
        elements = new ArrayList<Element>();
    }
    public void addElement(Element e){

        elements.add(e);
    }
}
