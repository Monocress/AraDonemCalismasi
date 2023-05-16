package com.dorukt.odev1.entities;

import java.time.LocalDate;

public class Muhasebe extends Personel {

	private int hazirlananGirdiCiktiRaporuSayisi;

	public Muhasebe(String id, String adSoyad, LocalDate iseGirisTarihi, int maas, Departman departman,
			int hazirlananGirdiCiktiRaporuSayisi) {
		super(EPersonelTipleri.MUHASEBECI, id, adSoyad, iseGirisTarihi, maas, departman);
		this.hazirlananGirdiCiktiRaporuSayisi = hazirlananGirdiCiktiRaporuSayisi;
	}

	public Muhasebe() {
		super(EPersonelTipleri.MUHASEBECI);
		System.out.println("Lütfen Adınızı ve soyadınızı girin");
		setAdSoyad(sc.nextLine());
		System.out.println("Lütfen personel Id'sini girin");
		setId(sc.nextLine());
		setIseGirisTarihi(LocalDate.now());
		System.out.println("Lütfen personel maaşını girin.");
		setMaas(sc.nextInt());
		sc.nextLine();
	}

	public int getHazirlananGirdiCiktiRaporuSayisi() {
		return hazirlananGirdiCiktiRaporuSayisi;
	}

	public void setHazirlananGirdiCiktiRaporuSayisi(int hazirlananGirdiCiktiRaporuSayisi) {
		this.hazirlananGirdiCiktiRaporuSayisi = hazirlananGirdiCiktiRaporuSayisi;
	}

	@Override
	public String toString() {
		return "Muhasebe [hazirlananGirdiCiktiRaporuSayisi=" + hazirlananGirdiCiktiRaporuSayisi
				+ super.toString() + "]";
	}

}
