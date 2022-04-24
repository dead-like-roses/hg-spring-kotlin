package eu.xetoo.koza_spring.product

import eu.xetoo.koza_spring.image.ImageRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.UUID
import javax.transaction.Transactional

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val productOptionRepository: ProductOptionRepository,
    private val imageRepository: ImageRepository
) {

    fun insertProduct(
        name: String,
        description: String,
        imageID: String,
        price: Double,
        packageType: String,
        available: Boolean,
        visible: Boolean,
        options: List<String>
    ): Product {
        val image = imageRepository.findById(UUID.fromString(imageID))
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
        val product = Product(
            name = name,
            description = description,
            image = image,
            price = price,
            packagingType = packageType,
            available = available,
            visible = visible
        ).let {
            productRepository.save(it)
        }
        options
            .map {
                ProductOption(it, product)
            }.let {
                productOptionRepository.saveAll(it)
            }

        return productRepository.findById(product.id!!)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
    }

    fun get(id: UUID): Product =
        productRepository.findById(id)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }

    fun getAll(): List<Product> =
        productRepository.getAllByDeleted(false)

    fun updateAvailability(id: UUID, available: Boolean) {
        productRepository.findById(id)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
            .let {
                it.available = available
                productRepository.save(it)
            }
    }

    fun delete(id: UUID) {
        productRepository.findById(id)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
            .let {
                it.deleted = true
                productRepository.save(it)
            }
    }

    fun edit(
        id: UUID,
        name: String,
        description: String,
        imageID: String,
        price: Double,
        packageType: String,
        available: Boolean,
        visible: Boolean,
        options: List<String>
    ): Product {
        val image = imageRepository.findById(UUID.fromString(imageID))
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
        val changed = productRepository.findById(id)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
            .let {
                it.name = name
                it.description = description
                it.image = image
                it.price = price
                it.packagingType = packageType
                it.available = available
                it.visible = visible
                productRepository.save(it)
            }
        return changed
    }
}