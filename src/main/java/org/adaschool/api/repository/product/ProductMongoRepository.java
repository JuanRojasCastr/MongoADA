package org.adaschool.api.repository.product;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

public interface ProductMongoRepository extends MongoRepository<Product, String> {

}
