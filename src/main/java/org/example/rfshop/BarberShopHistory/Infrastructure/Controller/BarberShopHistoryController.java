package org.example.rfshop.BarberShopHistory.Infrastructure.Controller;

import org.example.rfshop.BarberShopHistory.Application.AddBarberShopToHistoryUseCase.AddBarberShopToHistoryUseCase;
import org.example.rfshop.BarberShopHistory.Application.DeleteBarberShopToHistoryUseCase.DeleteBarberShopToHistoryUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("barber-shop/history")
public class BarberShopHistoryController {

    private final AddBarberShopToHistoryUseCase addBarberShopToHistoryUseCase;
    private final DeleteBarberShopToHistoryUseCase deleteBarberShopToHistoryUseCase;

    @Autowired
    public BarberShopHistoryController(AddBarberShopToHistoryUseCase addBarberShopToHistoryUseCase, DeleteBarberShopToHistoryUseCase deleteBarberShopToHistoryUseCase) {
        this.addBarberShopToHistoryUseCase = addBarberShopToHistoryUseCase;
        this.deleteBarberShopToHistoryUseCase = deleteBarberShopToHistoryUseCase;
    }


    @PostMapping("/user/{userId}/barber-shop/{barberShopId}")
    public ResponseEntity<?> addBarberShopToHistory(@PathVariable Long userId, @PathVariable Long barberShopId) {
            return new ResponseEntity<>(addBarberShopToHistoryUseCase.execute(userId, barberShopId), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/user/{userId}/barber-shop/{barberShopId}")
    public ResponseEntity<?> deleteBarberShopToHistory(@PathVariable Long userId, @PathVariable Long barberShopId) {
            deleteBarberShopToHistoryUseCase.execute(userId, barberShopId);
            return new ResponseEntity<>("Barber shop deleted from history", HttpStatus.OK);
    }
}
