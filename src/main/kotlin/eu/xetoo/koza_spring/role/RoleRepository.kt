package eu.xetoo.koza_spring.role

import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface RoleRepository: JpaRepository<Role, Long> {
    fun findByName(name: ERole): Optional<Role>
}