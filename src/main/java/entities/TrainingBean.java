package entities;

import java.sql.Timestamp;

public class TrainingBean {
    private String nameDis;
    private Timestamp dateTraining;
    private String nameEmp;

    public TrainingBean(String nameDis, Timestamp dateTraining, String nameEmp) {
        this.nameDis = nameDis;
        this.dateTraining = dateTraining;
        this.nameEmp = nameEmp;
    }

    public String getNameDis() {
        return nameDis;
    }

    public void setNameDis(String nameDis) {
        this.nameDis = nameDis;
    }

    public Timestamp getDateTraining() {
        return dateTraining;
    }

    public void setDateTraining(Timestamp dateTraining) {
        this.dateTraining = dateTraining;
    }

    public String getNameEmp() {
        return nameEmp;
    }

    public void setNameEmp(String nameEmp) {
        this.nameEmp = nameEmp;
    }

    @Override
    public String toString() {
        return "TrainingBean{" +
                "nameDis='" + nameDis +
                ", dateTraining=" + dateTraining +
                ", nameEmp='" + nameEmp +
                '}';
    }
}
