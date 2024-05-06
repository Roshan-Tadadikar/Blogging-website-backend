package com.example.demo.Aspects;

import lombok.Data;

@Data
public class ResourceNotFoundException extends RuntimeException {
    private String subMessage;
    private String message;


    public  ResourceNotFoundException(String subMessage, String message){
        this.subMessage=subMessage;
        this.message=message;
    }


}
