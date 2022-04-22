package eu.xetoo.koza_spring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KozaSpringApplication

fun main(args: Array<String>) {
    runApplication<KozaSpringApplication>(*args)
}
