package com.mvpt.service.product;

import com.mvpt.model.Product;
import com.mvpt.model.dto.ProductDTO;
import com.mvpt.service.IGeneralService;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductService  extends IGeneralService<Product>{
    List<ProductDTO> getAllProductDTOByDeletedIsFalse();

    Optional<ProductDTO> getProductDTOById(Long id);

    ProductDTO doCreate(ProductDTO productDTO);

    Boolean existsBySku(String sku);

    Optional<ProductDTO> findProductDTOBySkuAndIdIsNot(String sku, Long id);
}
