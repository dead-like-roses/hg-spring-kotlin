package eu.xetoo.koza_spring.user.request

data class LoginRequest(
    val login: String,
    val password: String
)
