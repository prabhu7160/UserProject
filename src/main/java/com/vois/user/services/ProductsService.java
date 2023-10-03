package com.vois.user.services;

import java.util.List;
import com.vois.user.dtos.ProductsDto;

public interface ProductsService {
	ProductsDto createProduct(ProductsDto productDto);
	ProductsDto updateProduct(ProductsDto productDto,Integer productId);
	ProductsDto getProductById(Integer productId);
	List<ProductsDto> getAllProducts();
	void deleteProductById(Integer productId);
	
	
}
