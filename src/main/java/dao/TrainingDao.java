package dao;

import entities.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateSessionFactory;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TrainingDao {
    public static final Logger logger = Logger.getLogger(TrainingDao.class.getName());

    public Training findById(int id) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            logger.info("find By Id");
            return session.get(Training.class, id);
        }
    }

    public void saveTraining(Training training) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(training);
            transaction.commit();
            logger.info("save Training");
        }
    }

    public void insertTraining(int disciplineId, int employeeId, int clientId, Training training) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Discipline discipline = session.get(Discipline.class, disciplineId);
            Client client = session.get(Client.class, clientId);
            Employee employee = session.get(Employee.class, employeeId);

            discipline.addTraining(training);
            client.addTraining(training);
            employee.addTraining(training);

           /* training.setFkDis(discipline);
            training.setFkClient(client);
            training.setFkEmp(employee);*/

            session.update(discipline);
            session.update(client);
            session.update(employee);
            session.save(training);
            transaction.commit();
            logger.info("insert Training");
        }
    }

    public void updateTraining(Training training) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(training);
            transaction.commit();
            logger.info("update Training");
        }
    }

    public void deleteTraining(int id) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(session.get(Training.class, id));
            transaction.commit();
            logger.info("delete Training");
        }
    }

    public List<Training> selectTraining() {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            List<Training> trainings = (List<Training>) session.createQuery("From Training order by dateTraining desc").list();
            logger.info("select Training");
            return trainings;
        }
    }

    public List<TrainingBean> selectTrByEmp(String fio) {
        List<TrainingBean> trList = new ArrayList<>();
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            String hql = "SELECT d.typeDiscipline, trEmp.dateTraining, e.fioEmployee " +
                    "from Employee e inner join e.trEmpList trEmp, Discipline d" +
                    " join d.trDisList trDis where e.fioEmployee " +
                    "like :pr1 and trEmp.id =trDis.id";

            Query query = session.createQuery(hql);
            query.setParameter("pr1", fio + "%");
            List<Object[]> rows = query.list();
            for (Object[] row : rows) {
                TrainingBean tr = new TrainingBean((String) row[0], (Timestamp) row[1], (String) row[2]);
                trList.add(tr);
            }
            return trList;
        }
    }
}

