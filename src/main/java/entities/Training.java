package entities;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "training_id_training_seq")
    @SequenceGenerator(name = "training_id_training_seq", sequenceName = "training_id_training_seq", allocationSize = 1)
    @Column(name = "id_training", nullable = false)
    private int idTraining;
    @Column(name = "date_training", nullable = true)
    private Timestamp dateTraining;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_client")
    private Client fkClient;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_employee")
    private Employee fkEmp;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_discipline")
    private Discipline fkDis;

    public Training() {
    }

    public Training(Timestamp dateTraining) {
        this.dateTraining = dateTraining;
    }

    public int getIdTraining() {
        return idTraining;
    }

    public void setIdTraining(int idTraining) {
        this.idTraining = idTraining;
    }

    public Timestamp getDateTraining() {
        return dateTraining;
    }

    public void setDateTraining(Timestamp dateTraining) {
        this.dateTraining = dateTraining;
    }

    public Client getFkClient() {
        return fkClient;
    }

    public void setFkClient(Client fkClient) {
        this.fkClient = fkClient;
    }

    public Employee getFkEmp() {
        return fkEmp;
    }

    public void setFkEmp(Employee fkEmp) {
        this.fkEmp = fkEmp;
    }

    public Discipline getFkDis() {
        return fkDis;
    }

    public void setFkDis(Discipline fkDis) {
        this.fkDis = fkDis;
    }

    @Override
    public String toString() {
        return "Training{" +
                "idTraining=" + getIdTraining() +
                ", dateTraining=" + getDateTraining() +
                '}';
    }
}
