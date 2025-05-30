package com.br.api.wishlist.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WishlistRequestDTO {

    @NotBlank
    @Size(min = 24, max = 24, message = "The size of clientId should be 24")
    @Pattern(regexp = "^[0-9a-fA-F]{24}$", message = "clientId is not a valid ObjectId pattern")
    private String clientId;

    @NotBlank
    @Size(min = 24, max = 24, message = "The size of productId should be 24")
    @Pattern(regexp = "^[0-9a-fA-F]{24}$", message = "productId is not a valid ObjectId pattern")
    private String productId;
}