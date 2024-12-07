package ma.youcode.pcauth.service.Interface;

import java.util.List;
import ma.youcode.pcauth.dto.res.UserResponseDto;
import ma.youcode.pcauth.entities.User;

public interface UserInterface {
  List<UserResponseDto> findAll();

  UserResponseDto updateUserRole(Long id, String role);

  void saveUser(User user);

  boolean usernameExists(String username);

}
