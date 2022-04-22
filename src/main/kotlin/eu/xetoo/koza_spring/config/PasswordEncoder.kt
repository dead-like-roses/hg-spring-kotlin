package eu.xetoo.koza_spring.config

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class PasswordEncoder: BCryptPasswordEncoder()