package eu.xetoo.koza_spring.product

import eu.xetoo.koza_spring.product.response.ProductListResponse
import org.springframework.http.HttpStatus
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.*

@RequestMapping("/product")
@RestController
@Secured("ROLE_USER")
@CrossOrigin(originPatterns = ["*"])
class ProductController(
    private val productService: ProductService
) {
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    fun getAll(): ProductListResponse =
        productService.getAll().map {
            it.toResponse()
        }.let {
            ProductListResponse(it)
        }

}