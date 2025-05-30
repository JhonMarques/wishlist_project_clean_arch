package com.br.api.wishlist.data.datasources;

import com.br.api.wishlist.data.models.WishlistDocumentModel;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IMongoWishlistRepository extends MongoRepository<WishlistDocumentModel, ObjectId> {

	Optional<WishlistDocumentModel> findByClientId(ObjectId clientId);

	boolean existsByClientId(ObjectId clientId);

	@Query("{'clientId': ?0, 'products.productId': ?1}")
	Optional<WishlistDocumentModel> existsByClientIdAndProductId(ObjectId clientId, ObjectId productId);


}
