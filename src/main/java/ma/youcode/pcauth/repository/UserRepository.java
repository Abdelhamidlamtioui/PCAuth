package ma.youcode.pcauth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.youcode.pcauth.entities.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
