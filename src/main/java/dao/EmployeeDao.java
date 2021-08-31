package dao;

import entities.Employee;
import entities.Sportcomplex;
import entities.Training;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactory;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class EmployeeDao {
    public static final Logger logger = Logger.getLogger(EmployeeDao.class.getName());


    public Employee findById(int id) throws IOException {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            //-Djava.util.logging.config.file=C:/Users/163ty/Desktop/KursProdjWEB/WebSC/src/main/resources/log.properties
            logger.info("find By Id");
            return session.get(Employee.class, id);
        }
    }

    public void saveEmployee(Employee employee) throws IOException {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
            logger.info("save Employee");
        }
    }

    public void insertEmployee(int sportcomplexId, Employee employee) throws IOException {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Sportcomplex sportcomplex = session.get(Sportcomplex.class, sportcomplexId);
            sportcomplex.addEmployee(employee);
            // employee.setFkSc(sportcomplex);
            session.update(sportcomplex);
            session.save(employee);
            transaction.commit();
            logger.info("insert Employee");
        }
    }

    public void updateEmployee(Employee employee) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();
            logger.info("update Employee");
        }
    }

    public void deleteEmployee(int id) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(session.get(Employee.class, id));
            transaction.commit();
            logger.info("delete Employee");
        }
    }

    public Training findTrainingById(int id) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            logger.info("find Training By Id");
            return session.get(Training.class, id);
        }
    }

    public List<Employee> selectEmployees() {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            List<Employee> employees = (List<Employee>) session.createQuery("From Employee order by idEmployee").list();
            logger.info("select Employees");
            return employees;
        }
    }
}
