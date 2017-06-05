package com.taotao.ucenter.threadlocal;

import com.taotao.ucenter.bean.User;

public class UserThreadLocal {

    private static final ThreadLocal<User> local = new ThreadLocal<User>();

    public static void set(User user) {
        local.set(user);
    }

    public static User get() {
        return local.get();
    }

}
