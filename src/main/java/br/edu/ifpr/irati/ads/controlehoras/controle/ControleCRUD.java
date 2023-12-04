package br.edu.ifpr.irati.ads.controlehoras.controle;

import br.edu.ifpr.irati.ads.controlehoras.dao.Dao;
import br.edu.ifpr.irati.ads.controlehoras.dao.GenericDAO;
import br.edu.ifpr.irati.ads.controlehoras.exception.PersistenceException;
import br.edu.ifpr.irati.ads.controlehoras.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class ControleCRUD<T> {

	private Session session;   
    
    @SuppressWarnings("rawtypes")
	private final Class classePersistente;

    @SuppressWarnings("rawtypes")
	public ControleCRUD(Class classePersistente) {
        this.classePersistente = classePersistente;
        this.session = HibernateUtil.getSessionFactory().openSession();
    }
    
    public List<T> buscarTodos() throws PersistenceException {
        Dao<T> dao = new GenericDAO<>(this.classePersistente, session);
        List<T> objetos = dao.buscarTodos();
        return objetos;
    }

    public T buscarPorId(int id) throws PersistenceException {        
        T t = null;
        Dao<T> dao = new GenericDAO<>(this.classePersistente, session);
        t = dao.buscarPorId(id);
        return t;
    }

    public void salvar(T t) throws PersistenceException {
        Dao<T> dao = new GenericDAO<>(this.classePersistente, session);                       
        dao.salvar(t);        
    }
    
    public void alterar(T t) throws PersistenceException {
        Dao<T> dao = new GenericDAO<>(this.classePersistente, session);
        dao.alterar(t);
    }

    public void excluir(T t) throws PersistenceException {
        Dao<T> dao = new GenericDAO<>(this.classePersistente, session);
        dao.excluir(t);
    }
}
