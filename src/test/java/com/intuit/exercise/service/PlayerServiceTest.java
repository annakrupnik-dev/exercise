package com.intuit.exercise.service;

import com.intuit.exercise.exception.NotFoundException;
import com.intuit.exercise.model.Player;
import com.intuit.exercise.model.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static com.intuit.exercise.transformer.PlayerCsvTransformer.transferResponseStringToPlayerObject;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.when;

class PlayerServiceTest {
    @Mock
    private PlayerRepository playerRepository;

    private PlayerService underTest;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        underTest = new PlayerService(playerRepository);
    }

    @Test
    public void itShouldGetPlayerById() {
        // given
        String playerId = "aardsda01";
        String[] row = {"aardsda01","1981", "12","27","USA","CO", "Denver","","","", "","","","David","Aardsma","David Allan","215","75","R","R","2004-04-06","2015-08-23","aardd001","aardsda01"};
        Player expectedPlayer = transferResponseStringToPlayerObject(row);

        when(playerRepository.getPlayers()).thenReturn(getSamplePlayerList());

        // when
        Player result = underTest.getPlayerById(playerId);

        // then
        assertEquals(expectedPlayer, result);
    }

    @Test
    public void itShouldNotGetPlayerById_PlayerNotFound() {
        // given
        String playerId = "aardsda0111";

        when(playerRepository.getPlayers()).thenReturn(getSamplePlayerList());

        // when
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            underTest.getPlayerById(playerId);
        });

        //then
        assertEquals("PlayerID aardsda0111 not found", exception.getMessage());
    }

    private List<Player> getSamplePlayerList() {
        List<Player> players = new ArrayList<>();

        String[] row = {"aardsda01","1981", "12","27","USA","CO", "Denver","","","", "","","","David","Aardsma","David Allan","215","75","R","R","2004-04-06","2015-08-23","aardd001","aardsda01"};
        String[] row1 = {"aardewc01","1981", "12","27","USA","CO", "Denver","","","", "","","","David","Aardsma","David Allan","215","75","R","R","2004-04-06","2015-08-23","aardd001","aardsda01"};
        players.add(transferResponseStringToPlayerObject(row));
        players.add(transferResponseStringToPlayerObject(row1));
        return players;
    }
}