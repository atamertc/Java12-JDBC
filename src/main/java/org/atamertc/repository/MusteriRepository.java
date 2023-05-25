package org.atamertc.repository;

import org.atamertc.repository.entity.Adres;
import org.atamertc.repository.entity.Musteri;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MusteriRepository implements ICrud<Musteri> {
    private ConnectionProvider connectionProvider;
    String SQL;

    public MusteriRepository() {
        this.connectionProvider = new ConnectionProvider();
    }

    @Override
    public void save(Musteri musteri) {
        SQL = "INSERT INTO tblmusteri(ad,soyad,telefon,cinsiyet,dtarih) " +
                "VALUES('" + musteri.getAd() + "'," +
                "'" + musteri.getSoyad() + "'," +
                "'" + musteri.getTelefon() + "'," +
                "'" + musteri.getCinsiyet() + "',"
                + musteri.getTelefon() + ")";
        connectionProvider.executeUpdate(SQL);
    }

    @Override
    public void update(Musteri musteri) {
        SQL = "UPDATE tblmusteri " +
                "SET ad='" + musteri.getAd() + "'," +
                "soyad='" + musteri.getSoyad() + "'," +
                "telefon='" + musteri.getTelefon() + "'," +
                "cinsiyet='" + musteri.getCinsiyet() + "'," +
                "dtarih=" + musteri.getDtarih() + " " +
                "WHERE id=" + musteri.getId();
        connectionProvider.executeUpdate(SQL);
    }

    @Override
    public void delete(int id) {
        SQL = "DELETE FROM tblmusteri WHERE id=" + id;
        connectionProvider.executeUpdate(SQL);
    }

    @Override
    public List<Musteri> findAll() {
        SQL = "SELECT * FROM tblmusteri";
        Optional<ResultSet> resultSet = connectionProvider.getAllData(SQL);
        if (resultSet.isEmpty()) {
            return new ArrayList<>();
        }
        List<Musteri> musteriList = new ArrayList<>();
        try {
            while (resultSet.get().next()) {
                int id = resultSet.get().getInt("id");
                String ad = resultSet.get().getString("ad");
                String soyad = resultSet.get().getString("soyad");
                String telefon = resultSet.get().getString("telefon");
                String cinsiyet = resultSet.get().getString("cinsiyet");
                long dtarih = resultSet.get().getLong("dtarih");
                int state = resultSet.get().getInt("state");
                long olusturmatarihi = resultSet.get().getLong("olusturmatarihi");
                long guncellemetarihi = resultSet.get().getLong("guncellemetarihi");
                musteriList.add(new Musteri(id, ad, soyad, telefon, cinsiyet, dtarih));
            }
            resultSet.get().close();
            return musteriList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public Musteri findById(int idargs) {
        SQL = "SELECT * FROM tblmusteri WHERE id=" + idargs;
        Optional<ResultSet> resultSet = connectionProvider.getAllData(SQL);
        if (resultSet.isEmpty()) {
            return new Musteri();
        }
        try {
            resultSet.get().next();
            int id = resultSet.get().getInt("id");
            String ad = resultSet.get().getString("ad");
            String soyad = resultSet.get().getString("soyad");
            String telefon = resultSet.get().getString("telefon");
            String cinsiyet = resultSet.get().getString("cinsiyet");
            long dtarih = resultSet.get().getLong("dtarih");
            int state = resultSet.get().getInt("state");
            long olusturmatarihi = resultSet.get().getLong("olusturmatarihi");
            long guncellemetarihi = resultSet.get().getLong("guncellemetarihi");
            return new Musteri(id, ad, soyad, telefon, cinsiyet, dtarih);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Musteri();
    }

    @Override
    public List<Musteri> findByAny(String findTur) {
        return null;
    }
}
