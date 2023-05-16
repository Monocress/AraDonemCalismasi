package com.dorukt.odev1.repo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import com.dorukt.odev1.entities.Buro;
import com.dorukt.odev1.entities.Departman;
import com.dorukt.odev1.entities.EPersonelTipleri;
import com.dorukt.odev1.entities.GenelMudur;
import com.dorukt.odev1.entities.Hizmetli;
import com.dorukt.odev1.entities.IModifiable;
import com.dorukt.odev1.entities.InsanKaynaklari;
import com.dorukt.odev1.entities.Mudur;
import com.dorukt.odev1.entities.Muhasebe;
import com.dorukt.odev1.entities.Personel;
import com.dorukt.odev1.entities.Teknik;

public class Database implements IModifiable {
	Scanner sc = new Scanner(System.in);

	private List<Personel> personeller = new ArrayList<>();
	private List<Departman> departmanlar = new ArrayList<>();

	public void menu() {
		defaultlariEkle();
		int secim = 0;
		do {
			Menu.anaMenu();
			System.out.println("Lütfen seçiminizi girin.");
			secim = kullaniciGirdisi();
			switch (secim) {
			case 1:
				personelveDepartmanIslemleriMenu();
				break;
			case 2:
				muhasebeIslemleri();
				break;
			case 3:
				raporlamaIslemleri();
				break;
			case 0:
				System.out.println("Yine bekleriz.");
				break;

			default:
				System.out.println("Hatalı giriş yaptınız.");
				break;
			}
		} while (secim != 0);

	}

