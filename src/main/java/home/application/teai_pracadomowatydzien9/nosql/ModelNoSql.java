package home.application.teai_pracadomowatydzien9.nosql;

import com.opencsv.bean.CsvBindByName;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document ()
public class ModelNoSql {

    @Id
    private String id;
    @CsvBindByName (column = "owner_gender", required = true)
    private String gender;
    @CsvBindByName (column = "owner_fullname", required = true)
    private String fullName;
    @CsvBindByName (column = "owner_email", required = true)
    private String email;
    @CsvBindByName (column = "owner_department", required = true)
    private String department;
    @CsvBindByName (column = "owner_car_vin", required = true)
    private String carVin;

    public ModelNoSql(String gender, String fullName, String email, String department, String carVin) {
        this.gender = gender;
        this.fullName = fullName;
        this.email = email;
        this.department = department;
        this.carVin = carVin;
    }

    public ModelNoSql() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCarVin() {
        return carVin;
    }

    public void setCarVin(String carVin) {
        this.carVin = carVin;
    }

}
