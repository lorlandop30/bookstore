package com.bookstore.bookstore.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.bookstore.models.Book;
import com.bookstore.bookstore.models.CartItem;
import com.bookstore.bookstore.models.Order;
import com.bookstore.bookstore.models.ShoppingCart;
import com.bookstore.bookstore.models.User;
import com.bookstore.bookstore.repositories.OrderRepository;
import com.bookstore.bookstore.services.CartItemService;
import com.bookstore.bookstore.services.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartItemService cartItemService;

    public synchronized Order createOrder(ShoppingCart shoppingCart,
                                          User user) {
        Order order = new Order();

        List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);

        for (CartItem cartItem : cartItemList) {
            Book book = cartItem.getBook();
            cartItem.setOrder(order);
            book.setInStockNumber(book.getInStockNumber() - cartItem.getQty());
        }

        order.setCartItemList(cartItemList);
        order.setUser(user);
        order = orderRepository.save(order);

        return order;
    }

    public Order findOne(Long id) {
        return orderRepository.findOrderById(id);
    }

    public Iterable<Order> findAll(){
        return orderRepository.findAll();
    }

//    public List<Order> fidAllByUser(User user){
//        Iterable<Order> orders = orderRepository.findAll();
//        List<Order> ordersByUser = new ArrayList<Order>();
//        for (Order order : orders) {
//            if(order.getUser().getId() == user.getId())
//                ordersByUser.add(order);
//        }
//
//        return ordersByUser;
//
//    }
}
