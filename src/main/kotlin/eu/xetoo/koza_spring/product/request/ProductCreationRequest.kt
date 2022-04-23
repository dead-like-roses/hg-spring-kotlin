package eu.xetoo.koza_spring.product.request

data class ProductCreationRequest(
    val image: String,
    val name: String,
    val description: String,
    val packagingType: String,
    val price: Double,
    val available: Boolean,
    val visible: Boolean,
    val options: List<String>
)
