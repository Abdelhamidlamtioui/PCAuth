package ma.youcode.pcauth.service.Interface;

import java.util.List;
import java.util.Optional;

import ma.youcode.pcauth.dto.request.CategoryRequestDto;
import ma.youcode.pcauth.dto.res.CategoryResponseDto;

public interface CategoryInterface {

    CategoryResponseDto create(CategoryRequestDto categoryRequestDto);
    CategoryResponseDto update(Long id ,CategoryRequestDto categoryRequestDto);
    void delete(Long id);
    Optional<CategoryResponseDto> findById(Long id);
    List<CategoryResponseDto> findAll(); 

}
