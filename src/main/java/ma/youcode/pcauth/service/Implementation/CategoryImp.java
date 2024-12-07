package ma.youcode.pcauth.service.Implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ma.youcode.pcauth.dto.request.CategoryRequestDto;
import ma.youcode.pcauth.dto.res.CategoryResponseDto;
import ma.youcode.pcauth.entities.Category;
import ma.youcode.pcauth.exceptions.CategoryException;
import ma.youcode.pcauth.mapper.CategoryMapper;
import ma.youcode.pcauth.repository.CategoryRepository;
import ma.youcode.pcauth.service.Interface.CategoryInterface;

@Service
public class CategoryImp implements CategoryInterface {

    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;

    public CategoryImp(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public CategoryResponseDto create(CategoryRequestDto categoryRequestDto) {
        Category category = categoryMapper.toEntity(categoryRequestDto);
        categoryRepository.save(category);
        return categoryMapper.toResponseDto(category);
    }

    @Override
    public CategoryResponseDto update(Long id , CategoryRequestDto categoryRequestDto) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new CategoryException("category is not found"));

        category.setNom(categoryRequestDto.nom());
        category.setDescription(categoryRequestDto.description());

        Category categoryUpdated = categoryRepository.save(category);
        return categoryMapper.toResponseDto(categoryUpdated);
        
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Optional<CategoryResponseDto> findById(Long id) {
        return categoryRepository.findById(id).map(categoryMapper::toResponseDto);        
    }

    @Override
    public List<CategoryResponseDto> findAll() {
        return categoryRepository.findAll().stream().map(categoryMapper::toResponseDto).toList();
    }

}