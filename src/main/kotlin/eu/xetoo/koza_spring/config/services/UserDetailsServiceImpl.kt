package eu.xetoo.koza_spring.config.services

import eu.xetoo.koza_spring.user.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(
    val db: UserRepository
): UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user = db.findByLogin(username).orElseThrow { UsernameNotFoundException("User Not Found with username: $username") }
        return UserDetailsImpl(user)
    }

}