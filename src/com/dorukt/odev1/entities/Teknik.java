package com.dorukt.odev1.entities;

import java.time.LocalDate;

public class Teknik extends Personel {

	private int tamirEdilenAracMiktari;

	public Teknik(String id, String adSoyad, LocalDate iseGirisTarihi, int maas,
			Departman departman, int tamirEdilenAracMiktari) {
		super(EPersonelTipleri.TEKNIK_PERSONEL, id, adSoyad, iseGirisTarihi, maas, departman);
		this.tamirEdilenAracMiktari = tamirEdilenAracMiktari;
	}

	public Teknik() {
		super(EPersonelTipleri.TEKNIK_PERSONEL);
		System.out.println("Lütfen Adınızı ve soyadınızı girin");
		setAdSoyad(sc.nextLine());
		System.out.println("Lütfen personel Id'sini girin");
		setId(sc.nextLine());
		setIseGirisTarihi(LocalDate.now());
		System.out.println("Lütfen personel maaşını girin.");
		setMaas(sc.nextInt());
		sc.nextLine();
	}
	public int getTamirEdilenAracMiktari() {
		return tamirEdilenAracMiktari;
	}


	public void setTamirEdilenAracMiktari(int tamirEdilenAracMiktari) {
		this.tamirEdilenAracMiktari = tamirEdilenAracMiktari;
	}

	@Override
	public String toString() {
		return "Teknik [tamirEdilenAracMiktari=" + tamirEdilenAracMiktari + super.toString() + "]";
	}

}
