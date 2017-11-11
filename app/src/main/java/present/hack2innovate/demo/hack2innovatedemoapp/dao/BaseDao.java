package present.hack2innovate.demo.hack2innovatedemoapp.dao;

import java.util.List;

/**
 * Created by anweshmishra on 11/11/17.
 */

public interface BaseDao<T> {
    List<T> findAll();
    void create(T t);
}
