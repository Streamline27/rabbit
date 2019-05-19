package lv.citadele.producer

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller {
    @GetMapping("/ping")
    fun ping() = "Ping from receiver"
}