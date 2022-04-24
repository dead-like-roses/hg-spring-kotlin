package eu.xetoo.koza_spring.image

import eu.xetoo.koza_spring.config.services.UserDetailsImpl
import eu.xetoo.koza_spring.image.request.ImageUploadRequest
import eu.xetoo.koza_spring.image.response.ImageCreateResponse
import eu.xetoo.koza_spring.user.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.access.annotation.Secured
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.util.*


@RestController
@RequestMapping("/image")
@CrossOrigin(originPatterns = ["*"])
class ImageController(
    private val imageService: ImageService,
    private val userRepository: UserRepository
) {

    @Secured("ROLE_USER")
    @GetMapping("/{id}")
    fun getImage(@PathVariable id: String): ResponseEntity<ByteArray> {
        return ResponseEntity
            .ok()
            .contentType(MediaType.IMAGE_PNG)
            .body(imageService.getFile(UUID.fromString(id)).data)
    }

    @Secured("ROLE_ADMIN")
    @PostMapping(consumes = ["multipart/form-data"])
    fun postImage(
        @RequestParam("file") file: MultipartFile
    ): ImageCreateResponse {
        return imageService.storeFile(
            file,
            "image/png",
            user = userRepository.findByLogin("ola@gmail.com").get()
        ).let {
            ImageCreateResponse(it.id!!)
        }
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/{id}/delete")
    @ResponseStatus(HttpStatus.OK)
    fun deleteImage(@PathVariable id: UUID) {
        imageService.deleteFile(id)
    }
}