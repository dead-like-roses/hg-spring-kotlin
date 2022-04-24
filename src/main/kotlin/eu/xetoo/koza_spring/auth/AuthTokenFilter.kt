package eu.xetoo.koza_spring.auth

import eu.xetoo.koza_spring.config.services.UserDetailsImpl
import eu.xetoo.koza_spring.token.TokenService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthTokenFilter(
    private val tokenService: TokenService
) : OncePerRequestFilter() {

    private val logger: Logger = LoggerFactory.getLogger(AuthTokenFilter::class.java)

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            val token: String? = request.getHeader("Authorization")
            if (token != null && token != "") {
                val user = tokenService.verifyToken(token)
                val authentication = UsernamePasswordAuthenticationToken(
                    user, null, user.authorities
                )
                authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authentication
            }
        } catch (e: Exception) {
            logger.error("Cannot set user authentication: {}", e)
        }
        filterChain.doFilter(request, response)
    }

}