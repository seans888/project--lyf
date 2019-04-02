package com.example.gahallon.app2.Model;


public class Message {
    public String message;
    public String[] errors;
    public Message(){

    }


    public Message(String message, String[] errors){
        this.message=message;
        this.errors=errors;
    }
    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }
    public String[] getErrors() {
        return errors;
    }

    public void setErrors(String[] errors) {
        this.errors = errors;
    }

}
