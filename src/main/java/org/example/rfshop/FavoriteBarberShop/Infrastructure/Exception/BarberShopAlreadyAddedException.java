package org.example.rfshop.FavoriteBarberShop.Infrastructure.Exception;

public class BarberShopAlreadyAddedException extends RuntimeException {
    public BarberShopAlreadyAddedException(String message) {
        super(message);
    }
}
