package org.atamertc;

import java.sql.*;
import java.util.Scanner;

public class MusteriArama {
    public static void main(String[] args) {
        System.out.println("************************************");
        System.out.println("******    Musteri EKleme     *******");
        System.out.println("************************************");
        System.out.println();
        Scanner sc = new Scanner(System.in);
        String aranan;
        System.out.println("Aradiginiz musterinin adi: ");
        aranan = sc.nextLine();
        findByName(aranan);
    }

    private static void findByName(String adargs) {
        String connectionAddress = "jdbc:postgresql://localhost:5432/sabah1_satisDB?user=postgres&password=root";
        String sql_Select = "Select * from tblmusteri where ad like ?";
        try (Connection connection = DriverManager.getConnection(connectionAddress)) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql_Select);
            preparedStatement.setString(1, "%" + adargs + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String ad = resultSet.getString("ad");
                String soyad = resultSet.getString("soyad");
                String telefon = resultSet.getString("telefon");
                String cinsiyet = resultSet.getString("cinsiyet");
                long dtarih = resultSet.getLong("dtarih");
                System.out.printf("|Ad : %-10s| |Soyad : %-10s| |Telefon : %-10s| |Cinsiyet : %-15s| |Dtarihi : %-15d| %n"
                        , ad, soyad, telefon, cinsiyet, dtarih);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
