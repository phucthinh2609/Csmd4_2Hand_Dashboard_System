package com.mvpt.controller.rest;

import com.mvpt.exception.DataInputException;
import com.mvpt.model.Category;
import com.mvpt.model.Product;
import com.mvpt.model.dto.ProductDTO;
import com.mvpt.service.category.CategoryService;
import com.mvpt.service.product.ProductService;
import com.mvpt.utils.AppUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/products")
public class ProductRestController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AppUtils appUtils;

    @GetMapping()
    public ResponseEntity<?> getAll() {
        List<ProductDTO> productList = productService.getAllProductDTOByDeletedIsFalse();

        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable long id) {
        Optional<ProductDTO> productDTO = productService.getProductDTOById(id);

        if (!productDTO.isPresent()) {
            throw new DataInputException("Product ID invalid!!!");
        }

        return new ResponseEntity<>(productDTO.get(), HttpStatus.OK);
    }

    @GetMapping("/search/{keySearch}")
    public ResponseEntity<?> doSearch(@PathVariable String keySearch) {
        List<ProductDTO> productDTOList = productService.searchProductDTOByTileAndSku(keySearch);

        return new ResponseEntity<>(productDTOList, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> doCreate(@Validated @RequestBody ProductDTO productDTO, BindingResult bindingResult) {

        if (bindingResult.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }

        productDTO.setId(String.valueOf(0L));
        productDTO.setPrice(String.valueOf(0));
        productDTO.setQuantity(String.valueOf(0));
        productDTO.setSold(String.valueOf(0));
        productDTO.setAvailable(String.valueOf(0));

        Boolean existBySku = productService.existsBySku(productDTO.getSku());

        if (existBySku) {
          throw new DataInputException("Sku already exist!!!");
        }

        Optional<Category> category = categoryService.findById(productDTO.toProduct().getCategory().getId());

        if (!category.isPresent()) {
            throw new DataInputException("Category ID invalid!!!");
        }

        ProductDTO newProductDTO =  productService.saveDTO(productDTO);

        return new ResponseEntity<>(newProductDTO, HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<?> doUpdate(@Validated @RequestBody ProductDTO productDTO, BindingResult bindingResult) {

        if (bindingResult.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }

        Optional<ProductDTO> optionalProductDTO = productService.findProductDTOBySkuAndIdIsNot(productDTO.getSku(), Long.valueOf(productDTO.getId()));

        if (optionalProductDTO.isPresent()) {
            throw new DataInputException("Sku already exist!!!");
        }

        Optional<Category> category = categoryService.findById(productDTO.toProduct().getCategory().getId());

        if (!category.isPresent()) {
            throw new DataInputException("Category ID invalid!!!");
        }

        try {
//              ProductDTO updateProductDTO = productService.saveDTO(productDTO);
            Product product = productDTO.toProduct();
              Product updateProduct = productService.save(product);
              ProductDTO productDTO1 = updateProduct.toProductDTO();
//              return new ResponseEntity<>(updateProductDTO, HttpStatus.OK);
            return new ResponseEntity<>(updateProduct.toProductDTO(), HttpStatus.OK);

        } catch (Exception ex) {
           throw new DataInputException("Please contact management!!!");
        }
    }
}
