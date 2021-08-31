package dao;

import entities.Client;
import entities.Discipline;
import entities.Training;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactory;
import java.util.List;
import java.util.logging.Logger;

public class ClientDao {
    public static final Logger logger = Logger.getLogger(ClientDao.class.getName());

    public Client findById(int id) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            logger.info("find By Id");
            return session.get(Client.class, id);
        }
    }

    public void saveClient(Client client) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(client);
            transaction.commit();
            logger.info("save Client");
        }
    }

    public void updateClient(Client client) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(client);
            transaction.commit();
            logger.info("update Client");
        }
    }

    public void deleteClient(int id) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(session.get(Client.class, id));
            transaction.commit();
            logger.info("delete Client");
        }
    }

    public Training findTrainingById(int id) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            logger.info("find Training By Id");
            return session.get(Training.class, id);
        }
    }

    public List<Client> selectClients() {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            List<Client> clients = (List<Client>) session.createQuery("From Client order by idClient").list();
            logger.info("select Clients");
            return clients;
        }
    }
}
