package com.tsl3060.open.extend.core.router;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ReflectUtil;
import com.tsl3060.open.extend.core.INotifyListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class NotifyMapRouter {
    private Map<String, INotifyRouter> map = new HashMap<>();

    public NotifyMapRouter(INotifyListener notifyListener) {
        Set<Class<?>> classes = ClassUtil.scanPackage("com.tsl3060.open.extend.core.router.notify");

        for (Class<?> c : classes) {
            INotifyRouter notifyRouter = (INotifyRouter) ReflectUtil.newInstance(c,notifyListener);
            map.put(notifyRouter.path(), notifyRouter);
        }

    }


    public INotifyRouter getRouter(String module) {
        return map.get(module);
    }


}
