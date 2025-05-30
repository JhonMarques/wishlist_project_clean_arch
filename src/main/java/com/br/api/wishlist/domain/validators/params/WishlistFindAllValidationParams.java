import com.br.api.wishlist.domain.entities.Product;

import java.util.List;

public class WishlistFindAllValidationParams {
    private final boolean wishlistExists;
    private final List<Product> products;

    public WishlistFindAllValidationParams(boolean wishlistExists, List<Product> products) {
        this.wishlistExists = wishlistExists;
        this.products = products;
    }

    public boolean isWishlistExists() {
        return wishlistExists;
    }

    public List<Product> getProducts() {
        return products;
    }
}
