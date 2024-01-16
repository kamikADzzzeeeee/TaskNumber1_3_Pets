package ru.yamshikov.pets.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.yamshikov.pets.shop.model.Pet;

@Data
@AllArgsConstructor
public class ConfirmUpdatePetDto {
    private Pet oldPet;
    private Pet newPet;
}
