package com.vois.user.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@Table(name="usersproject")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UsersProject{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	@Column(length = 20)
	@NotBlank(message = "First Name is required")
	@Size(min = 2, max = 20, message = "name must be between 2 and 40 characters")
	private String userName;
	@Column(name="mobile",unique=true)
	private String mobile;
	@Column(name="email",unique=true,nullable=false)
	@NotBlank(message="email must not be empty")
	@Email(message="invalid email address")
	private String email;
	@Column(name="password",nullable=false)
	@NotEmpty(message="password must not be empty")
	//@Pattern(regexp="((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{6,20})",message = "Invalid Password!")
	private String password;
	
	private String role;
	
	
	
}
