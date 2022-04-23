package eu.xetoo.koza_spring.user.response

data class LoginResponse(
    val token: String,
    val admin: Boolean
)
