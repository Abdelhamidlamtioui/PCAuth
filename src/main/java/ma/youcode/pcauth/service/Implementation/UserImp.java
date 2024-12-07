package ma.youcode.pcauth.service.Implementation;

import lombok.RequiredArgsConstructor;
import ma.youcode.pcauth.dto.res.UserResponseDto;
import ma.youcode.pcauth.entities.User;
import ma.youcode.pcauth.entities.Enum.Role;
import ma.youcode.pcauth.mapper.UserMapper;
import ma.youcode.pcauth.repository.UserRepository;
import ma.youcode.pcauth.service.Interface.UserInterface;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserImp implements UserInterface {
    
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public boolean existsByUsername(String username) {
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
            .orElseThrow(() -> new EntityNotFoundException(
                String.format("User with id %d not found", id)
            ));

        user.setRole(Role.R_ADMIN);
        
        return userMapper.toResponseDto(
            userRepository.save(user)
        );
    }
}