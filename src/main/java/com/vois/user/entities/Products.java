package com.vois.user.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

	@Entity
	@Table(name="products")
	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@ToString
	public class Products{
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name="id")
		private Integer id;
		@Column(length = 20)
		@NotBlank(message = "Product name is required")
		private String productName;
		@Column(name="mobile",unique=true)
		private String category;
		
	}

