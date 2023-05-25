package org.atamertc;

import java.sql.*;

/*
VERİTABANI DRIVER:
Bir programlama dili ile veritabanı arasında iletişim kurmaya yarayan bi yazılım veya bileşendir.
Veritabanı driver'i
    veritabanı sunucuna bağlanmak,
    veritabanıyla iletişim kurmak,
    sorgular göndermek,
    sonuçları almak
gibi bir çok temel işlevi yerine getirmede yardımcı olur.

Her bir veritabanı üreticisi her bir programlama dili için farklı driverlar üretir.
PostgreSQL jdbc Driver

Dependencies: Dışarıdan projenize dahil ettiğiniz kütüphanelere olan Bağımlılıktır.
Maven ve Gradle Build Systemleri ile dependency yönetimi otomatize edilmiş olur.

 */
public class ConnectionOrnekler {

    public static void main(String[] args) {
        /*
        1.ADIM
        # Driverin sunucuya baglanabilmesi icin gerekli parametreler
            format -> postgresql://host:port/database
            -Veritabani sunucusunun ip adresi -> localhost
            -Port numarasi -> port: 5432
            -Islem yapilacak veritabani adi -> sabah1_satisDB
        #Kullanici ve sifre bilgilerinin string ile tanimlanmasi
        2.ADIM
        # Bu parametrelerle bir connection olusturulur

        SQL cumlesini DRIVER'in anlayacagi sekle getirmek icin bu cumleyi hazirlayacak
        yapimiz olan PreparedStatement kullanilir

        PreparedStatement: SQL sorgularinizi parametreli bir sekilde calistirilmasini
        saglayan interface'dir
        Bu interface sayesinde SQL sorgularinizi dinamik olarak olusturup, sorgudaki
        parametrelerinizi duzenli bir sekilde yerlestirmenize yardimci olur.
         */

        //adresEkle(54, "Adana", "Suleymanpasa", "Ortacami", "Cinarli Cesme Sk.");
        //adresSil(15);
        //adresGuncelle(18,"Ankara","Mamak");
        //adresTumunuListele();
        adresIleGoreListele("Ankara");
    }

    public static void adresEkle(int musteriid, String il, String ilce, String mahalle, String adres) {
        String SQL = "INSERT INTO tbladres(musteriid,il,ilce,mahalle,adres) VALUES (?,?,?,?,?)";
        String connectionAddress = "jdbc:postgresql://localhost:5432/sabah1_satisDB";
        String username = "postgres";
        String password = "root";
        try (Connection connection = DriverManager.getConnection(connectionAddress, username, password)) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, musteriid);
            preparedStatement.setString(2, il);
            preparedStatement.setString(3, ilce);
            preparedStatement.setString(4, mahalle);
            preparedStatement.setString(5, adres);
            int etkilenenSatirSayisi = preparedStatement.executeUpdate();
            if (etkilenenSatirSayisi > 0) {
                System.out.println("Ekleme tamam");
            } else {
                System.out.println("Eklemede hata var");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void adresSil(int id) {
        String SQL = "DELETE FROM tbladres WHERE id=?";
        String connectionAddress = "jdbc:postgresql://localhost:5432/sabah1_satisDB";
        String username = "postgres";
        String password = "root";
        try (Connection connection = DriverManager.getConnection(connectionAddress, username, password)) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, id);
            int etkilenenSatirSayisi = preparedStatement.executeUpdate();
            if (etkilenenSatirSayisi > 0) {
                System.out.println("Silme tamam");
            } else {
                System.out.println("Silmede hata var");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void adresGuncelle(int id, String il, String ilce) {
        String SQL = "UPDATE tbladres SET il=?, ilce=? WHERE id=?";
        String connectionAddress = "jdbc:postgresql://localhost:5432/sabah1_satisDB";
        String username = "postgres";
        String password = "root";
        try (Connection connection = DriverManager.getConnection(connectionAddress, username, password)) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, il);
            preparedStatement.setString(2, ilce);
            preparedStatement.setInt(3, id);
            int etkilenenSatirSayisi = preparedStatement.executeUpdate();
            if (etkilenenSatirSayisi > 0) {
                System.out.println("Guncelleme tamam");
            } else {
                System.out.println("Guncellemede hata var");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
    Listeleme:
    Diger islemlerden (CREATE,UPDATE,DELETE) farkli olarak geriye tablo dondurur
    Tablo yapisi ResultSet ile tutulacak.
    ResultSet: Veritabani sorgusu sonucunda donen veri kumesini tasiyan bir yapidir
    ResultSet ile bu veri kumesi icerisinde gezinebiliriz.
     */

    public static void adresTumunuListele() {
        String SQL = "SELECT * FROM tbladres";
        String connectionAddress = "jdbc:postgresql://localhost:5432/sabah1_satisDB?user=postgres&password=root";
        try (Connection connection = DriverManager.getConnection(connectionAddress)) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int musteriid = resultSet.getInt("musteriid");
                String il = resultSet.getString("il");
                String ilce = resultSet.getString("ilce");
                System.out.println("Musteriid: " + musteriid + " il: " + il + " ilce: " + ilce);
            }
            resultSet.close();
            int etkilenenSatirSayisi = preparedStatement.executeUpdate();
            if (etkilenenSatirSayisi > 0) {
                System.out.println("Guncelleme tamam");
            } else {
                System.out.println("Guncellemede hata var");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Belirli bir ildeki musterilerin id'sini listeleyelim
    public static void adresIleGoreListele(String ilargs) {
        String SQL = "SELECT m.ad,a.il,a.ilce FROM tbladres AS a INNER JOIN tblmusteri as m ON a.musteriid = m.id WHERE il=?";
        String connectionAddress = "jdbc:postgresql://localhost:5432/sabah1_satisDB?user=postgres&password=root";
        try (Connection connection = DriverManager.getConnection(connectionAddress)) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, ilargs);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String ad = resultSet.getString(1);
                String il = resultSet.getString(2);
                String ilce = resultSet.getString(3);
                System.out.println(" musteriad: " + ad + " il: " + il + " ilce: " + ilce);
            }
            resultSet.close();
            int etkilenenSatirSayisi = preparedStatement.executeUpdate();
            if (etkilenenSatirSayisi > 0) {
                System.out.println("Guncelleme tamam");
            } else {
                System.out.println("Guncellemede hata var");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
    executeQuery()          |  executeUpdate()                     |   execute()
    '''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
    SELECT sorgulari        |  INSERT,UPDATE,DELETE sorgulari      |   Tum sorgular
    Returns: ResultSet      |  Returns:Int(etkilenen satir sayisi) |
    -----------------------------------------------------------------------------------
    Execute: Tum sql sorgulari icin
    Eger SQL ifadesinde INSERT UPDATE DELETE geciyorsa geriye int doner
    SELECT geciyorsa geriye ResultSet doner
    ALTER,CREATE,DROP geciyorsa geriye true veya false doner
     */


}
