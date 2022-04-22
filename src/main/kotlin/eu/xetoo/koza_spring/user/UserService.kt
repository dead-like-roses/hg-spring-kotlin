package eu.xetoo.koza_spring.user

import eu.xetoo.koza_spring.config.PasswordEncoder
import eu.xetoo.koza_spring.role.ERole
import eu.xetoo.koza_spring.role.RoleRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDateTime

@Service
class UserService(
    private val db: UserRepository,
    private val roleRepository: RoleRepository,
    private val passwordEncoder: PasswordEncoder
) {

    fun register(username: String, login: String, password: String) {
        val role = roleRepository.findByName(ERole.ROLE_USER)
            .orElseThrow { ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR) }
        val user = User(
            name = username,
            login = login,
            password = passwordEncoder.encode(password),
            changedPassword = LocalDateTime.now(),
            role = role
        )
        db.save(user)
    }
}