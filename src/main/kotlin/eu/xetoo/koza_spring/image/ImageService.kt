package eu.xetoo.koza_spring.image

import eu.xetoo.koza_spring.user.User
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.server.ResponseStatusException
import java.util.UUID
import javax.sql.rowset.serial.SerialBlob

@Service
class ImageService(
    private val db: ImageRepository
) {

    fun storeFile(
        file: MultipartFile,
        contentType: String,
        user: User
    ): Image {
        return Image(
            data = file.bytes,
            contentType = contentType,
            owner = user
        ).let { db.save(it) }
    }

    fun deleteFile(
        id: UUID
    ) {
        if (db.existsById(id)) {
            db.deleteById(id)
        } else {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST)
        }
    }

    fun getFile(
        id: UUID
    ): Image {
        return db.findById(id).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
    }
}