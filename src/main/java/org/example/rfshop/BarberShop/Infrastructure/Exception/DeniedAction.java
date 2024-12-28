package org.example.rfshop.BarberShop.Infrastructure.Exception;

public class DeniedAction extends RuntimeException{
    public  DeniedAction(String message){
        super(message);
    }
}
