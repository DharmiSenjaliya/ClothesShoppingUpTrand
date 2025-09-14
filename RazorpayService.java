package com.UpTrend.ClothesShopping.service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class RazorpayService {

    private RazorpayClient client;

    public RazorpayService() throws RazorpayException {
        this.client = new RazorpayClient("rzp_test_BzcHQzSJPU0CPl", "wUUDlENFQEWrmWx608lgn3Mq");
    }

    public Order createOrder(BigDecimal amount, String currency, String receipt) throws RazorpayException {
        JSONObject options = new JSONObject();
        options.put("amount", amount.multiply(BigDecimal.valueOf(100)).intValue()); // ₹ → paise
        options.put("currency", "INR");
        options.put("receipt", receipt);
        options.put("payment_capture", 1); // auto capture

        return client.orders.create(options);
    }
}
