package com.example.fusecanteen.exception;

public class LoginAlreadyUsedException extends BadRequestAlertException {

    public LoginAlreadyUsedException() {
        super(ErrorConstants.LOGIN_ALREADY_USED_TYPE, "Mobile Number already in use", "", "");
    }
}
