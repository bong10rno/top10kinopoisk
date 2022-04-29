package top.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import top.entities.Top10;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class Top10DAO {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Top10> getForDate(String date) {
        return entityManager.createQuery("from Top10 c where c.date = date order by c.position desc", Top10.class).getResultList();
    }

    public Top10 getById(int id) {
        return entityManager.find(Top10.class, id);
    }
}
