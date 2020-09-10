package com.kyx.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class Md5 {
    public static Object md5(String name,String password){
        ByteSource credentialsSalt = ByteSource.Util.bytes(name);
        return new SimpleHash("MD5",password,credentialsSalt,1024);
    }

}
