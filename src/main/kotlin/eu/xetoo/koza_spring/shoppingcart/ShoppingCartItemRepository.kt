package eu.xetoo.koza_spring.shoppingcart

import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface ShoppingCartItemRepository : CrudRepository<ShoppingCartItem, UUID> {
    fun findAllByShoppingCart(shoppingCart: ShoppingCart): List<ShoppingCartItem>
}