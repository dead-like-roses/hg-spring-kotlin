package eu.xetoo.koza_spring.product

import org.springframework.data.repository.CrudRepository
import java.util.UUID
import javax.transaction.Transactional

interface ProductRepository : CrudRepository<Product, UUID> {
    @Transactional
    fun getAllByDeleted(deleted: Boolean): List<Product>
}