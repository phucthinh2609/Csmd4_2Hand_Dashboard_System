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

}
