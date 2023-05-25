package org.atamertc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class MusteriEkleme {
    public static void main(String[] args) {
        System.out.println("************************************");
        System.out.println("******    Musteri EKleme     *******");
        System.out.println("************************************");
        System.out.println();
        Scanner sc = new Scanner(System.in);
        String ad, soyad, telefon, cinsiyet;
        Long dtarih;
        System.out.println("Musteri adi: ");
        ad = sc.nextLine();
        System.out.println("Musteri soyadi: ");
        soyad = sc.nextLine();
        System.out.println("Musteri telefonu: ");
        telefon = sc.nextLine();
        System.out.println("Musteri cinsiyeti: ");
        cinsiyet = sc.nextLine();
        System.out.println("Musteri dogum tarihi: ");
        dtarih = Long.parseLong(sc.nextLine());
        save(ad, soyad, telefon, cinsiyet, dtarih);
    }

    public static void save(String ad, String soyad, String telefon, String cinsiyet, Long dtarih) {
        String connectionAddress = "jdbc:postgresql://localhost:5432/sabah1_satisDB?user=postgres&password=root";
        String SQL_INSERT = "INSERT INTO tblmusteri(ad, soyad, telefon, cinsiyet, dtarih)" +
                "VALUES(?,?,?,?,?)";
        try (Connection connection = DriverManager.getConnection(connectionAddress)) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1,ad);
            preparedStatement.setString(2,soyad);
            preparedStatement.setString(3,telefon);
            preparedStatement.setString(4,cinsiyet);
            preparedStatement.setLong(5,dtarih);
            int eklenen = preparedStatement.executeUpdate();
            if (eklenen>0)
                System.out.println("Ekleme tamam");
            else System.out.println("Ekleme hatasi");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
