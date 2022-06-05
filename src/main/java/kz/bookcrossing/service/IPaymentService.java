package kz.bookcrossing.service;

import kz.bookcrossing.entity.Payment;

import java.util.List;

public interface IPaymentService {

    Payment save(Payment payment);

    List<Payment> getPaymentsByUser(Long userId);
}
