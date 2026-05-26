package com.example.orderservice.constant;
/**
 * Represents different states of a shopping cart.
 */
public enum CartStatus {

    /**
     *  User is currently adding or removing items.
     */
    ACTIVE,

    /**
     * Order has been successfully placed from this cart.
     */
    CHECKED_OUT,

    /**
     * User left the cart without completing checkout.
     */
    ABANDONED,

    /**
     * Cart expired after a certain validity duration.
     */
    EXPIRED,

    /**
     * User saved cart items for future purchase.
     */
    SAVED_FOR_LATER
}
