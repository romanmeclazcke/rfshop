package org.example.rfshop.BarberShop.Infrastructure.Controller;

import jakarta.validation.Valid;
import org.example.rfshop.BarberShop.Application.CreateBarberShopUseCase.CreateBarberShopUseCase;
import org.example.rfshop.BarberShop.Application.DeleteBarberShopUseCase.DeleteBarberShop;
import org.example.rfshop.BarberShop.Application.DeleteBarberShopUseCase.DeleteBarberShopImpl;
import org.example.rfshop.BarberShop.Application.GetAllBarberShopUseCase.GetAllBarberShopUseCase;
import org.example.rfshop.BarberShop.Application.GetBarberShopById.GetBarberShopByIdUseCase;
import org.example.rfshop.BarberShop.Application.UpdateBarberShopUseCase.UpdateBarberShopUseCase;
import org.example.rfshop.BarberShop.Domain.Dto.Request.CreateBarberShopDto;
import org.example.rfshop.BarberShop.Domain.Dto.Request.UpdateBarberShopDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/barber-shops")
public class BarberShopController {

    private final CreateBarberShopUseCase createBarberShopUseCase;
    private final UpdateBarberShopUseCase updateBarberShopUseCase;
    private final GetAllBarberShopUseCase getAllBarberShopUseCase;
    private final GetBarberShopByIdUseCase getBarberShopByIdUseCase;
    private final DeleteBarberShop deleteBarberShop;

    @Autowired
    public BarberShopController(CreateBarberShopUseCase createBarberShopUseCase, UpdateBarberShopUseCase updateBarberShopUseCase, GetAllBarberShopUseCase getAllBarberShopUseCase, GetBarberShopByIdUseCase getBarberShopByIdUseCase, DeleteBarberShop deleteBarberShop ) {
        this.createBarberShopUseCase = createBarberShopUseCase;
        this.updateBarberShopUseCase = updateBarberShopUseCase;
        this.getAllBarberShopUseCase = getAllBarberShopUseCase;
        this.getBarberShopByIdUseCase = getBarberShopByIdUseCase;
        this.deleteBarberShop = deleteBarberShop;
    };


    @GetMapping("")
    public ResponseEntity<?> getAllBarberShop() {
        return new ResponseEntity<>(this.getAllBarberShopUseCase.execute(), HttpStatus.OK);
    }

    @GetMapping("/{barberShopId}")
    public ResponseEntity<?> getBarberShopById(@PathVariable Long barberShopId) {
        return new ResponseEntity<>(this.getBarberShopByIdUseCase.execute(barberShopId), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> createBarberShop(@RequestBody @Valid CreateBarberShopDto createBarberShopDto) {
        return new ResponseEntity<>(this.createBarberShopUseCase.execute(createBarberShopDto), HttpStatus.CREATED);
    }

    @PatchMapping("/{barberId}")
    public ResponseEntity<?> updateBarberShop(@PathVariable Long barberId,@RequestBody @Valid UpdateBarberShopDto updateBarberShopDto) {
        return new ResponseEntity<>(this.updateBarberShopUseCase.execute(barberId,updateBarberShopDto), HttpStatus.OK);
    }

    @DeleteMapping("/{barberId}")
    public ResponseEntity<?> deleteBarberById(@PathVariable Long barberId) {
        this.deleteBarberShop.execute(barberId);
        return new ResponseEntity<>("Barber deleted", HttpStatus.OK);
    }
}
