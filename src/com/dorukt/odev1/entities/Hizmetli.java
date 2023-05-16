package com.dorukt.odev1.entities;

import java.time.LocalDate;

public class Hizmetli extends Personel {

	private String gorevBolgesi;

	public Hizmetli(String id, String adSoyad, LocalDate iseGirisTarihi, int maas,
			Departman departman, String gorevBolgesi) {
		super(EPersonelTipleri.HIZMETLI, id, adSoyad, iseGirisTarihi, maas, departman);
		this.gorevBolgesi = gorevBolgesi;

	}

	public Hizmetli() {
		super(EPersonelTipleri.HIZMETLI);
		System.out.println("Lütfen Adınızı ve soyadınızı girin");
		setAdSoyad(sc.nextLine());
		System.out.println("Lütfen personel Id'sini girin");
		setId(sc.nextLine());
		setIseGirisTarihi(LocalDate.now());
		System.out.println("Lütfen personel maaşını girin.");
		setMaas(sc.nextInt());
		sc.nextLine();

	}

	public String getGorevBolgesi() {
		return gorevBolgesi;
	}

	public void setGorevBolgesi(String gorevBolgesi) {
		this.gorevBolgesi = gorevBolgesi;
	}

	@Override
	public String toString() {
		return "Hizmetli [gorevBolgesi=" + gorevBolgesi + super.toString() + "]";
	}

}
