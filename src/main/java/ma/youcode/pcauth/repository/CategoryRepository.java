package ma.youcode.pcauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.youcode.pcauth.entities.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long>{

}
