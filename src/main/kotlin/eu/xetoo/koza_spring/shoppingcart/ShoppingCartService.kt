package eu.xetoo.koza_spring.shoppingcart

import eu.xetoo.koza_spring.product.Product
import eu.xetoo.koza_spring.product.ProductOption
import eu.xetoo.koza_spring.product.ProductOptionRepository
import eu.xetoo.koza_spring.product.ProductRepository
import eu.xetoo.koza_spring.user.User
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.UUID

@Service
class ShoppingCartService(
    private val shoppingCartRepository: ShoppingCartRepository,
    private val shoppingCartItemRepository: ShoppingCartItemRepository,
    private val productRepository: ProductRepository,
    private val productOptionRepository: ProductOptionRepository
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

    fun addToCart(user: User, productID: UUID, optionID: UUID, size: Int) {
        val shoppingCart = shoppingCartRepository
            .findByUser(user)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }

        val product =
            productRepository.findById(productID).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
        val productOption =
            productOptionRepository.findById(optionID).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }

        if(shoppingCartItemRepository.existsByShoppingCartAndProductAndOption(shoppingCart, product, productOption)) {
            //change size
            return
        }

        

    }

    fun resetCart(user: User) {
        val shoppingCart = shoppingCartRepository.findByUser(user).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
        shoppingCartItemRepository.deleteByShoppingCart(shoppingCart)
    }

}