package org.example.mvc.interfaces;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ControllerProxy implements InvocationHandler {

    private final ControllerProto controllerProto;
    private final ViewProto viewProto;

    public ControllerProxy(ControllerProto controllerProto, ViewProto viewProto) {
        this.controllerProto = controllerProto;
        this.viewProto = viewProto;
    }

    @Override
    public Object invoke(Object proxy, Method m, Object[] args) {
        Object result;
        try {
            if (m.getName().equals("awaitInput")) {
                String argument = (String) args[0];
                argument = argument.toLowerCase();
                if (argument.startsWith("play") || argument.startsWith("amount")) {
                    if (argument.contains(" ")) {
                        String[] parts = argument.split(" ");
                        if (parts.length != 2) result = "Wrong amount of attributes for command";
                        else {
                            m = controllerProto.getClass().getDeclaredMethod(parts[0], String.class);
                            args[0] = parts[1];
                            result = m.invoke(controllerProto, args);
                        }
                    }
                    else result = "Wrong format for play command";
                }
                else {
                    m = controllerProto.getClass().getDeclaredMethod((String) args[0]);
                    result = m.invoke(controllerProto);
                }
                viewProto.print(result.toString());
                m = controllerProto.getClass().getDeclaredMethod("awaitInput", String.class);
                m.invoke(controllerProto, argument);
            }
            else {
                result = m.invoke(controllerProto, args);
            }

        } catch (Exception e) {
            result = "Wrong command, please write again";
            viewProto.print((String) result);
        }
        return result;
    }

}
