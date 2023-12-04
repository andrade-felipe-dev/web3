package br.edu.ifpr.irati.ads.controlehoras.service;

public class ServiceFactory {

	public static Service getService(String nome) {
		switch (nome) {
			case "item":
				return new ItemService();
			default:
				break;
		}
		return new ItemService();
	}
}
