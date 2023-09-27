package com.intuit.exercise.model;

import com.intuit.exercise.exception.NotFoundException;
import com.intuit.exercise.exception.RuntimeApplicationException;
import com.intuit.exercise.model.enums.ServiceMessages;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static com.intuit.exercise.transformer.PlayerCsvTransformer.transferResponseStringToPlayerObject;

@Repository
@Slf4j
public class PlayerCsvRepository implements PlayerRepository{

    @Value("${app.inputFileName:player.csv}")
    protected String inputFileName;

    @Override
    public List<Player> getPlayers()  {
        List<Player> players = new ArrayList<>();

        List<String[]> data = getPlayersFromFile();

        if (data.isEmpty()) {
            log.error("File {} is empty", inputFileName);
            throw new NotFoundException(String.format(("File %s is empty"), inputFileName));
        }

        for (String[] row : data) {
            players.add(transferResponseStringToPlayerObject(row));
        }

        return players;
    }

    List<String[]> getPlayersFromFile() {
        List<String[]> data;
        // Load the CSV file from the resources folder
        FileReader fileReader ;
        Path path = Path.of("src", "main", "resources", inputFileName);
        try {
            fileReader = new FileReader(path.toFile());
        } catch (FileNotFoundException e) {
            log.error("File {} does not exist", inputFileName);
            throw new NotFoundException(String.format(("File %s does not exist"), inputFileName));
        }
        // Skip the header row
        CSVReader csvReader = new CSVReaderBuilder(fileReader).withSkipLines(1).build();

        try {
            data = csvReader.readAll();
            csvReader.close();
        } catch (IOException | CsvException e) {
            log.error("Error while reading input file");
            throw new RuntimeApplicationException(new ErrorResponseDto(
                    HttpStatus.FORBIDDEN, ServiceMessages.INVALID_INPUT, "Error while reading input file"));
        }
        return data;
    }
}
