package eu.xetoo.koza_spring.token

import eu.xetoo.koza_spring.user.User
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface TokenRepository : CrudRepository<Token, UUID> {
    fun findByUser(user: User): List<Token>
}