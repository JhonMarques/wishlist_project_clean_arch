package com.br.api.wishlist.adapters.inbound.controller;

import com.br.api.wishlist.adapters.inbound.controller.mappers.WishlistProductResponseMapper;
import com.br.api.wishlist.adapters.inbound.controller.mappers.WishlistRequestMapper;
import com.br.api.wishlist.adapters.inbound.controller.mappers.WishlistResponseMapper;
import com.br.api.wishlist.adapters.inbound.controller.request.WishlistRequest;
import com.br.api.wishlist.adapters.inbound.controller.request.WishlistProductPageRequest;
import com.br.api.wishlist.adapters.inbound.controller.response.WishlistProductPageResponse;
import com.br.api.wishlist.adapters.inbound.controller.response.WishlistResponse;
import com.br.api.wishlist.application.usecases.CreateWishlistProduct;
import com.br.api.wishlist.application.usecases.DeleteWishlistProduct;
import com.br.api.wishlist.application.usecases.GetAllProductsWishlistClient;
import com.br.api.wishlist.application.usecases.GetProductWishlistClient;
import com.br.api.wishlist.application.usecases.GetWishlistByClientId;
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

	private final WishlistRequestMapper wishlistRequestMapper;
	private final WishlistProductResponseMapper wishlistProductResponseMapper;
	private final WishlistResponseMapper wishlistResponseMapper;

	private final CreateWishlistProduct createWishlistProduct;
	private final DeleteWishlistProduct deleteWishlistProduct;
	private final GetProductWishlistClient getProductWishlistClient;
	private final GetAllProductsWishlistClient getAllProductsWishlistClient;
	private final GetWishlistByClientId getWishlistByClientId;

	public WishlistController(
			WishlistRequestMapper wishlistRequestMapper,
			WishlistProductResponseMapper wishlistProductResponseMapper,
			WishlistResponseMapper wishlistResponseMapper,
			CreateWishlistProduct createWishlistProduct,
			DeleteWishlistProduct deleteWishlistProduct,
			GetProductWishlistClient getProductWishlistClient,
			GetAllProductsWishlistClient getAllProductsWishlistClient,
			GetWishlistByClientId getWishlistByClientId
	) {
		this.wishlistRequestMapper = wishlistRequestMapper;
		this.wishlistProductResponseMapper = wishlistProductResponseMapper;
		this.wishlistResponseMapper = wishlistResponseMapper;
		this.createWishlistProduct = createWishlistProduct;
		this.deleteWishlistProduct = deleteWishlistProduct;
		this.getProductWishlistClient = getProductWishlistClient;
		this.getAllProductsWishlistClient = getAllProductsWishlistClient;
		this.getWishlistByClientId = getWishlistByClientId;
	}

	@Operation(
			summary = "Creates a new wishlist or adds a product to an existing one",
			responses = {
					@ApiResponse(description = "Wishlist created", responseCode = "201"),
					@ApiResponse(description = "Product added to existing wishlist", responseCode = "200"),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
			}
	)
	@PostMapping
	public ResponseEntity<Void> createWishlist(@RequestBody @Valid WishlistRequest request) {
		var wishlist = wishlistRequestMapper.toDomain(request);
		boolean isNew = createWishlistProduct.call(wishlist);
		return isNew ? ResponseEntity.status(HttpStatus.CREATED).build() : ResponseEntity.ok().build();
	}

	@Operation(
			summary = "Removes a product from an existing wishlist",
			responses = {
					@ApiResponse(description = "No content", responseCode = "204"),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
			}
	)
	@DeleteMapping("{clientId}/{productId}")
	public ResponseEntity<Void> deleteWishlist(@PathVariable String clientId, @PathVariable String productId) {
		deleteWishlistProduct.call(clientId, productId);
		return ResponseEntity.noContent().build();
	}

	@Operation(
			summary = "Returns all products from a client in pages of 5 items",
			responses = {
					@ApiResponse(description = "Success", responseCode = "200"),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
			}
	)
	@PostMapping("/find-all")
	public ResponseEntity<WishlistProductPageResponse> findAllByClientId(@RequestBody @Valid WishlistProductPageRequest request) {
		var page = wishlistRequestMapper.toDomain(request);
		var products = getAllProductsWishlistClient.call(page);
		return ResponseEntity.ok(wishlistProductResponseMapper.toProductPageResponse(products));
	}

	@Operation(
			summary = "Checks if a product exists in the wishlist of a client",
			responses = {
					@ApiResponse(description = "Success", responseCode = "200"),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
			}
	)
	@GetMapping("/{clientId}/product/{productId}")
	public ResponseEntity<Boolean> productExistsInWishlist(@PathVariable String clientId, @PathVariable String productId) {
		return ResponseEntity.ok(getProductWishlistClient.call(clientId, productId));
	}

	@Operation(
			summary = "Returns the complete wishlist for a client",
			responses = {
					@ApiResponse(description = "Success", responseCode = "200"),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
			}
	)
	@GetMapping("/full/{clientId}")
	public ResponseEntity<WishlistResponse> getWishlist(@PathVariable String clientId) {
		var wishlist = getWishlistByClientId.call(clientId);
		return ResponseEntity.ok(wishlistResponseMapper.toResponse(wishlist));
	}
}
