package dao;

import entities.Client;
import entities.Discipline;
import entities.Training;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateSessionFactory;


import java.util.List;
import java.util.logging.Logger;

public class DisciplineDao {
    public static final Logger logger = Logger.getLogger(DisciplineDao.class.getName());


    public Discipline findById(int id) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            logger.info("find By Id");
            return session.get(Discipline.class, id);
        }
    }

    public void saveDiscipline(Discipline discipline) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(discipline);
            transaction.commit();
            logger.info("save Discipline");
        }
    }

    public void updateDiscipline(Discipline discipline) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(discipline);
            transaction.commit();
            logger.info("update Discipline");
        }
    }

    public void deleteDiscipline(int id) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(session.get(Discipline.class, id));
            transaction.commit();
            logger.info("delete Discipline");
        }
    }

    public Training findTrainingById(int id) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            logger.info("find Training By Id");
            return session.get(Training.class, id);
        }
    }

    public List<Discipline> selectDiscipline() {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            List<Discipline> disciplines = (List<Discipline>) session.createQuery("From Discipline order by idDiscipline").list();
            logger.info("select Discipline");
            return disciplines;
        }
    }
    public List<Discipline> selectDisByPrice(int price1,int price2)
    {
        try(Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            String hql = "from Discipline where price between :pr1 and :pr2 order by price";
            Query query = session.createQuery(hql);
            query.setParameter("pr1",price1);
            query.setParameter("pr2",price2);
            return query.list();
        }
    }
}
