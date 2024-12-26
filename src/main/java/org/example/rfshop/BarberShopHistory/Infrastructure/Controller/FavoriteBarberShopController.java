package org.example.rfshop.BarberShopHistory.Infrastructure.Controller;

import org.example.rfshop.BarberShopHistory.Application.AddBarberShopToFavoriteUseCase.AddBarberShopToFavoriteUseCase;
import org.example.rfshop.BarberShopHistory.Application.DeleteBarberShopToFavoriteUseCase.DeleteBarberShopToFavoriteUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("barber-shop/history")
public class FavoriteBarberShopController {

    private final AddBarberShopToFavoriteUseCase addBarberShopToFavoriteUseCase;
    private final DeleteBarberShopToFavoriteUseCase deleteBarberShopToHistoryUseCase;

    @Autowired
    public FavoriteBarberShopController(AddBarberShopToFavoriteUseCase addBarberShopToFavoriteUseCase, DeleteBarberShopToFavoriteUseCase deleteBarberShopToHistoryUseCase) {
        this.addBarberShopToFavoriteUseCase = addBarberShopToFavoriteUseCase;
        this.deleteBarberShopToHistoryUseCase = deleteBarberShopToHistoryUseCase;
    }


    @PostMapping("/user/{userId}/barber-shop/{barberShopId}")
    public ResponseEntity<?> addBarberShopToHistory(@PathVariable Long userId, @PathVariable Long barberShopId) {
            return new ResponseEntity<>(addBarberShopToFavoriteUseCase.execute(userId, barberShopId), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/user/{userId}/barber-shop/{barberShopId}")
    public ResponseEntity<?> deleteBarberShopToHistory(@PathVariable Long userId, @PathVariable Long barberShopId) {
            deleteBarberShopToHistoryUseCase.execute(userId, barberShopId);
            return new ResponseEntity<>("Barber shop deleted from history", HttpStatus.OK);
    }
}
