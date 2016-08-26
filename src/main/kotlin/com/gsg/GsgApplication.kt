package com.gsg

import java.util.concurrent.TimeUnit

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import world.World

@SpringBootApplication
open class GsgApplication : CommandLineRunner {

    @Autowired
    internal var context: AnnotationConfigApplicationContext? = null

    @Autowired
    internal var rabbitTemplate: RabbitTemplate? = null

    @Bean
    open internal fun queue(): Queue {
        return Queue(queueName, false)
    }

    @Bean
    open internal fun exchange(): TopicExchange {
        return TopicExchange("spring-boot-exchange")
    }

    @Bean
    open internal fun binding(queue: Queue, exchange: TopicExchange): Binding {
        return BindingBuilder.bind(queue).to(exchange).with(queueName)
    }

    @Bean
    open internal fun container(connectionFactory: ConnectionFactory, listenerAdapter: MessageListenerAdapter): SimpleMessageListenerContainer {
        val container = SimpleMessageListenerContainer()
        container.connectionFactory = connectionFactory
        container.setQueueNames(queueName)
        container.messageListener = listenerAdapter
        return container
    }

    @Bean
    open internal fun receiver(): Receiver {
        return Receiver()
    }

    @Bean
    open internal fun listenerAdapter(receiver: Receiver): MessageListenerAdapter {
        return MessageListenerAdapter(receiver, "receiveMessage")
    }

    @Throws(Exception::class)
    override fun run(vararg args: String) {

        println("Waiting five seconds...")
        Thread.sleep(5000)
        for (i in 1..1) {
            World.next(null)
            rabbitTemplate!!.convertAndSend(queueName,World.send())
            /*var s =""
            for (country in World.countries) {
                s+= country.name + " "
                s+=country.population
                s+=" "
                s+=country.money
                rabbitTemplate!!.convertAndSend(queueName, s)
                s=""
            }*/
        }
        receiver().latch.await(100000, TimeUnit.MILLISECONDS)
        context!!.close()
    }

    companion object {

        internal val queueName = "spring-boot"

        @Throws(InterruptedException::class)
        @JvmStatic fun main(args: Array<String>) {
            SpringApplication.run(GsgApplication::class.java, *args)
        }
    }
}