	/**
	 * Raporlama işlemlerini içeren ve çalışmasını sağlayan switch case'li metod
	 */
	private void raporlamaIslemleri() {
		int secim = 0;
		do {
			Menu.reporlamaMenu();
			
			System.out.println("Lütfen seçiminizi yapın");
			secim = kullaniciGirdisi();
			switch (secim) {
			case 1: // Departman personel listesi
				departmanlar.stream().forEach(x -> {
					System.out.println("[#####" + x.getDepartmanAdi() + "#####]");
					x.getCalisanlar().stream().forEach(z -> System.out.println(z.getAdSoyad()));
				});
				sc.nextLine();
				break;
			case 2: // En çok personel bulunan departman.
				int boyut = 0;
				String isim = "";
				for (Departman departman : departmanlar) {
					if (boyut < departman.getCalisanlar().size()) {
						boyut = departman.getCalisanlar().size();
						isim = departman.getDepartmanAdi();
					}
				}
				System.out.println(isim + " adlı departman en çok personele sahiptir ve çalışan sayısı: " + boyut);
				System.out.println();
				sc.nextLine();
				break;
			case 3: // Departmanlara göre maaş ortalamaları
				double ortalama = 0;
				for (Departman departman : departmanlar) {
					ortalama = 0;
					System.out.print(departman.getDepartmanAdi() + " maaş ortalaması : ");
					for (Personel personel : departman.getCalisanlar()) {
						ortalama += (double) personel.getMaas();
					}
					System.out.println(ortalama / departman.getCalisanlar().size());
				}
				System.out.println();
				break;
			case 4: // Müdürlerin sorumlu olduğu departman listesi
				for (Personel personel : personeller) {
					if (personel.getDepartman() != null) {
						if (personel.getTip().equals(EPersonelTipleri.MUDUR)) {
							System.out.println(personel.getAdSoyad() + " isimli müdürün sorumlu olduğu departman: "
									+ personel.getDepartman().getDepartmanAdi());
						}
					} else {
						System.out.println(personel.getAdSoyad() + " isimli müdürün sorumlu olduğu departman: Yok");
					}
				}
				System.out.println();
				break;
			case 5: // Kayıt tarihlerine göre personellerin sıralı listesi
				personeller.stream().sorted(Comparator.comparing(Personel::getIseGirisTarihi))
						.forEach(x -> System.out.println(
								x.getAdSoyad() + " adlı personelin işe başlama tarihi: " + x.getIseGirisTarihi()));
				System.out.println();
				break;
			case 6: // Aynı gün içinde işe başlayan personellerin listesi
			Map<LocalDate, List<Personel>> iseGirisGrup = (Map<LocalDate, List<Personel>>) personeller.stream().collect(Collectors.groupingBy(Personel::getIseGirisTarihi));
			for (Map.Entry<LocalDate, List<Personel>> entry : iseGirisGrup.entrySet()) {
				LocalDate key = entry.getKey();
				List<Personel> val = entry.getValue();
				System.out.println("[" + key + "] tarihinde işe başlayanlar: ");
				int sayac = 0;
				for (Personel personel : val) {
					System.out.println(++sayac + " - " + personel.getAdSoyad());
				}
				System.out.println();
			}

				break;
			case 0:
				System.out.println("Devam etmek için enter'a basın. . .");
				sc.nextLine();
				break;
			default:
				System.out.println("Hatalı giriş yaptınız.");
				break;
			}

		} while (secim!=0);
	}
	/**
	 * Databasedeki para ile ilgili tüm işlemlerin switch case ile yapıldığı metod
	 * 
	 */
	public void muhasebeIslemleri() {
		int secim = 0;
		do {
			Menu.muhasebeIslemleriMenu();
			System.out.println("Lütfen seçiminizi girin.");
			secim = kullaniciGirdisi();
			switch (secim) {
			case 1: // Maaş ataması yap
				listeIcerikleriniYazdir(personeller);
				System.out.println("Lütfen maaş düzenlemesi yapmak istediğiniz personelin sıra no'sunu girin.");
				int index = kullaniciGirdisi() - 1;
				System.out.println("Personelin yeni maaşını girin.");
				personeller.get(index).setMaas(kullaniciGirdisi());
				System.out.println("Maaş güncellemesi başarılı.");
				sc.nextLine();
				break;
			case 2: // Personellerin maaşlarını listele
				System.out.println("Çalışan maaş listesi: ");
				AtomicInteger aSayac = new AtomicInteger(1); // Stream ile yazdırma sırasında sıra noyu gösterir.
				personeller.stream().forEach(
						x -> System.out.println(aSayac.getAndAdd(1) + " - " + x.getAdSoyad() + ": " + x.getMaas()));
				System.out.println(
						"Ödenecek toplam maaş tutarı: " + personeller.stream().mapToInt(x -> x.getMaas()).sum() + "TL");
				sc.nextLine();
				break;
			case 0:
				System.out.println("Devam etmek için enter'a basın. . .");
				sc.nextLine();
				break;

			default:
				System.out.println("Hatalı giriş yaptınız. Ana menüye dönülüyor.");
				break;
			}
		} while (secim != 0);
	}
	/**
	 * Kullanıcıdan rakam girişi beklendiğinde kullanılan metod
	 * 
	 * @return Kullanıcının girdiği değer.
	 */
	public int kullaniciGirdisi() {
		int secim;
		secim = sc.nextInt();
		sc.nextLine();
		return secim;
	}


