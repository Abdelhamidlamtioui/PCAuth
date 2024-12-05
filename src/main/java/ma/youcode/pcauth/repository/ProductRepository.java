package ma.youcode.pcauth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.youcode.pcauth.entities.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByCategory_Id(Long categoryId);
}
