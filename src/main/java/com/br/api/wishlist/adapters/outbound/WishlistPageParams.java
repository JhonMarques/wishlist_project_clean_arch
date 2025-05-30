package com.br.api.wishlist.adapters.outbound;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WishlistPageParams {
    private String clientId;
    private Integer page;
}