	/**
	 * Database'in Personel ve departman ile ilgili tüm işlemlerin toplandığı switch
	 * case içeren metod
	 * 
	 */
	private void personelveDepartmanIslemleriMenu() {
		int secim = 0;
		do {
			Menu.personelMenu();
			System.out.println("Lütfen seçiminizi girin.");
			secim = kullaniciGirdisi();
			switch (secim) {
			case 1: // Personel ekleme
				Personel temp = PersonelIslemleri.personelOlustur();
				if (temp == null)
					System.out.println("Işlem iptal edildi.");
				else {
					save(temp, personeller);
				}
				break;
			case 2: // Personel Sil
				delete(personeller);
				System.out.println("Devam etmek için enter'a basın. . .");
				sc.nextLine();
				break;
			case 3: // Personel listesi
				int sayac = 0;
				for (Personel personel : personeller) {
					if (personel.getDepartman() != null) {
					System.out.println(++sayac + " - " + personel.getTip().getInfo() + " - " + personel.getAdSoyad()
								+ " - " + "Çalıştığı departman: " + personel.getDepartman().getDepartmanAdi());
					} else {
						System.out.println(++sayac + " - " + personel.getTip().getInfo() + " - " + personel.getAdSoyad()
								+ " - " + "Çalıştığı departman: Yok");
					}
				}
				System.out.println("Devam etmek için enter'a basın. . .");
				sc.nextLine();
				break;

			case 4: // Personel düzenleme
				update(personeller);
				break;
			case 5: // Departman ekle güncelle
				Menu.departmanMenu();
				System.out.println("Lütfen seçiminizi girin.");
				String altSecim = kullaniciGirdisi() + "";
				if (altSecim.equals("1"))
				DepartmanIslemleri.departmanEkle(departmanlar);
				else if (altSecim.equals("2")) {
					DepartmanIslemleri.departmanlarıListele(departmanlar);
					System.out.println("Hangi departmanı güncellemek istiyorsunuz?");
					DepartmanIslemleri.departmanUpdateMenu(departmanlar.get(kullaniciGirdisi() - 1), personeller);
				}
				break;
			case 6: // Departman Listesi
				System.out.println("#######################");
				AtomicInteger sira = new AtomicInteger(1);
				departmanlar.forEach(x -> System.out.println(sira.getAndAdd(1) + " - " + x.getDepartmanAdi()));
				System.out.println("#######################");
				sc.nextLine();
				break;
			case 7:
				System.out.println("Devam etmek için enter'a basın. . .");
				sc.nextLine();
				break;
			case 8: // En başa dönmeden çıkmayı sağlamak istedim.
				System.out.println("Yine bekleriz.");
				System.exit(0);
				break;
			default:
				System.out.println("Hatalı seçim yaptınız.");
				break;
			}
		} while (secim != 7);
	}
	/**
	 * Default personelleri oluşturup denemelerde kullanmak için oluşturulmuş bir
	 * metod
	 */
	public void defaultlariEkle() {

		Departman departman1 = new Departman("Yönetim");
		Departman departman2 = new Departman("Halkla ilişkiler");
		Departman departman3 = new Departman("Teknik");
		Departman departman4 = new Departman("Temizlik");
		Departman departman5 = new Departman("Insan kaynakları");
		Departman departman6 = new Departman("Muhasebe");
		departmanlar.add(departman6);
		departmanlar.add(departman5);
		departmanlar.add(departman4);
		departmanlar.add(departman3);
		departmanlar.add(departman2);
		departmanlar.add(departman1);

		Buro b1 = new Buro("8", "Cem Akıncı", LocalDate.of(2022, 03, 04), 14000, departman2, 23,
				new ArrayList<>(List.of("Cem", "Hakan", "Ebru")));
		departman2.getCalisanlar().add(b1);

		Buro b2 = new Buro("7", "Fuat Tepe", LocalDate.of(2021, 03, 14), 13000, departman2, 33,
				new ArrayList<>(List.of("Kemal", "Remzi", "Hikmet")));
		departman2.getCalisanlar().add(b2);
		Buro b3 = new Buro("17", "Cengiz Şen", LocalDate.of(2017, 11, 25), 20000, departman2, 41,
				new ArrayList<>(List.of("İbrahim", "Ayşe", "Merve")));
		departman2.getCalisanlar().add(b3);

		Mudur mudur1 = new Mudur("2", "Ekrem Deva", LocalDate.of(2000, 10, 12), 30000, departman2, 23);
		departman2.getCalisanlar().add(mudur1);
		departman2.setSorumluMudur(mudur1);

		Mudur mudur2 = new Mudur("3", "Ebru Korkmaz", LocalDate.of(2020, 05, 06), 15000, departman3, 5);
		departman3.getCalisanlar().add(mudur2);
		departman3.setSorumluMudur(mudur2);

		Mudur mudur3 = new Mudur("4", "Yıldız Tok", LocalDate.of(2021, 07, 12), 13500, departman4, 1);
		departman4.getCalisanlar().add(mudur3);
		departman4.setSorumluMudur(mudur3);

		Mudur mudur4 = new Mudur("5", "Ege Koru", LocalDate.of(2018, 12, 15), 18000, departman5, 10);
		departman5.getCalisanlar().add(mudur4);
		departman5.setSorumluMudur(mudur4);

		Mudur mudur5 = new Mudur("6", "Emre Sarı", LocalDate.of(2010, 10, 12), 22000, departman6, 19);
		departman6.getCalisanlar().add(mudur5);
		departman6.setSorumluMudur(mudur5);

		ArrayList<Mudur> mudurList = new ArrayList<>(List.of(mudur1, mudur2, mudur3, mudur4, mudur5));
		GenelMudur gm = new GenelMudur("1", "Kemal Kazanır", LocalDate.of(1999, 01, 22), 40000, departman1, mudurList,
				300);

		departman1.getCalisanlar().add(gm);
		Hizmetli h1 = new Hizmetli("11", "Gürkan Ekler", LocalDate.of(2022, 01, 22), 10000, departman4, "Yönetim katı");
		Hizmetli h2 = new Hizmetli("12", "Leyla Dertsiz", LocalDate.of(2020, 05, 07), 12000, departman4, "Giriş kat");

		departman4.getCalisanlar().add(h2);
		departman4.getCalisanlar().add(h1);

		InsanKaynaklari ik1 = new InsanKaynaklari("9", "Burcu Tarif", LocalDate.of(2022, 11, 07), 19000, departman5, 4,
				4);
		InsanKaynaklari ik2 = new InsanKaynaklari("10", "Aleyna Alkan", LocalDate.of(2021, 07, 12), 20000, departman5,
				9, 4);

		departman5.getCalisanlar().add(ik2);
		departman5.getCalisanlar().add(ik1);

		Muhasebe muha1 = new Muhasebe("13", "Deniz Orkut", LocalDate.of(2019, 10, 11), 25000, departman6, 120);
		Muhasebe muha2 = new Muhasebe("14", "Ali Varmaz", LocalDate.of(2020, 02, 24), 22000, departman6, 80);
		departman6.getCalisanlar().add(muha2);
		departman6.getCalisanlar().add(muha1);

		Teknik tek1 = new Teknik("15", "Ahmet Becerir", LocalDate.of(2015, 07, 22), 28000, departman3, 200);
		Teknik tek2 = new Teknik("16", "Mehmet Günaydın", LocalDate.of(2022, 04, 06), 11000, departman3, 14);

		departman3.getCalisanlar().add(tek2);
		departman3.getCalisanlar().add(tek1);
		personeller.add(tek1);
		personeller.add(tek2);
		personeller.add(muha1);
		personeller.add(muha2);
		personeller.add(mudur1);
		personeller.add(mudur2);
		personeller.add(mudur3);
		personeller.add(mudur4);
		personeller.add(mudur5);
		personeller.add(b1);
		personeller.add(b2);
		personeller.add(gm);
		personeller.add(h1);
		personeller.add(h2);
		personeller.add(ik1);
		personeller.add(ik2);
		personeller.add(b3);

	}


