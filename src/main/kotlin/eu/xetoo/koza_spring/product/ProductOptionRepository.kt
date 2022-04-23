package eu.xetoo.koza_spring.product

import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface ProductOptionRepository : CrudRepository<ProductOption, UUID>