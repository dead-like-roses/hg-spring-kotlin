package eu.xetoo.koza_spring.product

import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface ProductRepository : CrudRepository<Product, UUID> {
    fun getAllByDeleted(deleted: Boolean): List<Product>
}