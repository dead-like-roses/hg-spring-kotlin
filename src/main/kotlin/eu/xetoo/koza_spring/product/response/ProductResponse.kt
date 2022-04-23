package eu.xetoo.koza_spring.product.response

import java.util.UUID

data class ProductResponse(
    val id: UUID,
    val image: UUID,
    val name: String,
    val description: String,
    val packagingType: String,
    val price: Double,
    val available: Boolean,
    val visible: Boolean = true,
    val options: Map<UUID, String>
)