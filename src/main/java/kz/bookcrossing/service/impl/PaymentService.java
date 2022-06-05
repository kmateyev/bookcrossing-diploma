package kz.bookcrossing.service.impl;

import com.stripe.exception.*;
import com.stripe.model.Charge;
import kz.bookcrossing.entity.ChargeRequest;
import kz.bookcrossing.entity.Payment;
import kz.bookcrossing.repository.PaymentRepository;
import kz.bookcrossing.service.IPaymentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class PaymentService implements IPaymentService {

    @Value("${STRIPE_SECRET_KEY}")
    private String secretKey;

    private final PaymentRepository repository;

    @Override
    public Payment save(Payment payment) {
        return repository.save(payment);
    }

    @Override
    public List<Payment> getPaymentsByUser(Long userId) {
        return repository.findAllByUserId(userId);
    }

    public Charge charge(ChargeRequest chargeRequest)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, CardException, APIException {
        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", chargeRequest.getAmount());
        chargeParams.put("currency", chargeRequest.getCurrency());
        chargeParams.put("description", chargeRequest.getDescription());
        chargeParams.put("source", chargeRequest.getStripeToken());
        return Charge.create(chargeParams);
    }

}
