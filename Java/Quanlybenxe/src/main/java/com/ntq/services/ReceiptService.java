package com.ntq.services;

import com.ntq.pojo.Cart;
import java.util.Map;


public interface ReceiptService {
    boolean addReceipt(Map<String, Cart> carts);
}
