package com.dorukt.odev1.entities;

import java.time.LocalDate;
import java.util.List;

public class GenelMudur extends Personel {

	private List<Mudur> ilgiliMudurler;
	private int duzenlenenToplantiMiktari;

	public GenelMudur(String id, String adSoyad, LocalDate iseGirisTarihi, int maas, Departman departman,
			List<Mudur> ilgiliMudurler, int duzenlenenToplantiMiktari) {
		super(EPersonelTipleri.GENEL_MUDUR, id, adSoyad, iseGirisTarihi, maas, departman);
		this.ilgiliMudurler = ilgiliMudurler;
		this.duzenlenenToplantiMiktari = duzenlenenToplantiMiktari;
	}


	public GenelMudur() {
		super(EPersonelTipleri.GENEL_MUDUR);
		System.out.println("Lütfen Adınızı ve soyadınızı girin");
		setAdSoyad(sc.nextLine());
		System.out.println("Lütfen personel Id'sini girin");
		setId(sc.nextLine());
		setIseGirisTarihi(LocalDate.now());
		System.out.println("Lütfen personel maaşını girin.");
		setMaas(sc.nextInt());
		sc.nextLine();

	}

	public List<Mudur> getIlgiliMudurler() {
		return ilgiliMudurler;
	}

	public void setIlgiliMudurler(List<Mudur> ilgiliMudurler) {
		this.ilgiliMudurler = ilgiliMudurler;
	}

	public int getDuzenlenenToplantiMiktari() {
		return duzenlenenToplantiMiktari;
	}

	public void setDuzenlenenToplantiMiktari(int duzenlenenToplantiMiktari) {
		this.duzenlenenToplantiMiktari = duzenlenenToplantiMiktari;
	}

	@Override
	public String toString() {
		return "GenelMudur [ilgiliMudurler=" + ilgiliMudurler + ", duzenlenenToplantiMiktari="
				+ duzenlenenToplantiMiktari + super.toString() + "]";
	}

}
