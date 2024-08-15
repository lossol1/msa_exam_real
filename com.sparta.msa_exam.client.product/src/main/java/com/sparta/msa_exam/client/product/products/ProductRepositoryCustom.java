package com.sparta.msa_exam.client.product.products;

import com.sparta.msa_exam.client.product.core.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepositoryCustom {
    Page<ProductResponseDto> searchProducts(ProductSearchDto searchDto, Pageable pageable);
}
