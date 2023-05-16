package com.dorukt.odev1.repo;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.dorukt.odev1.entities.EPersonelTipleri;
import com.dorukt.odev1.entities.Personel;

public class PersonelIslemleri {
	static Scanner sc = new Scanner(System.in);
	
	/**
	 * PersonelFactory'ye tip gönderilmesini sağlar ve factoryden dönen personeli
	 * döndürür.
	 * 
	 * @return metod içerisinde belirlenen tipe göre oluşturulmuş alt sınıf
	 *         nesnesini döndürür.
	 */
	public static Personel personelOlustur() {
		Arrays.stream(EPersonelTipleri.values())
				.forEach(x -> System.out.println((x.ordinal() + 1) + " - " + x.getInfo()));
		System.out.println(8 + " - Vazgeç");
		int secim = sc.nextInt() - 1;
		if (secim < 0 || secim >= 7) {
			System.out.println("Ana Menüye dönülüyor.");
			return null;
		} else {
			EPersonelTipleri tip = EPersonelTipleri.values()[secim];
			return PersonelFactory.build(tip);
		}
		
	}

	/**
	 * Verilen personel listesi içerisinde verilen id'yi ara ve varsa id'ye sahip
	 * olan personeli döner. Eğer id yoksa null döner.
	 * 
	 * 
	 * @param list Id'nin aranacağı liste
	 * @param id   Aranacak id
	 * @return Personel
	 */
	public static Personel findById(List<Personel> list, String id) {
		Personel person = null;
		int sayac = 0;
		boolean yokMu = true;
		for (Personel personel : list) {
			sayac++;
			if (id.equals(personel.getId())) {
				System.out.println(sayac + " - " + personel.getAdSoyad() + " - " + personel.getTip().getInfo());
				yokMu = false;
				person = personel;
				break;
			}
		}
		if (yokMu) {
			throw new RuntimeException("ID does not exist.");
		} else {
			return person;
		}
	}

	/**
	 * 
	 * Verilen personel listesinden girilen id'deki personeli siler.
	 * 
	 * 
	 * @param list Personel listesi
	 * @param id   Silenecek personele ait id
	 */
	public static void deleteById(List<Personel> list, String id) {

		list.remove(findById(list, id));

	}

	/**
	 * Verilen tipteki tüm personellerin ID ve Ad Soyad bilgilerini listeler
	 * 
	 * @param tip         Listelenecek personel tipi
	 * @param personeller aramanın yapılacağı personel listesi
	 */
	public static void findAll(EPersonelTipleri tip, List<Personel> personeller) {
		for (Personel personel : personeller) {
			if (personel.getTip().equals(tip))
				System.out.println(personel.getId() + " - " + personel.getAdSoyad());
		}
	}



}
