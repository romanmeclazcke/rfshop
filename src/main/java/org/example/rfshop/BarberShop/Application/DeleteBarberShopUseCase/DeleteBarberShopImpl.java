package org.example.rfshop.BarberShop.Application.DeleteBarberShopUseCase;

import jakarta.persistence.EntityNotFoundException;
import org.example.rfshop.BarberShop.Infrastructure.Repository.BarberShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteBarberShopImpl implements DeleteBarberShop {


    private final BarberShopRepository barberShopRepository;

    @Autowired
    public DeleteBarberShopImpl(BarberShopRepository barberShopRepository) {
        this.barberShopRepository = barberShopRepository;
    }

    @Override
    public void execute(Long id) {
        this.barberShopRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Barber with id " + id + " not found"));
        this.barberShopRepository.deleteById(id);
    }
}
