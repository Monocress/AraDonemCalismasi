package com.dorukt.odev1.entities;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Departman {
	
	private String departmanAdi;
	private Set<Personel> calisanlar;
	private Mudur sorumluMudur;



	public Departman() {
		super();
		Scanner sc = new Scanner(System.in);
		System.out.println("Departman adını girin.");
		this.departmanAdi = sc.nextLine();
		this.calisanlar = new HashSet<>();
	}

	public Departman(String departmanAdi) {
		super();
		this.departmanAdi = departmanAdi;
		this.calisanlar = new HashSet<>();
	}

	public String getDepartmanAdi() {
		return departmanAdi;
	}

	public void setDepartmanAdi(String departmanAdi) {
		this.departmanAdi = departmanAdi;
	}

	public Set<Personel> getCalisanlar() {
		return calisanlar;
	}

	public void setCalisanlar(Set<Personel> calisanlar) {
		this.calisanlar = calisanlar;
	}

	public Mudur getSorumluMudur() {
		return sorumluMudur;
	}

	public void setSorumluMudur(Mudur sorumluMudur) {
		this.sorumluMudur = sorumluMudur;
	}

	@Override
	public String toString() {
		return "Departman [departmanAdi=" + departmanAdi + ", sorumluMudur=" + sorumluMudur + "]";
	}

}
