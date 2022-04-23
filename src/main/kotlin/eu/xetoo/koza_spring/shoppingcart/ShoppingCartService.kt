package eu.xetoo.koza_spring.shoppingcart

import eu.xetoo.koza_spring.product.Product
import eu.xetoo.koza_spring.product.ProductOption
import eu.xetoo.koza_spring.user.User
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class ShoppingCartService(
    private val shoppingCartRepository: ShoppingCartRepository,
    private val shoppingCartItemRepository: ShoppingCartItemRepository
) {

    fun insertCart(user: User) {
        shoppingCartRepository.save(ShoppingCart(listOf(), user))
    }

    fun getShoppingCart(user: User): List<Triple<Product, ProductOption, Int>> {
        val shoppingCart = shoppingCartRepository
            .findByUser(user)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
        return shoppingCartItemRepository.findAllByShoppingCart(shoppingCart)
            .map {
                Triple(it.product, it.option, it.size)
            }
    }

}