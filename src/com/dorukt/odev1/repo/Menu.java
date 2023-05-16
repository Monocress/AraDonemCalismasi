package com.dorukt.odev1.repo;

public abstract class Menu {

	/**
	 * Raporlama menü içeriklerini taşıyan ve yazdıran metod.
	 */
	public static void reporlamaMenu() {
		System.out.println("Raporlama işlemlerine hoşgeldiniz.");
		System.out.println("1- Departman Personel Listesi");
		System.out.println("2- En çok personel bulunan Departman");
		System.out.println("3- Departmanlara göre maaş ortalamaları");
		System.out.println("4- Müdürlerin sorumlu olduğu departman listesi");
		System.out.println("5- Kayıt tarihlerine göre personellerin sıralı listesi");
		System.out.println("6- Aynı gün içinde işe başlayan personellerin listesi");
		System.out.println("0- Önceki menü");
	}

	/**
	 * Muhasebe işlemleri menüsünün metinlerini tutan ve yazdıran metod.
	 */
	public static void muhasebeIslemleriMenu() {
		System.out.println("Muhasebe işlemlerine hoşgeldiniz.");
		System.out.println("1 - Personele maaş ataması yap.");
		System.out.println("2 - Tüm personellerin maaşlarını listele");
		System.out.println("0 - Önceki menüye dön.");
	}

	/**
	 * Ana Menü seçeneklerini içeren ve yazdıran metod
	 * 
	 */
	public static void anaMenu() {
		System.out.println("--- Personel Takip sistemi ---");
		System.out.println("1- Personel/Departman işlemleri");
		System.out.println("2- Muhasebe işlemleri");
		System.out.println("3- Raporlamalar");
		System.out.println("0- Çıkış");
	}

	/**
	 * Personel menüsünün içeriklerini tutan ve yazdıran metod
	 */
	public static void personelMenu() {
		System.out.println("Personel/Departman işlemlerine hoşgeldiniz.");
		System.out.println("1 - Personel Ekleme");
		System.out.println("2 - Personel Sil");
		System.out.println("3 - Personel Listesi");
		System.out.println("4 - Personel Düzenleme");
		System.out.println("5 - Sisteme Yeni Departman Ekle/Güncelle");
		System.out.println("6 - Departman Listesi");
		System.out.println("7 - Ana menüye dön.");
		System.out.println("8 - Programı sonlandır.");
	}

	/**
	 * Sadece departman işlemlerini içeren alt menü çıktılarını tutan ve yazdıran
	 * metod
	 */
	public static void departmanMenu() {
		System.out.println("## Departman işlemleri ##");
		System.out.println("1 - Departman Ekleme");
		System.out.println("2 - Departman Güncelleme");
		System.out.println("0 - Vazgeç");
	}

	/**
	 * Personel güncelleme işlemleri için gerekli metinlerin tutulduğu ve
	 * yazdırıldığı metod.
	 */
	public static void updateMenu() {
		System.out.println("1 - Ad Soyad düzenle");
		System.out.println("2 - Işe giriş tarihi düzenle");
		System.out.println("3 - Departmanı değiştir");
		System.out.println("4 - Personel tipi özellikleri");
		System.out.println("5 - Personel sil");
		System.out.println("0 - Geri dön");
	}

	/**
	 * Departman güncelleme menüsünün metinlerini içeren ve yazdıran metod.
	 */
	public static void departmanUpdateMenu() {
		System.out.println("1- Departman adını güncelle");
		System.out.println("2- Müdür Ataması yap");
		System.out.println("3- Departman Listesini bosalt");
		System.out.println("4- Departmana calisan ekle");
		System.out.println("5- Cikis");
		System.out.println("Lütfen yapılacak islemi seçin:");
	}


}
