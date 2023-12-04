package br.edu.ifpr.irati.ads.controlehoras.service;

import br.edu.ifpr.irati.ads.controlehoras.controle.ControleCRUD;
import br.edu.ifpr.irati.ads.controlehoras.dao.Dao;
import br.edu.ifpr.irati.ads.controlehoras.dao.GenericDAO;
import br.edu.ifpr.irati.ads.controlehoras.exception.PersistenceException;
import br.edu.ifpr.irati.ads.controlehoras.modelo.Aluno;
import br.edu.ifpr.irati.ads.controlehoras.modelo.Item;
import br.edu.ifpr.irati.ads.controlehoras.modelo.ItemAluno;
import br.edu.ifpr.irati.ads.controlehoras.util.HibernateUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.hibernate.Session;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ItemService implements Service{

    private ControleCRUD<Item> itemCRUD = new ControleCRUD<>(Item.class);

    private ControleCRUD<ItemAluno> itemAlunoCRUD = new ControleCRUD<>(ItemAluno.class);

    private ControleCRUD<Aluno> alunoCRUD = new ControleCRUD<>(Aluno.class);

    @Override
    public void listar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession();
            Aluno alunoreq = (Aluno) session.getAttribute("alunoAtual");
            Aluno aluno = alunoCRUD.buscarPorId(alunoreq.getId());
            session.setAttribute("alunoAtual", aluno);
            List<ItemAluno> listaItemAlunos = aluno.getListaItemAluno();

            if (aluno == null) {
                resp.sendRedirect("/");
            } else if (listaItemAlunos.isEmpty()) {
                resp.sendRedirect("/item/prepararCadastro");
            } else {
                session.setAttribute("listaItems", null);
                session.setAttribute("listaItemsAluno", listaItemAlunos);
                req.getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
            }
        } catch (PersistenceException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void prepararCadastro(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession();
            Aluno aluno = (Aluno) session.getAttribute("alunoAtual");
            List<Item> items = itemCRUD.buscarTodos();
            req.getSession().setAttribute("alunoAtual", alunoCRUD.buscarPorId(aluno.getId()));
            req.getSession().setAttribute("listaItemsAluno", null);
            req.getSession().setAttribute("listaItems", items);
            req.getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void salvar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int idItem = Integer.parseInt(req.getParameter("itemId"));
            double horas = Double.parseDouble(req.getParameter("horas"));
            int pagina = Integer.parseInt(req.getParameter("pagina"));
            int idAluno = Integer.parseInt(req.getParameter("alunoId"));

            Aluno aluno = alunoCRUD.buscarPorId(idAluno);
            Item item = itemCRUD.buscarPorId(idItem);
            ItemAluno itemAluno = new ItemAluno(pagina, horas, item);
            aluno.adicionarListaItemAluno(itemAluno);

            alunoCRUD.salvar(aluno);
        }  catch (NumberFormatException | PersistenceException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/item/listar");
    }

    @Override
    public void excluir(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            System.out.println("\n \n \n id" + id);


            ItemAluno itemAluno = itemAlunoCRUD.buscarPorId(id);
            itemAlunoCRUD.excluir(itemAluno);
        }catch (NumberFormatException | PersistenceException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/item/listar");
    }
}
