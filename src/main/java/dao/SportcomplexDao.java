package dao;

import entities.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactory;


import java.util.List;
import java.util.logging.Logger;

public class SportcomplexDao {
    public static final Logger logger = Logger.getLogger(SportcomplexDao.class.getName());

    public Sportcomplex findById(int id) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            logger.info("find By Id");
            return session.get(Sportcomplex.class, id);
        }
    }

    public void saveSportcomplex(Sportcomplex sportcomplex) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(sportcomplex);
            transaction.commit();
            logger.info("save Sportcomplex");
        }
    }

    public void updateSportcomplex(Sportcomplex sportcomplex) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(sportcomplex);
            transaction.commit();
            logger.info("update Sportcomplex");
        }
    }

    public void deleteSportcomplex(int id) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(session.get(Sportcomplex.class, id));
            transaction.commit();
            logger.info("delete Sportcomplex");
        }
    }

    public Employee findEmployeeById(int id) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            logger.info("find Employee By Id");
            return session.get(Employee.class, id);
        }
    }

    public List<Sportcomplex> selectSportcomplex() {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            List<Sportcomplex> sportcomplexs = (List<Sportcomplex>) session.createQuery("From Sportcomplex order by idSc").list();
            logger.info("select Sportcomplex");
            return sportcomplexs;
        }
    }
}
