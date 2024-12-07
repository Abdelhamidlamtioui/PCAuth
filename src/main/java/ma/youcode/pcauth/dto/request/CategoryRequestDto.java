package ma.youcode.pcauth.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record CategoryRequestDto(
    @NotBlank(message = "nom should not be empty") String nom,
    @NotBlank(message = "description should not be empty") String description
) {}