package lv.citadele.producer

import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@EnableRabbit
@SpringBootApplication
open class ReceiverApplication

fun main(args: Array<String>) {
	runApplication<ReceiverApplication>(*args)
}

