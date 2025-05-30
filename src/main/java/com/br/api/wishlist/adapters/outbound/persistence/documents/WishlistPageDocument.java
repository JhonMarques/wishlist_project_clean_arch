package com.br.api.wishlist.adapters.outbound.persistence.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WishlistPageDocument {
    private String clientId;
    private Integer page;
}