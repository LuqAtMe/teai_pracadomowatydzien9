package home.application.teai_pracadomowatydzien9;

import com.opencsv.bean.CsvToBeanBuilder;
import home.application.teai_pracadomowatydzien9.aspect.AspectServiceLoad;
import home.application.teai_pracadomowatydzien9.aspect.AspectServiceSave;
import home.application.teai_pracadomowatydzien9.nosql.ModelNoSql;
import home.application.teai_pracadomowatydzien9.nosql.ModelRepoNoSql;
import home.application.teai_pracadomowatydzien9.sql.ModelRepoSql;
import home.application.teai_pracadomowatydzien9.sql.ModelSql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class Start {

    private ModelRepoNoSql modelRepoNoSql;
    private ModelRepoSql modelRepoSql;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public Start(ModelRepoNoSql modelRepoNoSql, ModelRepoSql modelRepoSql, JdbcTemplate jdbcTemplate) {
        this.modelRepoNoSql = modelRepoNoSql;
        this.modelRepoSql = modelRepoSql;
        this.jdbcTemplate = jdbcTemplate;
    }

    @AspectServiceSave
    public void saveInMongo() throws IOException {
        FileReader fileReader = new FileReader("src/main/resources/MOCK_DATA.csv");
        List<ModelNoSql> modelsNoSql = new CsvToBeanBuilder(fileReader).withType(ModelNoSql.class).build().parse();
        modelsNoSql.forEach(modelNoSql -> modelRepoNoSql.save(modelNoSql));
        System.out.println("Records saved");
    }
    @EventListener(ApplicationReadyEvent.class)
    @AspectServiceSave
    public void saveInH2() throws IOException {
        FileReader fileReader = new FileReader("src/main/resources/MOCK_DATA.csv");
        List<ModelSql> modelsSql = new CsvToBeanBuilder(fileReader).withType(ModelSql.class).build().parse();
        modelsSql.forEach(modelSql -> modelRepoSql.save(modelSql));
        System.out.println("Records saved");
    }


    @AspectServiceSave
    public void saveInRemoteMySql() throws IOException {

        FileReader fileReader = new FileReader("src/main/resources/MOCK_DATA.csv");
        List<ModelSql> modelsSql = new CsvToBeanBuilder(fileReader).withType(ModelSql.class).build().parse();
        for (ModelSql model : modelsSql
        ) {
            String sql = "INSERT INTO models VALUES (?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql, model.getId(), model.getGender(), model.getFullName(), model.getEmail(), model.getDepartment(), model.getCarVin());
        }
        System.out.println("Records saved");
    }

    @EventListener(ApplicationReadyEvent.class)
    @AspectServiceLoad
    public void readFromH2() {
        List<ModelSql> all = modelRepoSql.findAll();
        System.out.println("Records loaded: " + all.size());
    }

    @AspectServiceLoad
    public void readFromMongo() {
        List<ModelNoSql> all = modelRepoNoSql.findAll();
        System.out.println("Records loaded: " + all.size());
    }


    @AspectServiceLoad
    public void readFromRemoteMySQL() {
        String sql = "SELECT * FROM models";
        List<ModelSql> all = new ArrayList<ModelSql>();
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        maps.stream().forEach(model -> all.add(new ModelSql(
                        model.get("owner_gender").toString(),
                        model.get("owner_fullname").toString(),
                        model.get("owner_email").toString(),
                        model.get("owner_department").toString(),
                        model.get("owner_car_vin").toString()
                )
                )
        );
        System.out.println("Records loaded: " + all.size());
    }
}
