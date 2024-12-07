package ma.youcode.pcauth.mapper;

import org.mapstruct.Mapper;

import ma.youcode.pcauth.dto.request.CategoryRequestDto;
import ma.youcode.pcauth.dto.res.CategoryResponseDto;
import ma.youcode.pcauth.entities.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toEntity(CategoryRequestDto categoryRequestDto);
    CategoryResponseDto toResponseDto(Category category);
}
