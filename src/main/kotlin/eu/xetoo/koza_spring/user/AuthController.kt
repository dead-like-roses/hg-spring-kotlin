package eu.xetoo.koza_spring.user

import eu.xetoo.koza_spring.token.TokenService
import eu.xetoo.koza_spring.user.request.LoginRequest
import eu.xetoo.koza_spring.user.request.RegisterRequest
import eu.xetoo.koza_spring.user.response.LoginResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RequestMapping("/auth")
@RestController
class AuthController(
    private val userService: UserService,
    private val tokenService: TokenService
) {

    @PostMapping("/register")
    fun register(@RequestBody registerRequest: RegisterRequest): ResponseEntity<HttpStatus> {
        //TODO verify
        userService.register(
            registerRequest.name,
            registerRequest.email,
            registerRequest.login,
            registerRequest.password
        )
        return ResponseEntity(HttpStatus.OK)
    }

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): LoginResponse {
        val token = tokenService.login(loginRequest.login, loginRequest.password)
        return LoginResponse(token.toString())
    }

}