package com.br.api.wishlist.infrastructure.mongo.document;

import java.util.List;

import com.br.api.wishlist.infrastructure.persistence.mongo.documents.ProductDocument;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "wishlists")
public class WishlistDocument {

    @Id
    private ObjectId id;

    @Field("clientId")
    private ObjectId clientId;

    private List<ProductDocument> products;

    public String getIdAsString() {
        return id != null ? id.toHexString() : null;
    }

    public void setIdFromString(String id) {
        this.id = id != null ? new ObjectId(id) : null;
    }

    public String getClientIdAsString() {
        return clientId != null ? clientId.toHexString() : null;
    }

    public void setClientIdFromString(String clientId) {
        this.clientId = clientId != null ? new ObjectId(clientId) : null;
    }
}