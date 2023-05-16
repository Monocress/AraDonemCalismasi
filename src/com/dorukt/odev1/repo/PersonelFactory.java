package com.dorukt.odev1.repo;

import com.dorukt.odev1.entities.Buro;
import com.dorukt.odev1.entities.EPersonelTipleri;
import com.dorukt.odev1.entities.GenelMudur;
import com.dorukt.odev1.entities.Hizmetli;
import com.dorukt.odev1.entities.InsanKaynaklari;
import com.dorukt.odev1.entities.Mudur;
import com.dorukt.odev1.entities.Muhasebe;
import com.dorukt.odev1.entities.Personel;
import com.dorukt.odev1.entities.Teknik;

public class PersonelFactory {

	public static Personel build(EPersonelTipleri tip) {
		switch (tip) {
		case HIZMETLI:
			return new Hizmetli();

		case BURO_PERSONELI:
			return new Buro();

		case INSAN_KAYNAKLARI:
			return new InsanKaynaklari();

		case TEKNIK_PERSONEL:
			return new Teknik();

		case MUHASEBECI:
			return new Muhasebe();

		case MUDUR:
			return new Mudur();

		case GENEL_MUDUR:
			return new GenelMudur();

		default:
			return new Hizmetli();
		}

	}

}
