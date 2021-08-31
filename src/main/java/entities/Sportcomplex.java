package entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Sportcomplex {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sportcomplex_id_sc_seq")
    @SequenceGenerator(name = "sportcomplex_id_sc_seq", sequenceName = "sportcomplex_id_sc_seq", allocationSize = 1)
    @Column(name = "id_sc", nullable = false)
    private int idSc;
    @Column(name = "name_sc", nullable = false, length = 50)
    private String nameSc;
    @Column(name = "phone_sc", nullable = true, precision = 0)
    private BigDecimal phoneSc;
    @Column(name = "address_sc", nullable = false, length = 70)
    private String addressSc;
    @OneToMany(mappedBy = "fkSc", cascade = CascadeType.ALL)
    private List<Employee> empList;

    public Sportcomplex() {
    }

    public Sportcomplex(String nameSc, BigDecimal phoneSc, String addressSc) {
        this.nameSc = nameSc;
        this.phoneSc = phoneSc;
        this.addressSc = addressSc;
        empList = new ArrayList<>();
    }

    public int getIdSc() {
        return idSc;
    }

    public void setIdSc(int idSc) {
        this.idSc = idSc;
    }

    public String getNameSc() {
        return nameSc;
    }

    public void setNameSc(String nameSc) {
        this.nameSc = nameSc;
    }

    public BigDecimal getPhoneSc() {
        return phoneSc;
    }

    public void setPhoneSc(BigDecimal phoneSc) {
        this.phoneSc = phoneSc;
    }

    public String getAddressSc() {
        return addressSc;
    }

    public void setAddressSc(String addressSc) {
        this.addressSc = addressSc;
    }

    public List<Employee> getEmpList() {
        return empList;
    }

    public void setEmpList(List<Employee> empList) {
        this.empList = empList;
    }

    @Override
    public String toString() {
        return "Sportcomplex{" +
                "idSc=" + getIdSc() +
                ", nameSc='" + getNameSc() +
                ", phoneSc=" + getPhoneSc() +
                ", addressSc='" + getAddressSc() +
                '}';
    }

    public void addEmployee(Employee employee) {
        employee.setFkSc(this);
        empList.add(employee);
    }

    public void removeEmployee(Employee employee) {
        empList.remove(employee);
    }
}
