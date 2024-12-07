package ma.youcode.pcauth.service.Implementation;

import lombok.RequiredArgsConstructor;
import ma.youcode.pcauth.dto.request.ProductRequestDto;
import ma.youcode.pcauth.dto.res.ProductResponseDto;
import ma.youcode.pcauth.entities.Category;
import ma.youcode.pcauth.entities.Product;
import ma.youcode.pcauth.exceptions.CategoryException;
import ma.youcode.pcauth.exceptions.ProductException;
import ma.youcode.pcauth.mapper.ProductMapper;
import ma.youcode.pcauth.repository.CategoryRepository;
import ma.youcode.pcauth.repository.ProductRepository;
import ma.youcode.pcauth.service.Interface.ProductInterface;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductImp implements ProductInterface {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductResponseDto create(ProductRequestDto productRequestDto) {
        Category category = categoryRepository.findById(productRequestDto.categoryId())
            .orElseThrow(() -> new CategoryException("Category is not found"));

        Product product = productMapper.toEntity(productRequestDto);
        product.setCategory(category);
        
        return productMapper.toResponseDto(
            productRepository.save(product)
        );
    }

    @Override
    public ProductResponseDto update(Long id, ProductRequestDto productRequestDto) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new ProductException("Product is not found"));
            
        Category category = categoryRepository.findById(productRequestDto.categoryId())
            .orElseThrow(() -> new CategoryException("Category is not found"));

        updateProductFields(product, productRequestDto, category);
        
        return productMapper.toResponseDto(
            productRepository.save(product)
        );
    }

    private void updateProductFields(Product product, ProductRequestDto dto, Category category) {
        product.setDesignation(dto.designation());
        product.setPrice(dto.price());
        product.setQuantity(dto.quantity());
        product.setCategory(category);
    }

    @Override
    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductException("Product is not found");
        }
        productRepository.deleteById(id);
    }

    @Override
    public Optional<ProductResponseDto> findById(Long id) {
        return productRepository.findById(id)
            .map(productMapper::toResponseDto);
    }

    @Override
    public List<ProductResponseDto> findAll() {
        return productRepository.findAll()
            .stream()
            .map(productMapper::toResponseDto)
            .toList();
    }

    @Override
    public List<ProductResponseDto> findByCategoryId(Long categoryId) {
        return productRepository.findByCategory_Id(categoryId)
            .stream()
            .map(productMapper::toResponseDto)
            .toList();
    }
}