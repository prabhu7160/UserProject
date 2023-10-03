package com.vois.user.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UsersDto {
	private Integer id;
	@NotBlank
	private String userName;
	@NotBlank
	private String mobile;
	@NotBlank
	@Email
	private String email;
	@NotEmpty
	private String password;
}