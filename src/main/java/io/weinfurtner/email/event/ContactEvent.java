package io.weinfurtner.email.event;

import org.thymeleaf.context.Context;

/**
 * Created by Jared on 7/10/2016.
 */
public class ContactEvent extends Context {

    public void setName(String name) {
        setVariable("name", name);
    }

    public void setEmail(String email) {
        setVariable("email", email);
    }

    public void setMessage(String message) {
        setVariable("message", message);
    }
}