	@Override
	public void save(Personel personel, List<Personel> liste) {
		boolean varMi = false;
		for (Personel personel2 : liste) {
			if (personel.getId().equals(personel2.getId())) {
				varMi = true;
				break;
			}
		}
		if (varMi)
			System.out.println("Bu ID zaten kullanılıyor. Kullanıcı sisteme eklenemedi.");
		else {
			liste.add(personel);
			System.out.println("Kullanıcı database'e kaydedildi.");
		}

	}

	/**
	 * Databaseteki tüm Personeller ile ilgili güncelleme işlemlerinin yürütüldüğü
	 * metod.
	 */
	@Override
	public void update(List<Personel> liste) {
		listeIcerikleriniYazdir(personeller);
		System.out.println("Güncellenecek personelin sıra no'sunu secin");
		Personel temp = personeller.get(sc.nextInt() - 1);
		sc.nextLine();
		int secim = 0;
		do {
			System.out.println("Düzenlenen kullanıcı: " + temp + "\n");
			Menu.updateMenu();
			System.out.println("Lütfen seçiminizi girin.");
			secim = kullaniciGirdisi();
			switch (secim) {

			case 1: // Ad-Soyad Düzenle
				System.out.println("Lütfen " + temp.getAdSoyad() + " için yeni ismi girin");
				temp.setAdSoyad(sc.nextLine());
				System.out.println("İsim güncellendi.");
				break;
			case 2: // İşe giriş tarihi düzenle.
				System.out.println("Lütfen düzeltilecek işe giriş tarihini YYYY-MM-GG olacak şekilde girin");
				String tarih = sc.nextLine();
				temp.setIseGirisTarihi(LocalDate.parse(tarih));
				System.out.println("İşe giriş tarihi güncellendi.");
				break;
			case 3: // Departman değiştir.
				Departman gecici;
				int sayac = 0;
				for (Departman dep : departmanlar) {
					System.out.println(++sayac + " - " + dep.getDepartmanAdi());
				}
				System.out.println("Personele eklenmesini istediğiniz departmanın sıra numarasını girin.");
				gecici = departmanlar.get(sc.nextInt() - 1);
				if (temp.getDepartman() != null)
					temp.getDepartman().getCalisanlar().remove(temp);
				temp.setDepartman(gecici);
				gecici.getCalisanlar().add(temp);
				System.out.println("Departman güncellendi.");

				break;
			case 4: // Özelleştirilmiş personel özellikleri
				if (temp.getTip().equals(EPersonelTipleri.BURO_PERSONELI))
					updateBuro((Buro) temp);
				else if (temp.getTip().equals(EPersonelTipleri.GENEL_MUDUR)) {
					updateGenelMudur((GenelMudur) temp);
				}
				else if (temp.getTip().equals(EPersonelTipleri.HIZMETLI)) {
					updateHizmetli((Hizmetli) temp);
				} else if (temp.getTip().equals(EPersonelTipleri.INSAN_KAYNAKLARI)) {
					updateInsanKaynaklari((InsanKaynaklari) temp);
				} else if (temp.getTip().equals(EPersonelTipleri.MUDUR)) {
					updateMudur((Mudur) temp);
				} else if (temp.getTip().equals(EPersonelTipleri.MUHASEBECI)) {
					updateMuhasebe((Muhasebe) temp);
				} else if (temp.getTip().equals(EPersonelTipleri.TEKNIK_PERSONEL)) {
					updateTeknik((Teknik) temp);
				}
				break;
			case 5:// personeli sil.
				PersonelIslemleri.deleteById(liste, temp.getId());
				if (temp.getDepartman() != null) {
				if (temp.getTip().equals(EPersonelTipleri.MUDUR)) {
						temp.getDepartman().setSorumluMudur(null);
					}
					temp.getDepartman().getCalisanlar().remove(temp);
				}
				System.out.println("Kullanıcı silindi.");
				secim = 0;
				break;
			case 0:
				System.out.println("Devam etmek için enter'a basın. . .");
				sc.nextLine();
				break;
			default:
				System.out.println("Hatalı seçim");
				break;
			}
		} while (secim != 0);

	}

