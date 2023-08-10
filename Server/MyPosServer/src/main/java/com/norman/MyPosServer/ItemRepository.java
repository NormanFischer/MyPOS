package com.norman.MyPosServer;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ItemRepository extends CrudRepository<Item, Integer> {
    @Query(value = "SELECT * from item WHERE sku LIKE %?1%", nativeQuery = true)
    List<Item> listBySku(String sku);

    @Query(value = "SELECT * from item WHERE item_name LIKE %?1%", nativeQuery = true)
    List<Item> listByName(String itemName);

}
