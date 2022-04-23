package eu.xetoo.koza_spring.token

import eu.xetoo.koza_spring.config.PasswordEncoder
import eu.xetoo.koza_spring.user.User
import eu.xetoo.koza_spring.user.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDateTime
import java.util.UUID

@Service
class TokenService(
    val tokenRepository: TokenRepository,
    val userRepository: UserRepository,
    val passwordEncoder: PasswordEncoder
) {

    fun login(login: String, password: String): Token {
        val user = userRepository.findByLogin(login).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
        if (!user.verified) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }
        if (!passwordEncoder.matches(password, user.password)) {
            throw ResponseStatusException(HttpStatus.ALREADY_REPORTED)
        }
        return tokenRepository.save(
            Token(user, LocalDateTime.now().plusMonths(1))
        )
    }

    fun verifyToken(id: String): User {
        //TODO check if uuid is valid
        val tokenID = UUID.fromString(id)
        val token = tokenRepository.findById(tokenID).orElseThrow { ResponseStatusException(HttpStatus.UNAUTHORIZED) }
        return token.user
    }

    fun logout(token: UUID) {
        tokenRepository.deleteById(token)
    }
}