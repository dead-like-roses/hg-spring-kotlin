package eu.xetoo.koza_spring.image

import eu.xetoo.koza_spring.config.services.UserDetailsImpl
import eu.xetoo.koza_spring.image.request.ImageUploadRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.annotation.Secured
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.util.*


@RestController
@RequestMapping("/image")
class ImageController(
    private val imageService: ImageService
) {

    @Secured("ROLE_USER")
    @GetMapping("/{id}")
    fun getImage(@PathVariable id: String): ResponseEntity<ByteArray> {
        return ResponseEntity(imageService.getFile(UUID.fromString(id)).data, HttpStatus.OK)
    }

    @Secured("ROLE_ADMIN")
    @PostMapping(consumes = ["multipart/form-data"])
    fun postImage(
        @RequestParam("contentType") contentType: ImageUploadRequest,
        @RequestParam("file") file: MultipartFile
    ) {
        val user =
            (SecurityContextHolder.getContext().authentication.principal as UserDetailsImpl).user
        imageService.storeFile(
            file,
            contentType.contentType,
            user
        )
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/{id}/delete")
    @ResponseStatus(HttpStatus.OK)
    fun deleteImage(@PathVariable id: UUID) {
        imageService.deleteFile(id)
    }
}