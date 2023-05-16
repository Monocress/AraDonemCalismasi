package com.dorukt.odev1.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Buro extends Personel {


	private int hazirlananEvrakAdedi;
	private List<String> musteriler;


	public Buro(String id, String adSoyad, LocalDate iseGirisTarihi, int maas,
			Departman departman, int hazirlananEvrakAdedi, List<String> musteriler) {
		super(EPersonelTipleri.BURO_PERSONELI, id, adSoyad, iseGirisTarihi, maas, departman);
		this.musteriler = musteriler;
		this.hazirlananEvrakAdedi = hazirlananEvrakAdedi;
	}


	public Buro() {
		super(EPersonelTipleri.BURO_PERSONELI);
		System.out.println("Lütfen Adınızı ve soyadınızı girin");
		setAdSoyad(sc.nextLine());
		System.out.println("Lütfen personel Id'sini girin");
		setId(sc.nextLine());
		setIseGirisTarihi(LocalDate.now());
		System.out.println("Lütfen personel maaşını girin.");
		setMaas(sc.nextInt());
		sc.nextLine();
		musteriler = new ArrayList<>();
	}

	public int getHazirlananEvrakAdedi() {
		return hazirlananEvrakAdedi;
	}

	public void setHazirlananEvrakAdedi(int hazirlananEvrakAdedi) {
		this.hazirlananEvrakAdedi = hazirlananEvrakAdedi;
	}

	public List<String> getMusteriler() {
		return musteriler;
	}

	public void setMusteriler(List<String> musteriler) {
		this.musteriler = musteriler;
	}

	@Override
	public String toString() {
		return "Buro [hazirlananEvrakAdedi=" + hazirlananEvrakAdedi + ", musteriler=" + musteriler + super.toString()
				+ "]";
	}

}
