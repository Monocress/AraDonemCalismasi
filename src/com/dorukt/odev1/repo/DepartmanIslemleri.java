package com.dorukt.odev1.repo;

import java.util.List;
import java.util.Scanner;

import com.dorukt.odev1.entities.Departman;
import com.dorukt.odev1.entities.EPersonelTipleri;
import com.dorukt.odev1.entities.Mudur;
import com.dorukt.odev1.entities.Personel;

public class DepartmanIslemleri {
	static Scanner sc = new Scanner(System.in);


	/**
	 * Verilen departman listesine yeni departman eklenmesini sağlar eğer yaratılmak
	 * istenen deparman zaten mevcut ise yaratılmasına izin vermez.
	 * 
	 * @param departmanlar yaratılacak departmanın içine ekleneceği liste.
	 */
	public static void departmanEkle(List<Departman> departmanlar) {
		boolean varMi = false;
		Departman departman1 = new Departman();
		for (Departman departman : departmanlar) {
			if (departman1.getDepartmanAdi().equals(departman.getDepartmanAdi())) {
				varMi = true;
				break;
			}
		}
		if (varMi) {
			System.out.println("Belirtilen isimde bir departman zaten mevcut");
		} else {
			departmanlar.add(departman1);
			System.out.println("Yeni departman ekledi.");
		}
	}

	/**
	 * Verilen departman listesini sıra no ve departman adı ile listeleyen metod
	 * 
	 * @param departmanlar yazdırılacak departmanları içeren liste
	 */
	public static void departmanlarıListele(List<Departman> departmanlar) {
		int sayac = 0;
		for (Departman departman : departmanlar) {
			System.out.println(++sayac + " - " + departman.getDepartmanAdi());
		}
	}


	/**
	 * 
	 * Departmanın özelliklerinin güncellenmesini sağlayan metod
	 * 
	 * @param departman Güncellemesi yapılacak olan departman
	 * @param personel  Müdür ataması istenirse müdürün bulunacağı liste
	 */
	public static void departmanUpdateMenu(Departman departman, List<Personel> personel) {
		Menu.departmanUpdateMenu();
		int secim = sc.nextInt();
		sc.nextLine();
		switch (secim) {
		case 1: // Departman adını güncelle.
			System.out.println("Yeni adı girin.");
			departman.setDepartmanAdi(sc.nextLine());
			break;

		case 2:// Müdür Ataması yap
			PersonelIslemleri.findAll(EPersonelTipleri.MUDUR, personel);
			System.out.println("Lütfen yetki verilecek müdürün id'sini girin.");
			Mudur mudur = (Mudur) PersonelIslemleri.findById(personel, sc.nextLine());
			if (departman.getSorumluMudur() != null)
				departman.getSorumluMudur().setDepartman(null);
			departman.setSorumluMudur(mudur);
			if (mudur.getDepartman() != null)
			mudur.getDepartman().setSorumluMudur(null);
			mudur.setDepartman(departman);
			System.out.println("Atama yapıldı.");
			break;
		case 3: // Departman Listesini bosalt
			System.out.println("Departmandaki çalışan listesi boşaltılacak emin misiniz? Eminseniz lütfen evet yazın.");
			String cevap = sc.nextLine();
			if (cevap.equalsIgnoreCase("evet")) {
				departman.getCalisanlar().clear();
				System.out.println("Liste boşaltıldı.");
			} else {
				System.out.println("İşlem iptal edildi.");
			}
			break;
		case 4: // Departmana calisan ekle
			int sayac= 0;
			for (Personel personel2 : personel) {
				System.out.println(++sayac +" - "+ personel2.getAdSoyad());
			}
			
			System.out.println("Lütfen atanacak personelin sıra numarasını girin");
			int index = sc.nextInt()-1;
			if (departman.getCalisanlar().add(personel.get(index))) {
				personel.get(index).setDepartman(departman);
				System.out.println("Atama başarılı");
			} else {
				System.out.println("Atama başarısız ");
			}
			break;
		case 5:
			System.out.println("Devam etmek için enter'a basın. . .");
			sc.nextLine();
		default:
			break;
		}
	}



}
