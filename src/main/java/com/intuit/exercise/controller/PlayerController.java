package com.intuit.exercise.controller;

import com.intuit.exercise.model.Player;
import com.intuit.exercise.service.PlayerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="api/players")
@AllArgsConstructor
public class PlayerController {
    private final PlayerService playerService;
    @GetMapping(path = "{playerId}")
    Player getPlayer(@PathVariable("playerId") String id) {

        return playerService.getPlayerById(id);
    }


    @GetMapping()
    List<Player> getPlayers() {

        return playerService.getPlayers();
    }


}
