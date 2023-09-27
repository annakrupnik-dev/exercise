package com.intuit.exercise.model;

import com.intuit.exercise.exception.NotFoundException;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PlayerCsvRepositoryTest {

    private PlayerCsvRepository underTest;

    @BeforeEach
    public void setUp() {
        underTest = new PlayerCsvRepository();

    }

    @Test
    public void itShouldGetRowsFromFile() throws IOException, CsvException {
        underTest.inputFileName = "player.csv";

        List<String[]> playersFromFile = underTest.getPlayersFromFile();

        assertEquals(playersFromFile.size(), 19370);

    }

    @Test
    public void itShouldGetPlayers() throws IOException, CsvException {
        underTest.inputFileName = "player.csv";

        List<String[]> playersFromFile = underTest.getPlayersFromFile();
        List<Player> players = underTest.getPlayers();

        assertEquals(playersFromFile.size(), players.size());

    }

    @Test
    public void itShouldNotGetPlayers_EmptyFile() throws IOException, CsvException {
        underTest.inputFileName = "player1.csv";

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            underTest.getPlayers();
        });

        assertEquals("File player1.csv is empty", exception.getMessage());
    }

    @Test
    public void itShouldNotGetPlayers_FileNotFound() throws IOException, CsvException {

        underTest.inputFileName = "player11.csv";
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            underTest.getPlayersFromFile();
        });
        assertEquals("File player11.csv does not exist", exception.getMessage());
    }

}