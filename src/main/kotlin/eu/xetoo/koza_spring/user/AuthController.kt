package eu.xetoo.koza_spring.user

import eu.xetoo.koza_spring.role.ERole
import eu.xetoo.koza_spring.shoppingcart.ShoppingCartService
import eu.xetoo.koza_spring.token.TokenService
import eu.xetoo.koza_spring.user.request.LoginRequest
import eu.xetoo.koza_spring.user.request.RegisterRequest
import eu.xetoo.koza_spring.user.response.LoginResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController


@RequestMapping("/auth")
@RestController
@CrossOrigin(originPatterns = ["*"])
class AuthController(
    private val userService: UserService,
    private val tokenService: TokenService,
    private val shoppingCartService: ShoppingCartService
) {

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    fun register(@RequestBody registerRequest: RegisterRequest) {
        //TODO verify
        userService.register(
            registerRequest.name,
            registerRequest.email,
            registerRequest.password
        ).let {
            shoppingCartService.insertCart(it)
        }
    }

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): LoginResponse {
        val token = tokenService.login(loginRequest.email, loginRequest.password)
        return LoginResponse(token.id!!.toString(), token.user.role.firstOrNull { it.name == ERole.ROLE_ADMIN } != null)
    }

}