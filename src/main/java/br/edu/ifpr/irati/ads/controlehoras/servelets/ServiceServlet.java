package br.edu.ifpr.irati.ads.controlehoras.servelets;

import br.edu.ifpr.irati.ads.controlehoras.service.Service;
import br.edu.ifpr.irati.ads.controlehoras.service.ServiceFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(name = "serviceservlet", urlPatterns={
		"/item/listar",
		"/item/prepararCadastro",
		"/item/salvar",
		"/item/excluir",
})
public class ServiceServlet extends HttpServlet{

	private static final long serialVersionUID = 6778943264241827069L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String path = req.getServletPath();		
		
		if (path.split("/").length == 3) {
			String entity = path.split("/")[1];
			Service service = ServiceFactory.getService(entity);
			String method = path.split("/")[2];
			switch (method) {
				case "listar":
					service.listar(req, resp);
					break;
				case "prepararCadastro":
					service.prepararCadastro(req, resp);
					break;
				case "salvar":
					service.salvar(req, resp);
					break;
				case "excluir":
					service.excluir(req, resp);
					break;
				default:
					break;
			}
		}
	}
}
