package com.luolei.guava.base;

import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

/**
 * Objects对象 在JDK1.8 的util包里面有了
 * 因此还是使用JDK里面的类，不使用Guava里面的
 * 这个类里面就是静态的工具方法
 * Created by 罗雷 on 2017/3/8.
 */
public class ObjectsTest01 {

    @Test
    public void test1() {
        /*
            boolean equals(Object a, Object b)
            比较两个对象是否相等
         */
        assertThat(Objects.equals(null, null), is(true));
        assertThat(Objects.equals("test", null), is(false));
        assertThat(Objects.equals(null, "test"), is(false));
        assertThat(Objects.equals("test", "test"), is(true));
    }

    @Test
    public void test2() {
        /*
            boolean deepEquals(Object a, Object b)
            毕竟两个对象是否相等
            如果是数组对象，则会继续比较数组里面的每个位置的元素是否相等
         */
        assertThat(Objects.deepEquals(null, null), is(true));
        assertThat(Objects.deepEquals("test", null), is(false));
        assertThat(Objects.deepEquals(null, "test"), is(false));
        assertThat(Objects.deepEquals("test", "test"), is(true));
        assertThat(Objects.deepEquals(new String[]{"test1", "test2"}, new String[] {"test1", "test3"}), is(false));
        assertThat(Objects.deepEquals(new String[]{"test1", "test2"}, new String[] {"test1", "test2"}), is(true));
    }

    static class Bean {
        private int id;
        private String key;
        private String value;

        public Bean() {
        }

        public Bean(int id, String key, String value) {
            this.id = id;
            this.key = key;
            this.value = value;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    @Test
    public void test3() {
        String str = "test";
        String str1 = "test";
        /*
            int hashCode(Object o)
            生成指定对象的hashcode，如果为null则返回0
            实际上就是调用o.hashcode
         */
        assertThat(Objects.hashCode(str) == Objects.hashCode(str1), is(true));
    }

    @Test
    public void test4() {
        Bean bean = new Bean(1, "key", "value");
//        System.out.println(Objects.hash(bean.getId(), bean.getKey(), bean.getValue()));
        Bean bean2 = new Bean(1, "key", "value");
//        System.out.println(Objects.hash(bean2.getId(), bean2.getKey(), bean2.getValue()));
        /*
            int hash(Object... values)
            根据给定的对象数组，生成hashcode
            这个方法可以用在bean对象的hashCode方法中，将对象的所以变量当作参数
         */
        assertThat(Objects.hash(bean.getId(), bean.getKey(), bean.getValue()) == Objects.hash(bean2.getId(), bean2.getKey(), bean2.getValue()), is(true));
    }

    @Test
    public void test5() {
        Bean bean = new Bean(1, "key", "value");
        /*
            String toString(Object o)
            实际上调用的就是String.valueOf(o)
         */
        String str = Objects.toString(bean);
        System.out.println(str);
        str = Objects.toString(null);
        System.out.println(str);

        /*
            String toString(Object o, String nullDefault)
            如果为null，则返回给的默认值
         */
        str = Objects.toString(null, "test");
        assertThat(str, is("test"));
    }

    @Test
    public void test6() {
        /*
            int compare(T a, T b, Comparator<? super T> c)
            根据给定的Comparator 规则 比较a，b
         */
        int result = Objects.compare(1, 2, (a, b) -> b - a);
        assertThat(result < 0, is(false));
    }

    @Test(expected = NullPointerException.class)
    public void test7() {
        /*
            T requireNonNull(T obj)
            如果非null，则原样返回
            否则抛空指针异常
         */
        assertThat(Objects.requireNonNull("test"), is("test"));
        Objects.requireNonNull(null);
    }

    @Test(expected = NullPointerException.class)
    public void test8() {
        /*
            requireNonNull(T obj, String message)
            如果非null，则原样返回
            否则抛空指针异常,附带错误信息
         */
        Objects.requireNonNull(null, "errorMsg");
    }

    @Test(expected = NullPointerException.class)
    public void test9() {
        /*
            requireNonNull(T obj, Supplier<String> messageSupplier)
            如果非null，则原样返回
            否则抛空指针异常,附带错误信息
            这个信息根据Supplier接口获取
         */
        Objects.requireNonNull(null, () -> "errorMsg");
    }

    @Test
    public void test10() {
        /*
            boolean isNull(Object obj)
            判断是否为null
         */
        assertThat(Objects.isNull(null), is(true));
        assertThat(Objects.isNull("test"), is(false));
        /*
            boolean nonNull(Object obj)
            判断是否非空
         */
        assertThat(Objects.nonNull(null), is(false));
        assertThat(Objects.nonNull("test"), is(true));
    }
}
