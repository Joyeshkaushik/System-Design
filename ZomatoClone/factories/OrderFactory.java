package ZomatoClone.factories;

import java.util.List;
import ZomatoClone.models.*;
import ZomatoClone.strategies.*;

public interface OrderFactory {
    Order createOrder(User user, Cart cart, Restaurant restaurant, List<MenuItem> menuItems,
                      PaymentStrategy paymentStrategy, double totalCost, String orderType);
}