package eu.xetoo.koza_spring.user

import eu.xetoo.koza_spring.token.TokenService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import java.util.*
import javax.servlet.http.HttpServletRequest

@RequestMapping("/user")
@RestController
@Secured("ROLE_USER")
class UserController(
    private val tokenService: TokenService
) {

    @GetMapping()
    fun getUserInfo(): ResponseEntity<HttpStatus> {
        return ResponseEntity(HttpStatus.OK)
    }

    @PostMapping("/logout")
    fun logout(request: HttpServletRequest): ResponseEntity<HttpStatus> {
        val token = request.getHeader("Authorization")
            ?: throw ResponseStatusException(HttpStatus.UNAUTHORIZED)
        tokenService.logout(UUID.fromString(token))
        return ResponseEntity(HttpStatus.OK)
    }

}