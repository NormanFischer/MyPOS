package com.norman.MyPosServer.Item;

import com.norman.MyPosServer.Item.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends CrudRepository<Item, Integer> {
    @Query(value = "SELECT * FROM item WHERE sku LIKE %?1%", nativeQuery = true)
    List<Item> listBySku(String sku);

    @Query(value = "SELECT * FROM item WHERE item_name LIKE %?1%", nativeQuery = true)
    List<Item> listByName(String itemName);

    @Query(value = "SELECT id FROM item WHERE sku = ?1", nativeQuery = true)
    Optional<Integer> getIDBySku(String sku);

    @Query(value = "SELECT * FROM item WHERE sku = ?1", nativeQuery = true)
    Optional<Item> getItemBySku(String sku);

    @Query(value = "SELECT cost FROM item WHERE sku = ?1", nativeQuery = true)
    Optional<BigInteger> getCostBySku(String sku);

}
