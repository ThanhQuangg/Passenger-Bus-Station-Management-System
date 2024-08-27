
package com.ntq.repositories;

import com.ntq.pojo.Cart;
import java.util.Map;


public interface ReceiptRepository {
    boolean addReceipt(Map<String, Cart> carts);
}
