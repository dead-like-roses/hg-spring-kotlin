package eu.xetoo.koza_spring.user.request

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
)
