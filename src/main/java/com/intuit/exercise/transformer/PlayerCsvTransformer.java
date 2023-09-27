package com.intuit.exercise.transformer;

import com.intuit.exercise.model.Player;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PlayerCsvTransformer {

    public static Player transferResponseStringToPlayerObject(String[] row) {
        return Player.builder()
                .playerID(row[0])
                .birthYear(getIntegerFromInput(row[1]))
                .birthMonth(getIntegerFromInput(row[2]))
                .birthDay(getIntegerFromInput(row[3]))
                .birthCountry(getStringFromInput(row[4]))
                .birthState(getStringFromInput(row[5]))
                .birthCity(getStringFromInput(row[6]))
                .deathYear(getIntegerFromInput(row[7]))
                .deathMonth(getIntegerFromInput(row[8]))
                .deathDay(getIntegerFromInput(row[9]))
                .deathCountry(getStringFromInput(row[10]))
                .deathState(getStringFromInput(row[11]))
                .deathCity(getStringFromInput(row[12]))
                .nameFirst(getStringFromInput(row[13]))
                .nameLast(getStringFromInput(row[14]))
                .nameGiven(getStringFromInput(row[15]))
                .weight(getIntegerFromInput(row[16]))
                .height(getIntegerFromInput(row[17]))
                .bats(getStringFromInput(row[18]))
                .throwsCode(getStringFromInput(row[19]))
                .debutDate(getStringFromInput(row[20]))
                .finalGameDate(getStringFromInput(row[21]))
                .retroID(getStringFromInput(row[22]))
                .bbrefID(getStringFromInput(row[23]))
                .build();
    }

    private static Integer getIntegerFromInput(String str) {
        return !StringUtils.isEmpty(str) ? Integer.parseInt(str) : null;
    }

    private static String getStringFromInput(String str) {
        return !StringUtils.isEmpty(str) ? str : null;
    }
}
