package ma.youcode.pcauth.mapper;

import ma.youcode.pcauth.dto.request.UserRequestDto;
import ma.youcode.pcauth.dto.res.UserResponseDto;
import ma.youcode.pcauth.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserRequestDto userRequestDto);
    UserResponseDto toResponseDto(User user);
}
