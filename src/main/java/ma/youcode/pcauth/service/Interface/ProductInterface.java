package ma.youcode.pcauth.service.Interface;

import java.util.List;
import java.util.Optional;

import ma.youcode.pcauth.dto.request.ProductRequestDto;
import ma.youcode.pcauth.dto.res.ProductResponseDto;

public interface ProductInterface {

    ProductResponseDto create(ProductRequestDto productRequestDto);
    ProductResponseDto update(Long id ,ProductRequestDto productRequestDto);
    void delete(Long id);
    Optional<ProductResponseDto> findById(Long id);
    List<ProductResponseDto> findAll(); 
    List<ProductResponseDto> findByCategoryId(Long id); 

}
