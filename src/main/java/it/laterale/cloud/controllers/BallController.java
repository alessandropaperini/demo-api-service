package it.laterale.cloud.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import it.laterale.cloud.dtos.Ball;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Ball controller.
 */
@RestController
@RequestMapping("api/v1/ball")
public class BallController {

    @Autowired
    private Queue ballQueue;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * Input response entity.
     *
     * @param body the body
     * @return the response entity
     */
    @PostMapping
    @ApiOperation(value = "input ball", notes = "add ball from input")
    public ResponseEntity<Void> input(
            @ApiParam(name = "body", value = "the type Ball body request") @RequestBody Ball body) {
        rabbitTemplate.convertAndSend(ballQueue.getName(), body);
        return ResponseEntity.noContent().build();
    }

}
