package eu.xetoo.koza_spring.user.request

data class RegisterRequest(
    val name: String,
    val login: String,
    val email: String,
    val password: String
)
