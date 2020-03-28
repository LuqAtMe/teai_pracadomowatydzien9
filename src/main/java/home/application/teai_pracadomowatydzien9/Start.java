package home.application.teai_pracadomowatydzien9;

import com.opencsv.bean.CsvToBeanBuilder;
import home.application.teai_pracadomowatydzien9.aspect.AspectServiceAnnotation;
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
import java.util.List;

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


    public void initForNoSql() throws IOException {

        FileReader fileReader = new FileReader("src/main/resources/MOCK_DATA.csv");
        List<ModelNoSql> modelsNoSql = new CsvToBeanBuilder(fileReader).withType(ModelNoSql.class).build().parse();
        modelsNoSql.forEach(modelNoSql -> modelRepoNoSql.save(modelNoSql));

    }

    @AspectServiceAnnotation
    @EventListener(ApplicationReadyEvent.class)
    public void initForSql() throws IOException {

        FileReader fileReader = new FileReader("src/main/resources/MOCK_DATA.csv");
        List<ModelSql> modelsSql = new CsvToBeanBuilder(fileReader).withType(ModelSql.class).build().parse();
        modelsSql.forEach(modelSql -> modelRepoSql.save(modelSql));

    }

    public void initForRemoteMySql() throws IOException {

        FileReader fileReader = new FileReader("src/main/resources/MOCK_DATA.csv");
        List<ModelSql> modelsSql = new CsvToBeanBuilder(fileReader).withType(ModelSql.class).build().parse();
        for (ModelSql model: modelsSql
             ) {
            String sql = "INSERT INTO models VALUES (?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql, model.getId(), model.getGender(), model.getFullName(), model.getEmail(), model.getDepartment(), model.getCarVin());
        }

    }



}
