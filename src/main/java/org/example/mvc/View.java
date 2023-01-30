package org.example.mvc;

import org.example.mvc.interfaces.ViewProto;

public class View implements ViewProto {

    @Override
    public void print(String s)
    {
        System.out.println(s);
    }
}
