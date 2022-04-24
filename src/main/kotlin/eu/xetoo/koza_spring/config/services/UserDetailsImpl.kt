package eu.xetoo.koza_spring.config.services


import eu.xetoo.koza_spring.user.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails


class UserDetailsImpl(
    val user: User
) : UserDetails {

    private var grantedAuthorities: MutableCollection<out GrantedAuthority> = user.role.map { r -> SimpleGrantedAuthority(r.name.name) }.toMutableList()

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return grantedAuthorities
    }

    override fun getPassword(): String {
        return user.password
    }

    override fun getUsername(): String {
        return user.login
    }

}