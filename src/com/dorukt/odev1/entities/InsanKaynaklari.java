package com.dorukt.odev1.entities;

import java.time.LocalDate;

public class InsanKaynaklari extends Personel {


	private int iseAlinanCalisanAdeti;
	private int duzenlenenToplantilar;

	public InsanKaynaklari(String id, String adSoyad, LocalDate iseGirisTarihi, int maas, Departman departman,
			int iseAlinanCalisanAdeti, int duzenlenenToplantilar) {
		super(EPersonelTipleri.INSAN_KAYNAKLARI, id, adSoyad, iseGirisTarihi, maas, departman);
		this.iseAlinanCalisanAdeti = iseAlinanCalisanAdeti;
		this.duzenlenenToplantilar = duzenlenenToplantilar;
	}

	public InsanKaynaklari() {
		super(EPersonelTipleri.INSAN_KAYNAKLARI);
		System.out.println("Lütfen Adınızı ve soyadınızı girin");
		setAdSoyad(sc.nextLine());
		System.out.println("Lütfen personel Id'sini girin");
		setId(sc.nextLine());
		setIseGirisTarihi(LocalDate.now());
		System.out.println("Lütfen personel maaşını girin.");
		setMaas(sc.nextInt());
		sc.nextLine();
	}


	public int getIseAlinanCalisanAdeti() {
		return iseAlinanCalisanAdeti;
	}

	public void setIseAlinanCalisanAdeti(int iseAlinanCalisanAdeti) {
		this.iseAlinanCalisanAdeti = iseAlinanCalisanAdeti;
	}

	public int getDuzenlenenToplantilar() {
		return duzenlenenToplantilar;
	}

	public void setDuzenlenenToplantilar(int duzenlenenToplantilar) {
		this.duzenlenenToplantilar = duzenlenenToplantilar;
	}

	@Override
	public String toString() {
		return "InsanKaynaklari [iseAlinanCalisanAdeti=" + iseAlinanCalisanAdeti + ", duzenlenenToplantilar="
				+ duzenlenenToplantilar + super.toString() + "]";
	}

}
