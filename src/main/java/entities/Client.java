package entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_id_client_seq")
    @SequenceGenerator(name = "client_id_client_seq", sequenceName = "client_id_client_seq", allocationSize = 1)
    @Column(name = "id_client", nullable = false)
    private int idClient;
    @Column(name = "fio_client", nullable = false, length = 50)
    private String fioClient;
    @Column(name = "date_birth", nullable = true)
    private Date birthday;
    @Column(name = "gender", nullable = true, length = 10)
    private String gender;
    @Column(name = "phone_client", nullable = false, precision = 0)
    private BigDecimal phoneClient;
    @OneToMany(mappedBy = "fkClient", cascade = CascadeType.ALL)
    private List<Training> trClList;

    public Client() {
    }

    public Client(String fioClient, Date birthday, String gender, BigDecimal phoneClient) {
        this.fioClient = fioClient;
        this.birthday = birthday;
        this.gender = gender;
        this.phoneClient = phoneClient;
        trClList = new ArrayList<>();
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getFioClient() {
        return fioClient;
    }

    public void setFioClient(String fioClient) {
        this.fioClient = fioClient;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public BigDecimal getPhoneClient() {
        return phoneClient;
    }

    public void setPhoneClient(BigDecimal phoneClient) {
        this.phoneClient = phoneClient;
    }

    public List<Training> getTrClList() {
        return trClList;
    }

    public void setTrClList(List<Training> trClList) {
        this.trClList = trClList;
    }

    @Override
    public String toString() {
        return "Client{" +
                "idClient=" + getIdClient() +
                ", fioClient=" + getFioClient() +
                ", birthday=" + getBirthday() +
                ", gender=" + getGender() +
                ", phoneClient=" + getPhoneClient() +
                '}';
    }

    public void addTraining(Training training) {
        training.setFkClient(this);
        trClList.add(training);
    }

    public void removeTraining(Training training) {
        trClList.remove(training);
    }
}
