package com.mvpt.repository;

import com.mvpt.model.Product;
import com.mvpt.model.dto.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT NEW com.mvpt.model.dto.ProductDTO (" +
                "p.id, " +
                "p.title, " +
                "p.sku, " +
                "p.urlImage, " +
                "p.description, " +
                "p.price," +
                "p.quantity," +
                "p.sold," +
                "p.available," +
                "p.imported," +
                "p.createdAt," +
                "p.createdBy," +
                "p.updatedAt," +
                "p.updatedBy," +
                "p.category) " +
            "FROM Product p " +
            "WHERE p.deleted = false ")
    List<ProductDTO> getAllProductDTOByDeletedIsFalse();

    @Query("SELECT NEW com.mvpt.model.dto.ProductDTO (" +
                "p.id, " +
                "p.title, " +
                "p.sku, " +
                "p.urlImage, " +
                "p.description, " +
                "p.price," +
                "p.quantity," +
                "p.sold," +
                "p.available," +
                "p.imported," +
                "p.createdAt," +
                "p.createdBy," +
                "p.updatedAt," +
                "p.updatedBy," +
                "p.category) " +
            "FROM Product p " +
            "WHERE p.deleted = false " +
                "AND p.imported = true")
    List<ProductDTO> getAllProductDTOByDeletedIsFalseAndImportedIsTrue();

    @Query("SELECT NEW com.mvpt.model.dto.ProductDTO (" +
                "p.id, " +
                "p.title, " +
                "p.sku, " +
                "p.urlImage, " +
                "p.description, " +
                "p.price," +
                "p.quantity," +
                "p.sold," +
                "p.available," +
                "p.imported," +
                "p.createdAt," +
                "p.createdBy," +
                "p.updatedAt," +
                "p.updatedBy," +
                "p.category) " +
            "FROM Product p " +
            "WHERE p.deleted = false " +
                "AND p.imported = true " +
                "AND p.available > 0")
    List<ProductDTO> getAllProductDTOByDeletedIsFalseAndImportedIsTrueAndAvailableMoreThanZero();

    @Query("SELECT NEW com.mvpt.model.dto.ProductDTO (" +
                "p.id, " +
                "p.title, " +
                "p.sku, " +
                "p.urlImage, " +
                "p.description, " +
                "p.price," +
                "p.quantity," +
                "p.sold," +
                "p.available," +
                "p.imported," +
                "p.createdAt," +
                "p.createdBy," +
                "p.updatedAt," +
                "p.updatedBy," +
                "p.category) " +
            "FROM Product p " +
            "WHERE p.id = ?1 ")
    Optional<ProductDTO> getProductDTOById(Long id);

    Boolean existsBySku(String sku);

    @Query("SELECT NEW com.mvpt.model.dto.ProductDTO (" +
                "p.id, " +
                "p.title, " +
                "p.sku, " +
                "p.urlImage, " +
                "p.description, " +
                "p.price," +
                "p.quantity," +
                "p.sold," +
                "p.available," +
                "p.imported," +
                "p.createdAt," +
                "p.createdBy," +
                "p.updatedAt," +
                "p.updatedBy," +
                "p.category) " +
            "FROM Product p WHERE p.sku = ?1 AND p.id <> ?2 ")
    Optional<ProductDTO> findProductDTOBySkuAndIdIsNot(String sku, Long id);

    @Query("SELECT NEW com.mvpt.model.dto.ProductDTO (" +
                "p.id, " +
                "p.title, " +
                "p.sku, " +
                "p.urlImage, " +
                "p.description, " +
                "p.price," +
                "p.quantity," +
                "p.sold," +
                "p.available," +
                "p.imported," +
                "p.createdAt," +
                "p.createdBy," +
                "p.updatedAt," +
                "p.updatedBy," +
                "p.category) " +
            "FROM Product p " +
            "WHERE p.title LIKE %?1% " +
                "OR p.sku LIKE %?1% " +
                "OR p.category.name LIKE %?1%" +
                "OR p.category.code LIKE %?1%")
    List<ProductDTO> searchProductDTOByTileAndSkuAndCategory(String keySearch);

    @Query("SELECT NEW com.mvpt.model.dto.ProductDTO (" +
                "p.id, " +
                "p.title, " +
                "p.sku, " +
                "p.urlImage, " +
                "p.description, " +
                "p.price," +
                "p.quantity," +
                "p.sold," +
                "p.available," +
                "p.imported," +
                "p.createdAt," +
                "p.createdBy," +
                "p.updatedAt," +
                "p.updatedBy," +
                "p.category) " +
            "FROM Product p " +
            "WHERE (p.deleted = false " +
                "AND p.imported = true)" +
                "OR p.title LIKE %?1% " +
                "OR p.sku LIKE %?1% " +
                "OR p.category.name LIKE %?1%" +
                "OR p.category.code LIKE %?1%")
    List<ProductDTO> searchInventoryOfProductDTOByTileAndSkuAndCategory(String keySearch);


}
