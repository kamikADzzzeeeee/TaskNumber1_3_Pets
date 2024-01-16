package ru.yamshikov.pets.shop.repositories;

import lombok.Getter;
import org.springframework.stereotype.Repository;
import ru.yamshikov.pets.shop.model.Pet;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class PetRepository {
    @Getter
    private static final PetRepository instance = new PetRepository();
    private final Map<UUID, Pet> model;

    private PetRepository(){
        model = new HashMap<>();
        model.put(UUID.fromString("56046c1e-4b9b-4f23-9b20-24e55ffefbeb"), new Pet("Васька","Кошачьи","М", 5));
        model.put(UUID.fromString("c9cf309a-ec0f-4ce9-91a1-a1e0d8ff79da"), new Pet("Дуня","Рыба","М", 1));
        model.put(UUID.fromString("211aa0bd-0fa3-47c8-aae9-dbcdc980ee61"), new Pet("Павел","Собачьи","М", 8));
        model.put(UUID.fromString("6b4ba3a2-1605-46ae-ae83-de7190132a38"), new Pet("Тссцыпа","Змея","М", 3));
        model.put(UUID.fromString("4b50f59b-1759-4bdb-9c84-6c885187a3ad"), new Pet("Лариса","Кошачьи","Ж", 2));
    }

    public void add(Pet pet){
        model.put(UUID.randomUUID(), pet);
    }

    public Map<UUID, Pet> getMapAllPets(){
        return model;
    }

    public Optional<Pet> get(UUID petUUID){
        return Optional.ofNullable(model.get(petUUID));
    }

    public void update(UUID petUUID, Pet newPet){
        model.replace(petUUID, newPet);
    }

    public void delete(UUID petUUID){
        model.remove(petUUID);
    }

}
