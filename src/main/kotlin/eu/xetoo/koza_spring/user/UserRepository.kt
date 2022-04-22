package eu.xetoo.koza_spring.user

import org.springframework.data.repository.CrudRepository
import java.util.*

interface UserRepository : CrudRepository<User, UUID> {

    fun findByLogin(login: String): Optional<User>

}