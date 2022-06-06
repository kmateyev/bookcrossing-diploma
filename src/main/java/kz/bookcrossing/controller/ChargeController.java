package kz.bookcrossing.controller;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import kz.bookcrossing.entity.ChargeRequest;
import kz.bookcrossing.service.impl.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChargeController {
    @Autowired
    private PaymentService paymentsService;

    @PostMapping("/charge")
    public ResponseEntity charge(ChargeRequest chargeRequest, Model model)
            throws StripeException {
        chargeRequest.setDescription("Example charge");
        chargeRequest.setCurrency(ChargeRequest.Currency.EUR);
        Charge charge = paymentsService.charge(chargeRequest);
        return ResponseEntity.ok(charge);
    }

    @ExceptionHandler(StripeException.class)
    public String handleError(Model model, StripeException ex) {
        model.addAttribute("error", ex.getMessage());
        return "error";
    }
}
