package com.mvpt.controller.rest;

import com.mvpt.exception.DataInputException;
import com.mvpt.model.dto.ProductDTO;
import com.mvpt.service.product.ProductService;
import com.mvpt.service.product.ProductServiceImpl;
import com.mvpt.utils.AppUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/products")
public class ProductRestController {

    @Autowired
    private ProductService productService;

    @Autowired
    private AppUtils appUtils;

    @GetMapping()
    public ResponseEntity<?> getAll() {
        Iterable<ProductDTO> productList = productService.getAllProductDTOByDeletedIsFalse();

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

    @PostMapping("/create")
    public ResponseEntity<?> doCreate(@Validated @RequestBody ProductDTO productDTO, BindingResult bindingResult) {

        if (bindingResult.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }

        productDTO.setId(String.valueOf(0l));
        productDTO.setPrice(String.valueOf(0));
        productDTO.setQuantity(String.valueOf(0));
        productDTO.setSold(String.valueOf(0));
        productDTO.setAvailable(String.valueOf(0));

        Boolean existBySku = productService.existsBySku(productDTO.getSku());

        if (existBySku) {
          throw new DataInputException("Sku already exist!!!");
        }

        ProductDTO newProductDTO =  productService.doCreate(productDTO);

        return new ResponseEntity<>(newProductDTO, HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<?> doUpdate(@Validated @RequestBody ProductDTO productDTO, BindingResult bindingResult) {

        if (bindingResult.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }

        productDTO.setPrice(String.valueOf(0));
        productDTO.setQuantity(String.valueOf(0));
        productDTO.setSold(String.valueOf(0));
        productDTO.setAvailable(String.valueOf(0));

        Optional<ProductDTO> optionalProductDTO = productService.findProductDTOBySkuAndIdIsNot(productDTO.getSku(), Long.valueOf(productDTO.getId()));

        if (optionalProductDTO.isPresent()) {
            throw new DataInputException("Sku already exist!!!");
        }

        ProductDTO updateProductDTO = productService.doCreate(productDTO);
        return new ResponseEntity<>(updateProductDTO, HttpStatus.OK);

//        try {
//
//
//        } catch (Exception ex) {
//           throw new DataInputException("Please contact management!!!");
//        }
    }
}
