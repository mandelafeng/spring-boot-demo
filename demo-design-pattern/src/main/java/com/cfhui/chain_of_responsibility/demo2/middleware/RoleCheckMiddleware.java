package com.cfhui.chain_of_responsibility.demo2.middleware;

/**
 * ConcreteHandler. 检查用户的角色。
 */
public class RoleCheckMiddleware extends Middleware {
    @Override
    public boolean check(String email, String password) {
        if (email.equals("admin@example.com")) {
            System.out.println("Hello, admin!");
            return true;
        }
        System.out.println("Hello, user!");
        return checkNext(email, password);
    }
}
