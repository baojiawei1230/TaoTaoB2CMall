package com.taotao.store.order.threadLocal;

import com.taotao.store.order.bean.User;

public class UserThreadLocal {

    private static final ThreadLocal<User> local = new ThreadLocal<User>();

    public static void set(User user) {
        local.set(user);
    }

    public static User get() {
        return local.get();
    }

}
