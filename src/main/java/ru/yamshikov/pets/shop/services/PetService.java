package ru.yamshikov.pets.shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import ru.yamshikov.pets.shop.dto.ConfirmUpdatePetDto;
import ru.yamshikov.pets.shop.model.Pet;
import ru.yamshikov.pets.shop.repositories.PetRepository;
import ru.yamshikov.pets.shop.util.errors.PetValidationException;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PetService {
    private final PetRepository petRepository = PetRepository.getInstance();

    /**
     *Добавить новое животное
     * */
    public void addPet(Pet pet, Errors errors){
        petValidation(errors);
        petRepository.add(pet);
    }

    /**
     *Получить Map всех животных
     * */
    public Map<UUID, Pet> getMapAllPets() {
        return petRepository.getMapAllPets();
    }

    /**
     *Получить животное по его UUID
     * */
    public Pet getPetById(UUID uuid) {
        return petRepository.get(uuid).orElseThrow(NoSuchElementException::new); //NoSuchElementException
    }

    /**
     *Обновить данные о животном
     * */
    public ConfirmUpdatePetDto updatePetById(UUID uuid, Pet pet, Errors errors) {
        petValidation(errors);
        Pet oldPet = petRepository.get(uuid).orElseThrow(NoSuchElementException::new);
        petRepository.update(uuid,pet);
        return new ConfirmUpdatePetDto(oldPet,pet);
    }

    /**
     *Удалить животное по UUID
     * */
    public void deletePetByUUID(UUID uuid){
        petRepository.get(uuid).orElseThrow(NoSuchElementException::new); //NoSuchElementException
        petRepository.delete(uuid);
    }

    /**
     * Метод для проверки введенных данных в JSON'e
     *
     */
    private void petValidation(Errors errors){
        if (errors.hasErrors()){
            List<String> listErrors = errors.getFieldErrors()
                    .stream()
                    .map(field -> field.getField() + " - " + field.getDefaultMessage())
                    .toList();
            throw new PetValidationException(listErrors);
        }
    }

}
