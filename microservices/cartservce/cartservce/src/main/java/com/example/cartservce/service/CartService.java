package com.example.cartservce.service;

import com.example.cartservce.constants.CartStatus;
import com.example.cartservce.dto.CartResponse;
import com.example.cartservce.dto.NewCart;
import com.example.cartservce.dto.ProductResponse;
import com.example.cartservce.dto.RequestCart;
import com.example.cartservce.mapping.CartMapper;
import com.example.cartservce.model.Cart;
import com.example.cartservce.model.CartItem;
import com.example.cartservce.repo.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class CartService {

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private InventoryFeignclient inventoryFeignclient;

    @Autowired
    private ProductFeignClient productFeignClient;

    // ---------------- CREATE / ADD ITEM ----------------
    public NewCart createNewCart(RequestCart requestCart) {

        int userId = requestCart.getUserId();
        int productId = requestCart.getProductId();
        int quantity = requestCart.getQuantity();

        if (!checkProductById(productId, quantity)) {
            throw new RuntimeException("Out of stock");
        }

        float price = getProduct(productId).getPrice();

        Cart cart = cartRepo.findByUserId(userId)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUserId(userId);
                    newCart.setCartStatus(CartStatus.ACTIVE);
                    newCart.setCartItemList(new ArrayList<>());
                    return cartRepo.save(newCart);
                });

        CartItem item = cart.getCartItemList()
                .stream()
                .filter(i -> i.getProductId() == productId)
                .findFirst()
                .orElse(null);

        if (item != null) {
            item.setProductQuantity(item.getProductQuantity() + quantity);
        } else {
            CartItem newItem = new CartItem();
            newItem.setProductId(productId);
            newItem.setProductQuantity(quantity);
            newItem.setProductPriceSnapshot(price);
            newItem.setCart(cart);
            cart.getCartItemList().add(newItem);
        }

        return cartMapper.convertCartToNewCart(cartRepo.save(cart));
    }

    // ---------------- GET CART ----------------
    public CartResponse findByUserId(int userId) {
        Cart cart = cartRepo.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        return cartMapper.convertCartToCartResponse(cart);
    }

    // ---------------- INCREASE ----------------
    public CartResponse increaseQuantity(int userId, int productId, int quantity) {

        Cart cart = getCart(userId);

        CartItem item = findItem(cart, productId);

        int newQty = item.getProductQuantity() + quantity;

        if (!checkProductById(productId, newQty)) {
            throw new RuntimeException("Not enough stock");
        }

        item.setProductQuantity(newQty);

        return cartMapper.convertCartToCartResponse(cartRepo.save(cart));
    }

    // ---------------- DECREASE ----------------
    public CartResponse decreaseQuantity(int userId, int productId, int quantity) {

        Cart cart = getCart(userId);

        CartItem item = findItem(cart, productId);

        int newQty = item.getProductQuantity() - quantity;

        if (newQty < 0) {
            throw new RuntimeException("Invalid quantity");
        }

        if (newQty == 0) {
            cart.getCartItemList().remove(item);
        } else {
            item.setProductQuantity(newQty);
        }

        return cartMapper.convertCartToCartResponse(cartRepo.save(cart));
    }

    // ---------------- REMOVE ITEM ----------------
    public CartResponse removeItem(int userId, int productId) {

        Cart cart = getCart(userId);

        CartItem item = findItem(cart, productId);

        cart.getCartItemList().remove(item);

        return cartMapper.convertCartToCartResponse(cartRepo.save(cart));
    }

    // ---------------- CLEAR CART ----------------
    public void clearCart(int userId) {

        Cart cart = getCart(userId);

        cart.getCartItemList().clear();

        cartRepo.save(cart);
    }

    // ---------------- SUMMARY ----------------
    public Map<String, Object> getCartSummary(int userId) {

        Cart cart = getCart(userId);

        int totalItems = cart.getCartItemList().size();
        int totalQty = cart.getCartItemList()
                .stream()
                .mapToInt(CartItem::getProductQuantity)
                .sum();

        float totalPrice = (float) cart.getCartItemList()
                .stream()
                .mapToDouble(i -> i.getProductPriceSnapshot() * i.getProductQuantity())
                .sum();
        Map<String, Object> map = new HashMap<>();
        map.put("totalItems", totalItems);
        map.put("totalQuantity", totalQty);
        map.put("totalPrice", totalPrice);

        return map;
    }

    // ---------------- CHECKOUT ----------------
    public void checkout(int userId) {

        Cart cart = getCart(userId);

        if (cart.getCartItemList().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        // TODO: call Order Service (future microservice)
        //6200

        cart.getCartItemList().clear();
        cartRepo.save(cart);
    }

    // ---------------- HELPERS ----------------
    private Cart getCart(int userId) {
        return cartRepo.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    private CartItem findItem(Cart cart, int productId) {
        return cart.getCartItemList()
                .stream()
                .filter(i -> i.getProductId() == productId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not in cart"));
    }

    public boolean checkProductById(int id, int quantity) {
        try {
            return inventoryFeignclient.checkIfAvailable(id, quantity).getBody();
        } catch (Exception e) {
            return false;
        }
    }

    public ProductResponse getProduct(int id) {
        return productFeignClient.getById(id).getBody();
    }
}