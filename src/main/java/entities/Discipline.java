package entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Discipline {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "discipline_id_discipline_seq")
    @SequenceGenerator(name = "discipline_id_discipline_seq", sequenceName = "discipline_id_discipline_seq", allocationSize = 1)
    @Column(name = "id_discipline", nullable = false)
    private int idDiscipline;
    @Column(name = "type_discipline", nullable = false, length = 30)
    private String typeDiscipline;
    @Column(name = "date_start", nullable = false)
    private Timestamp dateStart;
    @Column(name = "number_discipline", nullable = true, precision = 0)
    private BigDecimal numberDiscipline;
    @Column(name = "price", nullable = false)
    private int price;
    @OneToMany(mappedBy = "fkDis", cascade = CascadeType.ALL)
    private List<Training> trDisList;

    public Discipline() {
    }

    public Discipline(String typeDiscipline, Timestamp dateStart, BigDecimal numberDiscipline, int price) {
        this.typeDiscipline = typeDiscipline;
        this.dateStart = dateStart;
        this.numberDiscipline = numberDiscipline;
        this.price = price;
        trDisList = new ArrayList<>();
    }

    public int getIdDiscipline() {
        return idDiscipline;
    }

    public void setIdDiscipline(int idDiscipline) {
        this.idDiscipline = idDiscipline;
    }

    public String getTypeDiscipline() {
        return typeDiscipline;
    }

    public void setTypeDiscipline(String typeDiscipline) {
        this.typeDiscipline = typeDiscipline;
    }

    public Timestamp getDateStart() {
        return dateStart;
    }

    public void setDateStart(Timestamp dateStart) {
        this.dateStart = dateStart;
    }

    public BigDecimal getNumberDiscipline() {
        return numberDiscipline;
    }

    public void setNumberDiscipline(BigDecimal numberDiscipline) {
        this.numberDiscipline = numberDiscipline;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Training> getTrDisList() {
        return trDisList;
    }

    public void setTrDisList(List<Training> trDisList) {
        this.trDisList = trDisList;
    }

    @Override
    public String toString() {
        return "Discipline{" +
                "idDiscipline=" + getIdDiscipline() +
                ", typeDiscipline=" + getTypeDiscipline() +
                ", dateStart=" + getDateStart() +
                ", numberDiscipline=" + getNumberDiscipline() +
                ", price=" + getPrice() +
                '}';
    }

    public void addTraining(Training training) {
        training.setFkDis(this);
        trDisList.add(training);
    }

    public void removeTraining(Training training) {
        trDisList.remove(training);
    }
}
