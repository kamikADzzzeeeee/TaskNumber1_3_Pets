package ru.yamshikov.pets.shop.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.yamshikov.pets.shop.dto.ConfirmDto;
import ru.yamshikov.pets.shop.dto.ConfirmUpdatePetDto;
import ru.yamshikov.pets.shop.model.Pet;
import ru.yamshikov.pets.shop.services.PetService;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/pets")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;

    @PostMapping()
    public ResponseEntity<ConfirmDto> createNewPet(
            @RequestBody @Valid Pet pet,
            Errors errors) {
        petService.addPet(pet, errors);
        return new ResponseEntity<>(new ConfirmDto("Животное создано"), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<Map<UUID, Pet>> getAllPets() {
        return new ResponseEntity<>(petService.getMapAllPets(), HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Pet> getPet(
            @PathVariable(value = "uuid") UUID uuid) {
        return new ResponseEntity<>(petService.getPetById(uuid), HttpStatus.OK);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<ConfirmUpdatePetDto> updatePet(
            @PathVariable(value = "uuid") UUID uuid,
            @RequestBody @Valid Pet pet,
            Errors errors) {
        return new ResponseEntity<>(petService.updatePetById(uuid, pet, errors), HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<ConfirmDto> deletePet(
            @PathVariable(value = "uuid") UUID uuid) {
        petService.deletePetByUUID(uuid);
        return new ResponseEntity<>(new ConfirmDto("Животное удалено"), HttpStatus.OK);
    }


}
