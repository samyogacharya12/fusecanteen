package com.example.fusecanteen.exception;


public class OrderExistException extends BadRequestAlertException {

    public OrderExistException() {
        super(ErrorConstants.ORDER_ALREADY_USED, "Order Already Exist", "", "");
    }
}
