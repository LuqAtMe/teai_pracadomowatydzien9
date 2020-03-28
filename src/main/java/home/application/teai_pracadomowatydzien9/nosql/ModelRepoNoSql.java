package home.application.teai_pracadomowatydzien9.nosql;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepoNoSql extends MongoRepository<ModelNoSql, String> {

}
