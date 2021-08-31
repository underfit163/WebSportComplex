package entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_id_employee_seq")
    @SequenceGenerator(name = "employee_id_employee_seq", sequenceName = "employee_id_employee_seq", allocationSize = 1)
    @Column(name = "id_employee", nullable = false)
    private int idEmployee;
    @Column(name = "fio_employee", nullable = false, length = 50)
    private String fioEmployee;
    @Column(name = "phone_employee", nullable = false, precision = 0)
    private BigDecimal phoneEmployee;
    @Column(name = "date_birth", nullable = true)
    private Date birthday;
    @Column(name = "education", nullable = true, length = 50)
    private String education;
    @Column(name = "salary", nullable = false)
    private int salary;
    @Column(name = "position_emp", nullable = false, length = 50)
    private String positionEmp;
    @Column(name = "work_exp", nullable = false)
    private int workExp;
    @Column(name = "gender", nullable = true, length = 10)
    private String gender;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sc")
    private Sportcomplex fkSc;
    @OneToMany(mappedBy = "fkEmp", cascade = CascadeType.ALL)
    private List<Training> trEmpList;


    public Employee() {
    }

    public Employee(String fioEmployee, BigDecimal phoneEmployee, Date birthday, String education, int salary, String positionEmp, int workExp, String gender) {
        this.fioEmployee = fioEmployee;
        this.phoneEmployee = phoneEmployee;
        this.birthday = birthday;
        this.education = education;
        this.salary = salary;
        this.positionEmp = positionEmp;
        this.workExp = workExp;
        this.gender = gender;
        trEmpList = new ArrayList<>();
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getFioEmployee() {
        return fioEmployee;
    }

    public void setFioEmployee(String fioEmployee) {
        this.fioEmployee = fioEmployee;
    }

    public BigDecimal getPhoneEmployee() {
        return phoneEmployee;
    }

    public void setPhoneEmployee(BigDecimal phoneEmployee) {
        this.phoneEmployee = phoneEmployee;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getPositionEmp() {
        return positionEmp;
    }

    public void setPositionEmp(String positionEmp) {
        this.positionEmp = positionEmp;
    }

    public int getWorkExp() {
        return workExp;
    }

    public void setWorkExp(int workExp) {
        this.workExp = workExp;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Sportcomplex getFkSc() {
        return fkSc;
    }

    public void setFkSc(Sportcomplex fkSc) {
        this.fkSc = fkSc;
    }

    public List<Training> getTrEmpList() {
        return trEmpList;
    }

    public void setTrEmpList(List<Training> trEmpList) {
        this.trEmpList = trEmpList;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "idEmployee=" + getIdEmployee() +
                ", fioEmployee=" + getFioEmployee() +
                ", phoneEmployee=" + getPhoneEmployee() +
                ", birthday=" + getBirthday() +
                ", education=" + getEducation() +
                ", salary=" + getSalary() +
                ", positionEmp=" + getPositionEmp() +
                ", workExp=" + getWorkExp() +
                ", gender='" + getGender() +
                '}';
    }

    public void addTraining(Training training) {
        training.setFkEmp(this);
        trEmpList.add(training);
    }

    public void removeTraining(Training training) {
        trEmpList.remove(training);
    }
}
