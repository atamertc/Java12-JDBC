package org.atamertc.repository;

import java.sql.*;
import java.util.Optional;

public class ConnectionProvider {
    String connectionAddress = "jdbc:postgresql://localhost:5432/sabah1_satisDB";
    String username = "postgres";
    String password = "root";
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    private boolean openConnection() {
        try {
            connection = DriverManager.getConnection(connectionAddress, username, password);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public void closeConnection() {
        try {
            if (!connection.isClosed())
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean executeUpdate(String SQL) {
        if (openConnection()) {
            try {
                preparedStatement = connection.prepareStatement(SQL);
                preparedStatement.executeUpdate();
                closeConnection();
                return true;
            } catch (SQLException e) {
                closeConnection();
            }
        }
        return false;
    }

    public Optional<ResultSet> getAllData(String SQL) {
        if (openConnection()) {
            try {
                preparedStatement = connection.prepareStatement(SQL);
                resultSet = preparedStatement.executeQuery();
                closeConnection();
                return Optional.of(resultSet);
            } catch (SQLException e) {
                closeConnection();
            }
        }
        return Optional.empty();
    }
}







