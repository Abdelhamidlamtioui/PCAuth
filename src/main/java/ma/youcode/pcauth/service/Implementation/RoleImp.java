package ma.youcode.pcauth.service.Implementation;

import lombok.RequiredArgsConstructor;
import ma.youcode.pcauth.dto.res.UserResponseDto;
import ma.youcode.pcauth.entities.User;
import ma.youcode.pcauth.entities.Enum.Role;
import ma.youcode.pcauth.mapper.UserMapper;
import ma.youcode.pcauth.repository.UserRepository;
import ma.youcode.pcauth.service.Interface.RoleInterface;
import org.springframework.stereotype.Service;

import java.util.List;
import jakarta.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class RoleImp implements RoleInterface {
    
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public boolean usernameExists(String username) {
        return userRepository.findByUsername(username)
            .isPresent();
    }

    @Override
    public List<UserResponseDto> findAll() {
        return userRepository.findAll()
            .stream()
            .map(userMapper::toResponseDto)
            .toList();
    }

    @Override
    public UserResponseDto updateUserRole(Long id, String role) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
            
        user.setRole(Role.R_ADMIN);
        
        return userMapper.toResponseDto(
            userRepository.save(user)
        );
    }
}