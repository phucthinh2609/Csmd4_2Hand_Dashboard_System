package com.mvpt.service.product;

import com.mvpt.model.Category;
import com.mvpt.model.Product;
import com.mvpt.model.dto.CategoryDTO;
import com.mvpt.model.dto.ProductDTO;
import com.mvpt.repository.CategoryRepository;
import com.mvpt.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<ProductDTO> getAllProductDTOByDeletedIsFalse() {

        return productRepository.getAllProductDTOByDeletedIsFalse();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product getById(Long id) {
        return productRepository.getById(id);
    }

    @Override
    public Optional<ProductDTO> getProductDTOById(Long id) {
        return productRepository.getProductDTOById(id);
    }

    @Override
    public Product save(Product product) {
        Product productSave = productRepository.save(product);
        return productRepository.save(product);
    }

    @Override
    public ProductDTO saveDTO(ProductDTO productDTO) {
        return productRepository.save(productDTO.toProduct()).toProductDTO();
    }

    @Override
    public Boolean existsBySku(String sku) {
        return productRepository.existsBySku(sku);
    }

    @Override
    public Optional<ProductDTO> findProductDTOBySkuAndIdIsNot(String sku, Long id) {
        return productRepository.findProductDTOBySkuAndIdIsNot(sku, id);
    }

    @Override
    public void remove(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDTO> searchProductDTOByTileAndSku(String keySearch) {
        return productRepository.searchProductDTOByTileAndSku(keySearch);
    }
}
