package eu.xetoo.koza_spring.shoppingcart

import eu.xetoo.koza_spring.product.Product
import eu.xetoo.koza_spring.product.ProductOption
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface ShoppingCartItemRepository : CrudRepository<ShoppingCartItem, UUID> {
    fun findAllByShoppingCart(shoppingCart: ShoppingCart): List<ShoppingCartItem>
    fun existsByShoppingCartAndProductAndOption(shoppingCart: ShoppingCart, product: Product, option: ProductOption): Boolean
    fun deleteByShoppingCart(shoppingCart: ShoppingCart)
}