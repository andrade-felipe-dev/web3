package br.edu.ifpr.irati.ads.controlehoras.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface Service {

	public void listar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

	public void prepararCadastro(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

	public void salvar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

	public void excluir(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
