package tr.com.trs.customerbatch.dal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tr.com.trs.customerbatch.dal.model.Customer;


@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {


}
