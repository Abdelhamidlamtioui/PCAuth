package ma.youcode.pcauth.dto.res;

import lombok.Builder;

@Builder
public record RoleResponseDto(
        Long id,
        String username,
        String email,
        Boolean active,
        String role) {

}
