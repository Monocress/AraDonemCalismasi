package com.dorukt.odev1.entities;

import java.time.LocalDate;

public class Mudur extends Personel {
	
	private int duzenlenenToplantiSayisi;


	public Mudur(String id, String adSoyad, LocalDate iseGirisTarihi, int maas,
			Departman departman, int duzenlenenToplantiSayisi) {
		super(EPersonelTipleri.MUDUR, id, adSoyad, iseGirisTarihi, maas, departman);
		this.duzenlenenToplantiSayisi = duzenlenenToplantiSayisi;
	}

	public Mudur() {
		super(EPersonelTipleri.MUDUR);
		System.out.println("Lütfen Adınızı ve soyadınızı girin");
		setAdSoyad(sc.nextLine());
		System.out.println("Lütfen personel Id'sini girin");
		setId(sc.nextLine());
		setIseGirisTarihi(LocalDate.now());
		System.out.println("Lütfen personel maaşını girin.");
		setMaas(sc.nextInt());
		sc.nextLine();
	}

	public int getDuzenlenenToplantiSayisi() {
		return duzenlenenToplantiSayisi;
	}

	public void setDuzenlenenToplantiSayisi(int duzenlenenToplantiSayisi) {
		this.duzenlenenToplantiSayisi = duzenlenenToplantiSayisi;
	}

	@Override
	public String toString() {
		return "Mudur [duzenlenenToplantiSayisi=" + duzenlenenToplantiSayisi + super.toString() + "]";
	}

}
