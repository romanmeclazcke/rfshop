package org.example.rfshop.Court.Infrastructure.Controller;

import jakarta.validation.Valid;
import org.example.rfshop.BarberShop.Domain.Dto.Request.CreateBarberShopDto;
import org.example.rfshop.BarberShop.Domain.Dto.Request.UpdateBarberShopDto;
import org.example.rfshop.Court.Application.CreateCourtUseCase.CreateCourtUseCase;
import org.example.rfshop.Court.Application.DeleteCourtUseCase.DeleteCourtUseCases;
import org.example.rfshop.Court.Application.UpdateCourtUseCase.UpdateCourtUseCase;
import org.example.rfshop.Court.Domain.Dto.Request.CreateCourtDto;
import org.example.rfshop.Court.Domain.Dto.Request.UpdateCourtDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courts")
public class CourtController {

    private final CreateCourtUseCase createCourtUseCase;
    private final UpdateCourtUseCase  updateCourtUseCase;
    private final DeleteCourtUseCases  deleteCourtUseCases;

    @Autowired
    public CourtController(CreateCourtUseCase createCourtUseCase,UpdateCourtUseCase updateCourtUseCase,DeleteCourtUseCases deleteCourtUseCases) {
        this.createCourtUseCase = createCourtUseCase;
        this.updateCourtUseCase = updateCourtUseCase;
        this.deleteCourtUseCases = deleteCourtUseCases;
    }

//    @GetMapping("/{barberShopId}")
//    public ResponseEntity<?> getAllBarberShop(@PathVariable Long barberShopId) {
//        return new ResponseEntity<>(this.getBarberShopByIdUseCase.execute(barberShopId), HttpStatus.OK);
//    }

    @PostMapping("/barber-shop/{barberShopId}") //check if barberId will be in path or Dto
    public ResponseEntity<?> createCourt(@PathVariable Long barberShopId,@RequestBody @Valid CreateCourtDto createCourtDto) {
        return new ResponseEntity<>(this.createCourtUseCase.execute(barberShopId,createCourtDto), HttpStatus.CREATED);
    }

    @PatchMapping("/{courtId}")
    public ResponseEntity<?> updateCourt(@PathVariable Long courtId,@RequestBody @Valid UpdateCourtDto updateCourtDto) {
        return new ResponseEntity<>(this.updateCourtUseCase.execute(courtId,updateCourtDto), HttpStatus.OK);
    }

    @DeleteMapping("/{courtId}")
    public ResponseEntity<?> deleteCourt(@PathVariable Long courtId) {
        this.deleteCourtUseCases.execute(courtId);
        return new ResponseEntity<>("Court deleted", HttpStatus.OK);
    }


}
