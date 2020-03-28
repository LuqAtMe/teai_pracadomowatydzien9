package home.application.teai_pracadomowatydzien9.sql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepoSql extends JpaRepository<ModelSql, Long> {

}
