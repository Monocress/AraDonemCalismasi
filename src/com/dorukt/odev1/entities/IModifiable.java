package com.dorukt.odev1.entities;

import java.util.List;

public interface IModifiable {

	/**
	 * Parametre olarak verilen listeye, parametre olarak verilen personeli ekler.
	 * 
	 * @param personel database listesine eklenecek personel
	 * @param liste    personelin ekleneceği database listesi
	 */
	public void save(Personel personel, List<Personel> liste);

	/**
	 * Parametre olarak verilen listenin ve içerisindeki personellerin
	 * düzenlenmesini sağlar.
	 * 
	 * @param liste Düzenlenmek istenen personelleri içeren liste.
	 */
	public void update(List<Personel> liste);

	/**
	 * Verilen liste içerisindeki personelleri siler
	 * 
	 * @param liste silinmesi istenen nesnenin içinde bulunduğu liste
	 */
	public void delete(List<Personel> liste);

	/**
	 * Listenin içerisindeki personellerin sıra no, ad soyad ve id'sinin çıktısını
	 * verecek şekilde yazdırır.
	 * 
	 * @param liste
	 */

	public void listeIcerikleriniYazdir(List<Personel> liste);

}
