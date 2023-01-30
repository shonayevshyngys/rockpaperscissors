package org.example.mvc;

import org.example.mvc.interfaces.ControllerProto;
import org.example.mvc.interfaces.ControllerProxy;
import org.example.mvc.interfaces.ModelProto;
import org.example.mvc.interfaces.ViewProto;
import org.example.util.FigureEnum;

import java.lang.reflect.Proxy;
import java.util.Scanner;

public class Injector {

    private ModelProto m;

    private ViewProto v;

    private ControllerProto c;

    private ControllerProto cp;

    private Scanner sc;

    static {
        FigureEnum[] enums = FigureEnum.values();
        for (int i = 1; i < enums.length; i++) {
            enums[i].setBeats(enums[i-1]);
        }
        enums[0].setBeats(enums[enums.length-1]);
    }
    public Injector()
    {
    }

    public ControllerProto getControllerProxy()
    {
        if (cp == null) cp = (ControllerProto) Proxy.newProxyInstance(getController().getClass().getClassLoader(),
                getController().getClass().getInterfaces(), new ControllerProxy(getController(), getView()));
        return  cp;
    }

    public ControllerProto getController()
    {
        if (c == null) c = new Controller(getView(), getModel());
        return c;
    }

    private ViewProto getView(){
        if (v == null) v = new View();
        return v;
    }

    private ModelProto getModel()
    {
        if (m == null) m = new Model();
        return m;
    }

    public Scanner getScanner()
    {
        if (sc == null) sc = new Scanner(System.in);
        return sc;
    }
}
