package com.dorukt.odev1.entities;

import java.time.LocalDate;
import java.util.Scanner;

public class Personel {
	Scanner sc = new Scanner(System.in);
	private EPersonelTipleri tip;
	private String Id;
	private String adSoyad;
	private LocalDate iseGirisTarihi;
	private int maas;
	private Departman departman;

	public Personel(EPersonelTipleri tip) {
		this.tip = tip;
	}


	public Personel(EPersonelTipleri tip, String id, String adSoyad, LocalDate iseGirisTarihi, int maas,
			Departman departman) {
		super();
		this.tip = tip;
		Id = id;
		this.adSoyad = adSoyad;
		this.iseGirisTarihi = iseGirisTarihi;
		this.maas = maas;
		this.departman = departman;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getAdSoyad() {
		return adSoyad;
	}

	public void setAdSoyad(String adSoyad) {
		this.adSoyad = adSoyad;
	}

	public LocalDate getIseGirisTarihi() {
		return iseGirisTarihi;
	}

	public void setIseGirisTarihi(LocalDate iseGirisTarihi) {
		this.iseGirisTarihi = iseGirisTarihi;
	}

	public int getMaas() {
		return maas;
	}

	public void setMaas(int maas) {
		this.maas = maas;
	}

	public Departman getDepartman() {
		return departman;
	}

	public void setDepartman(Departman departman) {
		this.departman = departman;
	}

	public EPersonelTipleri getTip() {
		return tip;
	}

	public void setTip(EPersonelTipleri tip) {
		this.tip = tip;
	}

	@Override
	public String toString() {
		if (departman == null) {
			return "tip=" + tip + ", Id=" + Id + ", adSoyad=" + adSoyad + ", iseGirisTarihi=" + iseGirisTarihi
					+ ", maas=" + maas + ", departman=" + departman;
		} else {
			return "tip=" + tip + ", Id=" + Id + ", adSoyad=" + adSoyad + ", iseGirisTarihi=" + iseGirisTarihi
					+ ", maas=" + maas + ", departman=" + departman.getDepartmanAdi();
		}

	}

}
