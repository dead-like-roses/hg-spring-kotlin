package eu.xetoo.koza_spring.user.request

data class LoginRequest(
    val email: String,
    val password: String
)