	@Override
	public void delete(List<Personel> liste) {
		listeIcerikleriniYazdir(liste);
		System.out.println("Lütfen silinecek personelin ID'sini girin.");
		Personel temp = PersonelIslemleri.findById(liste, sc.nextLine());
		if (temp.getDepartman() != null)
		temp.getDepartman().getCalisanlar().remove(temp);
		if (temp.getTip().equals(EPersonelTipleri.MUDUR))
			temp.getDepartman().setSorumluMudur(null);
		liste.remove(temp);
		System.out.println("Silme işlemi tamamlandı.");
	}
	@Override
	public void listeIcerikleriniYazdir(List<Personel> liste) {
		int sayac = 0;
		for (Personel personel : liste) {
			System.out.println(++sayac + " - " + personel.getAdSoyad() + " - ID: " + personel.getId() + " Maaş: "
					+ personel.getMaas());
		}

	}

	/**
	 * Sadece Teknik personel sınıfı özelliklerine ulaşıp modifikasyona olanak
	 * sağlayan metod
	 * 
	 * @param teknik özellikleri değiştirilecek olan nesne
	 */
	public void updateTeknik(Teknik teknik) {
		System.out.println("Teknik personel işlemleri");
		System.out.println("1 - Tamir edilen araç miktarı");
		System.out.println("2 - Çıkış");
		int secim = sc.nextInt();
		switch (secim) {
		case 1:
			System.out.println("Tamir edilen toplam araç: " + teknik.getTamirEdilenAracMiktari());
			System.out.println("Lütfen yeni miktarı girin.");
			teknik.setTamirEdilenAracMiktari(sc.nextInt());
			System.out.println("Güncelleme tamamlandı.");
			sc.nextLine();
			break;
		case 2:
			System.out.println("Önceki menüye dönülüyor.");
			sc.nextLine();
			break;
		default:
			System.out.println("Hatalı giriş");
			break;
		}
	}

