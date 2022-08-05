package com.mvpt.service.product;

import com.mvpt.model.Product;
import com.mvpt.model.dto.ProductDTO;
import com.mvpt.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface ProductService  extends IGeneralService<Product>{
    List<ProductDTO> getAllProductDTOByDeletedIsFalse();

    Optional<ProductDTO> getProductDTOById(Long id);

    ProductDTO saveDTO(ProductDTO productDTO);

    Boolean existsBySku(String sku);

    Optional<ProductDTO> findProductDTOBySkuAndIdIsNot(String sku, Long id);

    List<ProductDTO> searchProductDTOByTileAndSku(String keySearch);
}
