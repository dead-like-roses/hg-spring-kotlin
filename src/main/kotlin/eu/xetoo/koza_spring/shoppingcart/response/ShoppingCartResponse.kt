package eu.xetoo.koza_spring.shoppingcart.response

data class ShoppingCartResponse(
    val products: List<Triple<String, String, Int>>
)
