package top.dao;

import org.hibernate.CacheMode;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import top.entities.Top10;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class MainDAO {

    @PersistenceContext
    private EntityManager entityManager;

    private Session session;

    public MainDAO() {
    }

    public List<Top10> getTop10ForDate(Date date) {
        CriteriaBuilder criteriaBuilder = getSession().getCriteriaBuilder();
        CriteriaQuery<Top10> criteriaQuery = criteriaBuilder.createQuery(Top10.class);
        Root<Top10> root = criteriaQuery.from(Top10.class);
        criteriaQuery.select(root);
        criteriaQuery.where(root.get("date").in(date));
        TypedQuery<Top10> query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }

    public <T> void save(T o) {
        getSession().saveOrUpdate(o);
    }

    private Session getSession() {
        if ((session == null) || (!session.isOpen())) {
            this.session = (Session) entityManager.getDelegate();
            session.setCacheMode(CacheMode.GET);
            return session;
        } else {
            return session;
        }
    }
}
