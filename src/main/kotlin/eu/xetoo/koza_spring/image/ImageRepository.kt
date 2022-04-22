package eu.xetoo.koza_spring.image

import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface ImageRepository : CrudRepository<Image, UUID>