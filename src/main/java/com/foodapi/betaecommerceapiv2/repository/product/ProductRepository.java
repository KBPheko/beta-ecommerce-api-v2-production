package com.foodapi.betaecommerceapiv2.repository.product;

import com.foodapi.betaecommerceapiv2.models.product.Category;
import com.foodapi.betaecommerceapiv2.models.product.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    /**
     * Search / Filter functionality which checks data from database and filters according to product name
     *  and category name
     */

    default List<Product> searchProduct(String productName, String categoryName){

        return findAll((new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                if (productName != null) {
                    predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("productName")), "%" + productName.toLowerCase() + "%" + productName.toUpperCase() + "%"));
                }
                if (categoryName != null) {
                    Join<Product, Category> prodCategoryJoin = root.join("category");
                    predicates.add(criteriaBuilder.like(criteriaBuilder.lower(prodCategoryJoin.get("categoryName")), "%" + categoryName.toLowerCase() + "%" + categoryName.toUpperCase() + "%" ));
                }

                return criteriaBuilder.and(predicates.toArray( new Predicate[predicates.size()]));
            }
        }));
    }
}
