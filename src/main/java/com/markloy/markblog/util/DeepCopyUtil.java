package com.markloy.markblog.util;

import java.io.*;
import java.util.List;

public class DeepCopyUtil {

    /***
     * 对集合进行深拷贝 注意需要对泛型类进行序列化(实现Serializable)
     *
     * @param srcList
     * @param <T>
     * @return
     */
    public static <T> List<T> depCopy(List<T> srcList) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(srcList);
        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream inStream = new ObjectInputStream(byteIn);
        return (List<T>) inStream.readObject();
    }

}
