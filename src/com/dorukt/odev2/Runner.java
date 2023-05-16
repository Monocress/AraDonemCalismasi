package com.dorukt.odev2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Runner {

	public static void main(String[] args) {
		ArrayList<Personel> personelListesi = new ArrayList<>();
		personelListesi.add(new Personel("Ahmet", 30));
		personelListesi.add(new Personel("Ayşe", 25));
		personelListesi.add(new Personel("Mehmet", 35));
		personelListesi.add(new Personel("Fatma", 28));
		personelListesi.add(new Personel("Ali", 32));
		personelListesi.add(new Personel("Zeynep", 29));
		personelListesi.add(new Personel("Mustafa", 40));
		personelListesi.add(new Personel("Esra", 27));
		personelListesi.add(new Personel("Ömer", 33));
		personelListesi.add(new Personel("Gül", 26));
		personelListesi.add(new Personel("Hakan", 31));
		personelListesi.add(new Personel("Elif", 24));
		personelListesi.add(new Personel("Serkan", 38));
		personelListesi.add(new Personel("Deniz", 23));
		personelListesi.add(new Personel("Sevgi", 30));
		personelListesi.add(new Personel("Murat", 37));
		personelListesi.add(new Personel("Selin", 22));
		personelListesi.add(new Personel("Cem", 29));
		personelListesi.add(new Personel("Pınar", 36));
		personelListesi.add(new Personel("Emre", 27));

		// 1- Soru: Yaşı 30'dan büyük olan personellerin sayısını bulunuz.
		long otuzdanBuyuk = personelListesi.stream().filter(x -> x.getYas() > 30).count();
		System.out.println(otuzdanBuyuk);
		System.out.println("----------------------------------------------------------");

		// 2- Soru: İsimleri "A" harfiyle başlayan personellerin sayısı
		long aIleBaslayanlar = personelListesi.stream().filter(x -> x.getAd().startsWith("A")).count();
		System.out.println(aIleBaslayanlar);
		System.out.println("----------------------------------------------------------");

		// 3- Soru: Yaşı en büyük olan personelin adı
		String enYasliPersonel = personelListesi.stream().max(Comparator.comparing(Personel::getYas)).get().getAd();
		System.out.println(enYasliPersonel);
		System.out.println("----------------------------------------------------------");

		// 4- Soru: İsmi "Ali" olan personelin yaşı
		int alininYasi = personelListesi.stream().filter(x -> x.getAd().equalsIgnoreCase("Ali")).findFirst().get()
				.getYas();
		System.out.println(alininYasi);
		System.out.println("----------------------------------------------------------");

		// 5- Soru: Yaşı en küçük olan 3 personelin isimleri
		personelListesi.stream().sorted(Comparator.comparing(Personel::getYas)).limit(3)
				.forEach(x -> System.out.println(x.getAd()));

		System.out.println("----------------------------------------------------------");
		// 6- Soru: Yaşı 25 ile 35 arasında olan personellerin sayısı
		long araliktakiPersonelSayisi = personelListesi.stream().filter(x -> x.getYas() > 25 && x.getYas() < 35)
				.count();
		System.out.println(araliktakiPersonelSayisi);

		// 7- Soru: İsimleri 4 harften uzun olan personellerin isimleri
		System.out.println("----------------------------------------------------------");
		personelListesi.stream().filter(x -> x.getAd().length() > 4).forEach(x -> System.out.println(x.getAd()));

		// 8- Soru: Yaşı en büyük 2 personelin isimleri
		System.out.println("----------------------------------------------------------");
		personelListesi.stream().sorted(Comparator.comparing(Personel::getYas).reversed()).limit(2)
				.forEach(x -> System.out.println(x.getAd()));

		// 9- Soru: İsimleri "a" harfi ile biten personellerin isimleri, büyük harf
		// dönüşümü yapılarak yazdırınız
		System.out.println("----------------------------------------------------------");
		personelListesi.stream().filter(x -> x.getAd().endsWith("a"))
				.forEach(x -> System.out.println(x.getAd().toUpperCase()));

		// 10- Soru: İsimlerin uzunluğunun ortalaması kaçtır?
		System.out.println("----------------------------------------------------------");
		double isimUzunlukOrtalamasi = personelListesi.stream().mapToDouble(x -> x.getAd().length()).average()
				.getAsDouble();
		System.out.println(isimUzunlukOrtalamasi);

		// 11- Soru: İsmi en uzun olan personelin yaşı kaçtır?
		System.out.println("----------------------------------------------------------");
		int enUzunIsimliPersonelinYasi = personelListesi.stream()
				.max(Comparator.comparing(personel -> personel.getAd().length())).get().getYas();
		System.out.println(enUzunIsimliPersonelinYasi);

		// 12- Soru: İsimlerin herhangi biri "ahmet" mi? (boolean dönüş yapmalı)
		System.out.println("----------------------------------------------------------");
		boolean ahmetMi = personelListesi.stream().map(Personel::getAd).anyMatch(x -> x.equals("ahmet"));
		System.out.println(ahmetMi);

		// 13- Soru: İsimleri ters çevirilmiş olarak alfabetik olarak sıralanmış bir
		// liste oluşturun.
		System.out.println("----------------------------------------------------------");
		List<String> yeniListe = personelListesi.stream().map(x -> {
			String kelime = "";
			for (int i = x.getAd().length() - 1; i >= 0; i--) {
				kelime += x.getAd().charAt(i);
			}
			return kelime;
		}).sorted().toList();
		yeniListe.stream().forEach(System.out::println);

		// 14- Soru: İsimlerinde "e" harfi geçen personellerin isimlerini bir listeye
		// ekleyin.
		System.out.println("----------------------------------------------------------");
		List<String> eHarfiOlanlar = personelListesi.stream().filter(x -> x.getAd().contains("e")).map(Personel::getAd)
				.toList();
		eHarfiOlanlar.stream().forEach(System.out::println);

		// 15- Soru: Yaşı en küçük olan personelin adı ve yaşını bir Map olarak
		// döndürün.
		System.out.println("----------------------------------------------------------");
		Map<String, Integer> enKucukPersonel = personelListesi.stream().sorted(Comparator.comparing(Personel::getYas))
				.limit(1).collect(Collectors.toMap(Personel::getAd, Personel::getYas));
		System.out.println(enKucukPersonel);
	}

}
