package ma.youcode.pcauth.mapper;

import org.mapstruct.Mapper;

import ma.youcode.pcauth.dto.request.ProductRequestDto;
import ma.youcode.pcauth.dto.res.ProductResponseDto;
import ma.youcode.pcauth.entities.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toEntity(ProductRequestDto productRequestDto);
    ProductResponseDto toResponseDto(Product product);
}
