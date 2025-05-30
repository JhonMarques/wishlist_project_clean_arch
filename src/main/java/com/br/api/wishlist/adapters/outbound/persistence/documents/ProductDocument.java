package com.br.api.wishlist.adapters.outbound.persistence.documents;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@NoArgsConstructor
public class ProductDocument {

    @Field("productId")
    private ObjectId productId;

    public ProductDocument(ObjectId productId) {
        this.productId = productId;
    }

    public String getProductIdAsString() {
        return productId != null ? productId.toHexString() : null;
    }

    public void setProductIdFromString(String productId) {
        this.productId = productId != null ? new ObjectId(productId) : null;
    }
}
