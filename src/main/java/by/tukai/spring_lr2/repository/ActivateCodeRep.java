package by.tukai.spring_lr2.repository;

import by.tukai.spring_lr2.model.ActivateCode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivateCodeRep extends CrudRepository<ActivateCode, String> {

}
