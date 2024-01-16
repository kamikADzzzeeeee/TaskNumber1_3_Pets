package ru.yamshikov.pets.shop.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pet {
    @NotBlank(message = "Поле не должно быть пустым")
    private String name;
    @NotBlank(message = "Поле не должно быть пустым")
    private String type;
    @NotBlank(message = "Поле не должно быть пустым")
    private String sex;
    @NotNull(message = "Поле не может быть null")
    @Min(value = 0, message = "Минимальное значение для поля - 0")
    private Integer age;
}
