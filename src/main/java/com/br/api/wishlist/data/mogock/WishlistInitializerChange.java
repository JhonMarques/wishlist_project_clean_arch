package com.br.api.wishlist.data.mogock;

import com.br.api.wishlist.data.models.WishlistDocumentModel;
import com.br.api.wishlist.data.models.WishlistProductModel;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Arrays;

@ChangeUnit(id="client-initializer", order = "001", author = "mongock")
public class WishlistInitializerChange {
	private final MongoTemplate mongoTemplate;

    public WishlistInitializerChange(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Execution
    public void changeSet() {
        WishlistProductModel product = new WishlistProductModel();
        WishlistDocumentModel wishlist = new WishlistDocumentModel();
        wishlist.setClientId("LvGtFbS3aNZJ7tRn61bCkrlJ");
        wishlist.setProducts(Arrays.asList(product));

        mongoTemplate.save(wishlist, "wishlists");
    }

    @RollbackExecution
    public void rollback() {
        mongoTemplate.remove(new Query(), "wishlists");
    }
}
