package com.vois.user.controllers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vois.user.dtos.ApiResponse;
import com.vois.user.dtos.ProductsDto;
import com.vois.user.services.ProductsService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductsService productService;
	
	@PostMapping("/add")
	public ResponseEntity<ProductsDto> createNewProduct(
			@Valid @RequestBody ProductsDto productDto)
	{
		ProductsDto addProduct = this.productService.createProduct(productDto);
		return new ResponseEntity<>(addProduct,HttpStatus.CREATED);
		
	}         
//PUT
	@PutMapping("/edit/{productId}")
	public ResponseEntity<ProductsDto> updateProduct(@RequestBody ProductsDto productDto, @PathVariable Integer productId){
		ProductsDto updatedProduct = this.productService.updateProduct(productDto, productId);
		return ResponseEntity.ok(updatedProduct);
	}

//DELETE
	@DeleteMapping("/delete/{productId}")
	public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Integer productId){
		this.productService.deleteProductById(productId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Area deleted successfully",true),HttpStatus.OK);
	}
//GET
	@GetMapping("/display/all")
	public ResponseEntity<List<ProductsDto>> displayAllProduct() {
		return ResponseEntity.ok(this.productService.getAllProducts());
	}
	
	@GetMapping("/display/{productId}")
	public ResponseEntity<ProductsDto> getProductById(@PathVariable Integer productId){
		return ResponseEntity.ok(this.productService.getProductById(productId));
	}
}

