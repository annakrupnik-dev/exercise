package com.intuit.exercise.service;

import com.intuit.exercise.exception.NotFoundException;
import com.intuit.exercise.model.Player;
import com.intuit.exercise.model.PlayerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@Slf4j
public class PlayerService {

    private PlayerRepository playerRepository;

    public List<Player> getPlayers() {
        return playerRepository.getPlayers();
    }

    public Player getPlayerById(String id) {

        return getPlayers().stream()
                .filter(player -> Objects.equals(player.getPlayerID(), id))
                .findFirst()
                .orElseThrow(() -> {log.error("PlayerID {} not found", id);
                                    return new NotFoundException("PlayerID " + id + " not found");
                });
    }
}
