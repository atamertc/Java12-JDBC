package org.atamertc.repository;

import org.atamertc.repository.entity.Musteri;

public class Runner {
    public static void main(String[] args) {
        MusteriRepository repository = new MusteriRepository();
        //repository.findAll().forEach(System.out::println);

        //System.out.println(repository.findById(1005));

        //repository.save(new Musteri(1002, "Matamert", "Cokor", "23123123", "Transgender", 123123123));

        //repository.delete(1004);

        Musteri arananMusteri = repository.findById(1000);
        arananMusteri.setAd("Ali Cemal");
        repository.update(arananMusteri);
    }
}
