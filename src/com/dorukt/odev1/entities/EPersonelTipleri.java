package com.dorukt.odev1.entities;

public enum EPersonelTipleri {
	HIZMETLI("Hizmetli"), BURO_PERSONELI("Büro Personeli"), INSAN_KAYNAKLARI("İnsan kaynakları"),
	TEKNIK_PERSONEL("Teknik Personel"), MUHASEBECI("Muhasebe"), MUDUR("Müdür"), GENEL_MUDUR("Genel Müdür");

	String info;

	private EPersonelTipleri(String info) {
		this.info = info;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}


}
