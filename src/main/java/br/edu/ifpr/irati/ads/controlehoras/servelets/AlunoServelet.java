package br.edu.ifpr.irati.ads.controlehoras.servelets;

import br.edu.ifpr.irati.ads.controlehoras.controle.ControleCRUD;
import br.edu.ifpr.irati.ads.controlehoras.exception.PersistenceException;
import br.edu.ifpr.irati.ads.controlehoras.modelo.Aluno;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@WebServlet(name = "alunoservelet", urlPatterns = {""})
public class AlunoServelet extends HttpServlet {
    ControleCRUD<Aluno> alunoCRUD = new ControleCRUD<>(Aluno.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            try {
                int id = Integer.parseInt(req.getParameter("alunoId"));
                doPost(req, resp);
            } catch (NumberFormatException e) {
                req.getSession().setAttribute("listaItems", null);
                req.getSession().setAttribute("listaItemsAluno", null);
                req.getSession().setAttribute("alunoAtual", null);

                List<Aluno> alunos = alunoCRUD.buscarTodos();
                Collections.sort(alunos);
                req.setAttribute("alunos", alunos);
                req.getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
            }
        }catch (PersistenceException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("alunoId"));
            Aluno aluno = alunoCRUD.buscarPorId(id);
            HttpSession session = req.getSession();
            session.setAttribute("alunoAtual", aluno);
            resp.sendRedirect("/item/listar");
        }catch (PersistenceException e) {
            e.printStackTrace();
        }
    }
}
