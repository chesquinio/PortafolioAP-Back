package com.portafolio.SpringBoot.Security.Controller;

import java.util.logging.Logger;


public class Message {
    private String message;
    
    //Constructores

    public Message() {
    }

    public Message(String message) {
        this.message = message;
    }
    
    //Getters and Setters

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