	/**
	 * Sadece Muhasebe personeline ait özelliklere ulaşılmasını ve modifikasyonunu
	 * sağlayan metod.
	 * 
	 * @param muhasebe özellikleri değiştirilecek olan nesne
	 */
	public void updateMuhasebe(Muhasebe muhasebe) {
		System.out.println("Muhasebe personeli işlemleri");
		System.out.println("1 - Hazırlanan rapor sayısı");
		System.out.println("2 - Çıkış");
		int secim = sc.nextInt();
		switch (secim) {
		case 1:
			System.out.println("Hazırlanan rapor sayısı: " + muhasebe.getHazirlananGirdiCiktiRaporuSayisi());
			System.out.println("Lütfen yeni rapor miktarını girin.");
			muhasebe.setHazirlananGirdiCiktiRaporuSayisi(sc.nextInt());
			sc.nextLine();
			System.out.println("Rapor sayısı güncellendi.");
			break;
		case 2:
			System.out.println("Önceki menüye dönülüyor.");
			sc.nextLine();
			break;

		default:
			System.out.println("Hatalı giriş");
			break;
		}
	}

	/**
	 * Sadece Mudur personeline ait özelliklere ulaşılmasını ve modifikasyonunu
	 * sağlayan metod.
	 * 
	 * @param mudur özellikleri değiştirilecek olan nesne
	 */
	public void updateMudur(Mudur mudur) {
		System.out.println("Müdür işlemleri");
		System.out.println("1 - Düzenlenen toplantı sayısı");
		System.out.println("2 - Çıkış");
		int secim = sc.nextInt();
		switch (secim) {
		case 1:
			System.out.println(mudur.getAdSoyad() + " toplamda " + mudur.getDuzenlenenToplantiSayisi()
					+ " adet toplantı düzenlemiştir.");
			System.out.println("Güncellenecek toplantı değerini girin:");
			mudur.setDuzenlenenToplantiSayisi(sc.nextInt());
			sc.nextLine();
			break;
		case 2:
			System.out.println("Önceki menüye dönülüyor.");
			sc.nextLine();
			break;
		default:
			System.out.println("Hatalı giriş");
			break;
		}
	}

	/**
	 * Sadece Insan kaynakları personeline ait özelliklere ulaşılmasını ve
	 * modifikasyonunu sağlayan metod.
	 * 
	 * @param ik özellikleri değiştirilecek olan nesne
	 */
	public void updateInsanKaynaklari(InsanKaynaklari ik) {
		System.out.println("Insan kaynakları personeli işlemleri");
		System.out.println("1 - İşe alınan personel sayısını güncelle");
		System.out.println("2 - Düzenlenen toplantı sayısını güncelle");
		System.out.println("3 - Çıkış");
		int secim = sc.nextInt();
		switch (secim) {
		case 1:
			System.out
					.println(ik.getAdSoyad() + " toplamda " + ik.getIseAlinanCalisanAdeti() + " kişiyi işe almıştır.");
			System.out.println("Lütfen güncellenecek yeni değeri girin: ");
			ik.setIseAlinanCalisanAdeti(sc.nextInt());
			sc.nextLine();
			System.out.println("İşe alınan personel adedi güncellendi.");
			break;
		case 2:
			System.out.println("Lütfen yeni toplantı miktarını girin");
			ik.setDuzenlenenToplantilar(sc.nextInt());
			sc.nextLine();
			System.out.println("Toplantı miktarı güncellendi.");
			break;
		case 3:
			System.out.println("Önceki menüye dönülüyor.");
			break;

		default:
			System.out.println("Hatalı giriş");
			break;
		}
	}

