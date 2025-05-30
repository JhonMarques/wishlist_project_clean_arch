package com.br.api.wishlist.data.controllers;

import com.br.api.wishlist.data.converter.ProductDataConverter;
import com.br.api.wishlist.data.converter.WishlistDataConverter;
import com.br.api.wishlist.data.models.WishlistProductPageRequestModel;
import com.br.api.wishlist.data.models.WishlistRequestDataModel;
import com.br.api.wishlist.domain.usecases.CreateWishlistProduct;
import com.br.api.wishlist.domain.usecases.DeleteWishlistProduct;
import com.br.api.wishlist.domain.usecases.GetAllProductsWishlistClient;
import com.br.api.wishlist.domain.usecases.GetProductWishlistClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/wishlist")
@Tag(name = "Wishlists", description = "Endpoints for managing wishlists")
public class WishlistController {

	private final WishlistDataConverter wishlistConverter;
	private final ProductDataConverter productConverter;

	private final CreateWishlistProduct createWishlistProduct;
	private final DeleteWishlistProduct deleteWishlistProduct;
	private final GetProductWishlistClient getProductWishlistClient;
	private final GetAllProductsWishlistClient getAllProductsWishlistClient;

	WishlistController(WishlistDataConverter wishlistConverter, CreateWishlistProduct createWishlistProduct,
                       DeleteWishlistProduct deleteWishlistProduct, GetProductWishlistClient getProductWishlistClient,
                       GetAllProductsWishlistClient getAllProductsWishlistClient, ProductDataConverter productConverter) {
		this.createWishlistProduct = createWishlistProduct;
		this.wishlistConverter = wishlistConverter;
		this.deleteWishlistProduct = deleteWishlistProduct;
		this.getProductWishlistClient = getProductWishlistClient;
		this.getAllProductsWishlistClient = getAllProductsWishlistClient;
		this.productConverter = productConverter;
	}

	@Operation(
			summary = "Creates a new wishlist or adds a product to an existing one",
			description = "Creates a new wishlist if one does not exist, or adds a product to the existing wishlist",
			tags = { "Wishlists" },
			responses = {
					@ApiResponse(description = "Wishlist created", responseCode = "201"),
					@ApiResponse(description = "Product added to existing wishlist", responseCode = "200"),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
			}
	)
	@PostMapping
	public ResponseEntity<Void> createWishlist(@RequestBody @Valid WishlistRequestDataModel requestModel) throws Exception {
		boolean isNew = createWishlistProduct.call(wishlistConverter.toWishlist(requestModel));
		return isNew ? ResponseEntity.status(HttpStatus.CREATED).build() : ResponseEntity.ok().build();
	}

	@Operation(summary = "Removes a product from an existing wishlist", description = "Removes a product from an existing wishlist", tags = {
			"Wishlists" }, responses = { @ApiResponse(description = "No content", responseCode = "204"),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content) })
	@DeleteMapping("{clientId}/{productId}")
	public ResponseEntity<?> deleteWishlist(@PathVariable(name = "clientId", required = true) String clientId,
											@PathVariable(name = "productId", required = true) String productId)  {
		deleteWishlistProduct.call(clientId, productId);
		return ResponseEntity.noContent().build();
	}


	@Operation(summary = "Return all products from a client in pages of 5 itens", description = "Return all products from a client in pages of 5 itens", tags = {
			"Wishlists" }, responses = { @ApiResponse(description = "Success", responseCode = "200"),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content) })

	@PostMapping("/find-all")
	public ResponseEntity<?> findAllByClientId(@RequestBody @Valid WishlistProductPageRequestModel requestModel)  {
		var allValues = getAllProductsWishlistClient.call(wishlistConverter.toWishlistPage(requestModel));
		return ResponseEntity.ok().body(productConverter.toProductPageResponse(allValues));
	}

	@Operation(summary = "Checks if a product exists in an wishlist of a client", description = "Checks if a product exists in an wishlist of a client", tags = {
			"Wishlists" }, responses = { @ApiResponse(description = "Success", responseCode = "200"),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content) })
	@GetMapping("{clientId}/{productId}")
	public ResponseEntity<?> findIfProductExistsInWhislist(@PathVariable(name = "clientId", required = true) String clientId,
			@PathVariable(name = "productId", required = true) String productId)  {
		return ResponseEntity.ok(getProductWishlistClient.call(clientId, productId));
	}
	


}
