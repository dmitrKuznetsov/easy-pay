package com.github.dmitrkuznetsov.backend.dao;

import com.github.dmitrkuznetsov.backend.entity.Payment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentDAOImpl implements PaymentDAO {

    private final SessionFactory sessionFactory;

    public PaymentDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Payment payment) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(payment);
    }

}
