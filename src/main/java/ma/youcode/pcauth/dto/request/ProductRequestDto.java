package ma.youcode.pcauth.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record ProductRequestDto(
    @NotBlank(message = "designation should not be empty") String designation,
    @NotNull @Positive(message = "price must be positive") Double price,
    @NotNull @Positive(message = "quantity must be positive") Integer quantity,
    Long categoryId,
    Long userId
) {}

