package org.example.rfshop.FavoriteBarberShop.Infrastructure.Controller;

import org.example.rfshop.FavoriteBarberShop.Application.AddBarberShopToFavoriteUseCase.AddBarberShopToFavoriteUseCase;
import org.example.rfshop.FavoriteBarberShop.Application.DeleteBarberShopToFavoriteUseCase.DeleteBarberShopToFavoriteUseCase;
import org.example.rfshop.FavoriteBarberShop.Application.GetAllFavoriteBarberShopByUserId.GetAllFavoriteBarberShopByUserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/barber-shop/favorite")
public class FavoriteBarberShopController {

    private final GetAllFavoriteBarberShopByUserId getAllFavoriteBarberShopByUserId;
    private final AddBarberShopToFavoriteUseCase addBarberShopToFavoriteUseCase;
    private final DeleteBarberShopToFavoriteUseCase deleteBarberShopToHistoryUseCase;

    @Autowired
    public FavoriteBarberShopController(GetAllFavoriteBarberShopByUserId  getAllFavoriteBarberShopByUserId ,AddBarberShopToFavoriteUseCase addBarberShopToFavoriteUseCase, DeleteBarberShopToFavoriteUseCase deleteBarberShopToHistoryUseCase) {
        this.getAllFavoriteBarberShopByUserId = getAllFavoriteBarberShopByUserId;
        this.addBarberShopToFavoriteUseCase = addBarberShopToFavoriteUseCase;
        this.deleteBarberShopToHistoryUseCase = deleteBarberShopToHistoryUseCase;
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getBarberShopHistory(@PathVariable Long userId) {
        return new ResponseEntity<>(getAllFavoriteBarberShopByUserId.execute(userId), HttpStatus.OK);
    }

    @PostMapping("/barber-shop/{barberShopId}")
    public ResponseEntity<?> addBarberShopToHistory(@PathVariable Long barberShopId) {
            return new ResponseEntity<>(addBarberShopToFavoriteUseCase.execute(barberShopId), HttpStatus.CREATED);
    }

    @DeleteMapping("/barber-shop/{barberShopId}")
    public ResponseEntity<?> deleteBarberShopToHistory(@PathVariable Long barberShopId) {
            deleteBarberShopToHistoryUseCase.execute( barberShopId);
            return new ResponseEntity<>("Barber shop deleted from history", HttpStatus.OK);
    }
}
