package org.example.rfshop.BarberShop.Infrastructure.Controller;

import jakarta.validation.Valid;
import org.example.rfshop.BarberShop.Application.CreateBarberShopUseCase.CreateBarberShopUseCase;
import org.example.rfshop.BarberShop.Application.GetAllBarberShopUseCase.GetAllBarberShopUseCase;
import org.example.rfshop.BarberShop.Application.GetBarberShopById.GetBarberShopByIdUseCase;
import org.example.rfshop.BarberShop.Application.UpdateBarberShopUseCase.UpdateBarberShopUseCase;
import org.example.rfshop.BarberShop.Domain.Dto.Request.CreateBarberShopDto;
import org.example.rfshop.BarberShop.Domain.Dto.Request.UpdateBarberShopDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/barber-shop")
public class BarberShopController {

    private final CreateBarberShopUseCase createBarberShopUseCase;
    private final UpdateBarberShopUseCase updateBarberShopUseCase;
    private final GetAllBarberShopUseCase getAllBarberShopUseCase;
    private final GetBarberShopByIdUseCase getBarberShopByIdUseCase;

    @Autowired
    public BarberShopController(CreateBarberShopUseCase createBarberShopUseCase, UpdateBarberShopUseCase updateBarberShopUseCase, GetAllBarberShopUseCase getAllBarberShopUseCase, GetBarberShopByIdUseCase getBarberShopByIdUseCase) {
        this.createBarberShopUseCase = createBarberShopUseCase;
        this.updateBarberShopUseCase = updateBarberShopUseCase;
        this.getAllBarberShopUseCase = getAllBarberShopUseCase;
        this.getBarberShopByIdUseCase = getBarberShopByIdUseCase;
    };


    @GetMapping("")
    public ResponseEntity<?> getAllBarberShop() {
        return new ResponseEntity<>(this.getAllBarberShopUseCase.execute(), HttpStatus.OK);
    }

    @GetMapping("/{barberShopId}")
    public ResponseEntity<?> getAllBarberShop(@PathVariable Long barberShopId) {
        return new ResponseEntity<>(this.getBarberShopByIdUseCase.execute(barberShopId), HttpStatus.OK);
    }

    @PostMapping("/owner/{ownerId}")
    public ResponseEntity<?> createBarberShop(@PathVariable Long ownerId,@RequestBody @Valid CreateBarberShopDto createBarberShopDto) {
        return new ResponseEntity<>(this.createBarberShopUseCase.execute(ownerId,createBarberShopDto), HttpStatus.CREATED);
    }


    @PatchMapping("/{barberId}")
    public ResponseEntity<?> updateBarberShop(@PathVariable Long barberId,@RequestBody @Valid UpdateBarberShopDto updateBarberShopDto) {
        return new ResponseEntity<>(this.updateBarberShopUseCase.execute(barberId,updateBarberShopDto), HttpStatus.OK);
    }
}
