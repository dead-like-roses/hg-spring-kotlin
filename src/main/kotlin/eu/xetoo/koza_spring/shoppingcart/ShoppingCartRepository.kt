package eu.xetoo.koza_spring.shoppingcart

import eu.xetoo.koza_spring.user.User
import org.springframework.data.repository.CrudRepository
import java.util.Optional
import java.util.UUID

interface ShoppingCartRepository : CrudRepository<ShoppingCart, UUID> {
    fun findByUser(user: User): Optional<ShoppingCart>
}