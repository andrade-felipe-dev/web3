package br.edu.ifpr.irati.ads.controlehoras.seeder;

import br.edu.ifpr.irati.ads.controlehoras.dao.Dao;
import br.edu.ifpr.irati.ads.controlehoras.dao.GenericDAO;
import br.edu.ifpr.irati.ads.controlehoras.exception.PersistenceException;
import br.edu.ifpr.irati.ads.controlehoras.modelo.Item;
import br.edu.ifpr.irati.ads.controlehoras.util.HibernateUtil;
import org.hibernate.Session;

public class ItemSeeder {
    public static void main(String[] args) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Dao<Item> itemDao = new GenericDAO<>(Item.class, session);

            Item item1 = new Item(0, "Curso online");
            Item item2 = new Item(0, "Workshop ministrado");
            Item item3 = new Item(0, "Ação social");

            itemDao.salvar(item1);
            itemDao.salvar(item2);
            itemDao.salvar(item3);
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
    }
}
