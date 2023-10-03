package com.vois.user.services.impl;

import java.util.List;
import java.util.stream.Collectors;
import com.vois.user.dtos.ProductsDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vois.user.exceptions.ResourceNotFoundException;
import com.vois.user.repos.ProductRepo;
import com.vois.user.services.ProductsService;
import com.vois.user.entities.*;

@Service
public class ProductsServiceImpl implements ProductsService {

	@Autowired
	private ProductRepo productRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	public Products dtoToProduct(ProductsDto productDto)
	{
		Products product = this.modelMapper.map(productDto, Products.class);
		
		return product;
	}
	public ProductsDto productToDto(Products product)
	{
		ProductsDto productDto = this.modelMapper.map(product, ProductsDto.class);
		
		return productDto;
	}
	@Override
	public ProductsDto createProduct(ProductsDto productDto) {
		Products product = this.dtoToProduct(productDto);
		Products savedProduct = this.productRepo.save(product);
		return this.productToDto(savedProduct);
	}
	
	@Override
	public ProductsDto updateProduct(ProductsDto productDto, Integer productId) {
		Products updatedProduct = this.productRepo.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Products", "id", productId));
		updatedProduct.setProductName(productDto.getProductName());
		updatedProduct.setCategory(productDto.getCategory());

		Products updateSavedProduct = this.productRepo.save(updatedProduct);
		ProductsDto updatedProductDto = this.productToDto(updateSavedProduct);
		
		return updatedProductDto;
	}

	@Override
	public List<ProductsDto> getAllProducts() {
		List<Products> products = this.productRepo.findAll();
		List<ProductsDto> productsDto = products.stream().map(product->this.productToDto(product)).collect(Collectors.toList());
		return productsDto;
	}

	@Override
	public void deleteProductById(Integer productId) {
		Products product = this.productRepo.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Products", "id", productId));
		this.productRepo.delete(product);
	}

	@Override
	public ProductsDto getProductById(Integer productId) {
		// TODO Auto-generated method stub
		Products product = this.productRepo.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Products", "id", productId));
		return this.productToDto(product);
	}
	
}