	/**
	 * Sadece Hizmetli personeline ait özelliklere ulaşılmasını ve modifikasyonunu
	 * sağlayan metod.
	 * 
	 * @param hizmetli özellikleri değiştirilecek olan nesne
	 */
	public void updateHizmetli(Hizmetli hizmetli) {
		System.out.println("Hizmetli islemleri");
		System.out.println("1 - Hizmet bölgesini değiştir");
		System.out.println("2 - Çıkış");
		int secim = sc.nextInt();
		switch (secim) {
		case 1:
			System.out.println(hizmetli.getAdSoyad()+" için çalışma bölgesi: " +hizmetli.getGorevBolgesi());
			System.out.println("Lütfen yeni çalışma bölgesini girin.");
			hizmetli.setGorevBolgesi(sc.nextLine());
			sc.nextLine();
			System.out.println("Görev bölgesi güncellendi");
			break;
		case 2:
			System.out.println("Önceki menüye dönülüyor.");
			break;

		default:
			System.out.println("Hatalı giriş");
			break;
		}
	}

	/**
	 * Sadece Genel Müdür personeline ait özelliklere ulaşılmasını ve
	 * modifikasyonunu sağlayan metod.
	 * 
	 * @param gm özellikleri değiştirilecek olan nesne
	 */
	public void updateGenelMudur(GenelMudur gm) {
		System.out.println("Genel Müdür işlemleri");
		System.out.println("1 - Müdür ekle");
		System.out.println("2 - Düzenlenen toplantı adedi");
		System.out.println("3 - Çıkış");
		int secim = sc.nextInt();
		switch (secim) {
		case 1:
			System.out.println("Eklenecek müdürün id'sini girin");
			PersonelIslemleri.findAll(EPersonelTipleri.MUDUR, personeller);
			Mudur temp = (Mudur) PersonelIslemleri.findById(personeller, sc.nextLine());
			gm.getIlgiliMudurler().add(temp);
			sc.nextLine();
			break;
		case 2:
			System.out.println(gm.getDuzenlenenToplantiMiktari()+ "adet toplantı düzenlenmiştir.");
			sc.nextLine();
			break;
		case 3:
			System.out.println("Önceki menüye dönülüyor.");
			break;

		default:
			System.out.println("Hatalı giriş");
			break;
		}
	}

	/**
	 * Sadece Büro personeline ait özelliklere ulaşılmasını ve modifikasyonunu
	 * sağlayan metod.
	 * 
	 * @param buro özellikleri değiştirilecek olan nesne
	 */
	public void updateBuro(Buro buro) {
		System.out.println("Büro personeli işlemleri");
		System.out.println("1 - Hazırlanan evrak adedi");
		System.out.println("2 - Müşteri listesi");
		System.out.println("3 - Çıkış");
		int secim = sc.nextInt();
		switch (secim) {
		case 1:
			System.out.println(
					buro.getAdSoyad() + "toplamda " + buro.getHazirlananEvrakAdedi() + " evrak düzenlemiştir.");
			sc.nextLine();
			break;
		case 2:
			buro.getMusteriler().forEach(System.out::println);
			sc.nextLine();
			break;
		case 3:
			System.out.println("Önceki menüye dönülüyor.");
			break;

		default:
			System.out.println("Hatalı giriş");
			break;
		}
	}


}
