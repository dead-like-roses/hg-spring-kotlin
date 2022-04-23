package eu.xetoo.koza_spring.user

import eu.xetoo.koza_spring.user.response.UserResponse
import org.springframework.http.HttpStatus
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RequestMapping("/user")
@RestController
@Secured("ROLE_ADMIN")
class UserAdminController(
    private val userService: UserService
) {

    @GetMapping("/notaccepted")
    fun getNotAccepted(): List<UserResponse> =
        userService.getNotVerified().map { it.toResponse() }

    @PostMapping("/{id}/accept")
    @ResponseStatus(HttpStatus.OK)
    fun acceptUser(@PathVariable id: String) {
        userService.acceptUser(UUID.fromString(id))
    }

    @PostMapping("/{id}/delete")
    @ResponseStatus(HttpStatus.OK)
    fun deleteUser(@PathVariable id: String) {
        userService.deleteUser(UUID.fromString(id))
    }
}