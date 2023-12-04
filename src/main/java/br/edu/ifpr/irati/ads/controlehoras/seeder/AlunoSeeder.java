package br.edu.ifpr.irati.ads.controlehoras.seeder;

import br.edu.ifpr.irati.ads.controlehoras.dao.Dao;
import br.edu.ifpr.irati.ads.controlehoras.dao.GenericDAO;
import br.edu.ifpr.irati.ads.controlehoras.exception.PersistenceException;
import br.edu.ifpr.irati.ads.controlehoras.modelo.Aluno;
import br.edu.ifpr.irati.ads.controlehoras.util.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;

public class AlunoSeeder {
    public static void main(String[] args) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();

            Dao<Aluno> alunoDAO = new GenericDAO<>(Aluno.class, session);

            Aluno aluno1 = new Aluno(0, "Zé da vila", new ArrayList<>());
            Aluno aluno2 = new Aluno(0, "João da vila", new ArrayList<>());
            Aluno aluno3 = new Aluno(0, "Maria do Bairro", new ArrayList<>());

            alunoDAO.salvar(aluno1);
            alunoDAO.salvar(aluno2);
            alunoDAO.salvar(aluno3);

            System.out.println("fim");
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
    }
}
