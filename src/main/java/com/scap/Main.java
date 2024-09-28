package com.scap;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        // Read the files json in the directory docs
        try (Stream<Path> paths = Files.walk(Paths.get("output/"))) {
            List<Aircraft> manyAircraft = new ArrayList<>();
            paths.filter(Files::isRegularFile).forEach(path -> {
                try {
                    byte[] bytes = Files.readAllBytes(path);
                    ObjectMapper mapper = new ObjectMapper();
                    Aircraft aircraft = mapper.readValue(bytes, Aircraft.class);
                    manyAircraft.add(aircraft);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            // Insert in database the registers
            insertMany(manyAircraft);
        }
    }

    private static void insertMany(List<Aircraft> aircraft) {
        // Open a connection
        try (Connection conn = DriverManager.getConnection("jdbc:h2:C:/Users/Saturno/IdeaProjects/Scrapaircraft.History/sql/Scrapaircraft;AUTO_SERVER=TRUE", "", "")) {
            for (Aircraft singleAircraft : aircraft) {
                PreparedStatement statement = conn.prepareStatement("INSERT INTO DETAILS(CAPACITY,POWERPLANTS,PERFORMANCE,TYPE,DIMENSIONS,RELATEDAIRCRAFT,COUNTRYORIGIN,WEIGHTS,HISTORY,NAME,PRODUCTION) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

                statement.setString(1, singleAircraft.capacity);
                statement.setString(2, singleAircraft.powerPlants);
                statement.setString(3, singleAircraft.performance);
                statement.setString(4, singleAircraft.type);
                statement.setString(5, singleAircraft.dimensions);
                statement.setString(6, singleAircraft.relatedAircraft);
                statement.setString(7, singleAircraft.countryOrigin);
                statement.setString(8, singleAircraft.weights);
                statement.setString(9, singleAircraft.history);
                statement.setString(10, singleAircraft.name);
                statement.setString(11, singleAircraft.production);
                statement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
