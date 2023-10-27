package com.cfhui.chain_of_responsibility.demo2.middleware;

import com.cfhui.chain_of_responsibility.demo2.server.Server;

/**
 * ConcreteHandler. 检查是否存在具有给定凭据的用户。.
 */
public class UserExistsMiddleware extends Middleware {
    private Server server;

    public UserExistsMiddleware(Server server) {
        this.server = server;
    }

    @Override
    public boolean check(String email, String password) {
        if (!server.hasEmail(email)) {
            System.out.println("This email is not registered!");
            return false;
        }
        if (!server.isValidPassword(email, password)) {
            System.out.println("Wrong password!");
            return false;
        }
        return checkNext(email, password);
    }
}
