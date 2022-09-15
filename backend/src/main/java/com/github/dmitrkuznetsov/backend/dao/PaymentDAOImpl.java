package com.github.dmitrkuznetsov.backend.dao;

import com.github.dmitrkuznetsov.backend.entity.Payment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Repository
public class PaymentDAOImpl implements PaymentDAO {

    private final SessionFactory sessionFactory;

    public PaymentDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void save(Payment payment) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(payment);
    }

    @Override
    @Transactional
    public List<Payment> getPaymentFromDate(Date date) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Payment where datetimeTransaction > :lastDateTimeTransaction");
        query.setParameter("lastDateTimeTransaction", date);
        List<Payment> list = query.getResultList();
        return list;
    }

}
