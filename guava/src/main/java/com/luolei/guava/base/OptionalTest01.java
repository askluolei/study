package com.luolei.guava.base;

import org.junit.Test;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

/**
 * 由于JDK1.8 已经支持Optional用法了
 * 因此下面测试的都是基于JDK1.8的 Optional对象
 *
 * Created by 罗雷 on 2017/3/7.
 */
public class OptionalTest01 {

    @Test
    public void test1() {
        /*
            Optional 对象只能通过Optional对象的静态方法生成
            Optional.empty();  生成一个包含空的Optional
            Optional.of(value); 生成一个包含value的Optional， value不能为null
            Optional.ofNullable(value);生成一个包含value的Optional， value可能为null
         */
        Optional<Integer> possible = Optional.of(5);

        /*
             Optional 对象方法
             isPresent(); 用来判断Optional内的对象是否有值
         */
        assertThat(possible.isPresent(), is(true));

        /*
            Optional 对象方法
            get(); 用来获取值
            在get之前，你应该使用isPresent()方法判断是否有值，如果为null，又调用了get，则会抛异常
         */
        assertThat(possible.get(), is(5));
    }

    @Test
    public void test2() {
        Optional<Integer> possible = Optional.of(5);
        /*
            Optional 对象方法
            ifPresent(Consumer<? super T> consumer)
            如果存在值，则执行传入的Consumer接口
            这是一个函数式接口，只有一个方法，参数一个，没有返回值
         */
        possible.ifPresent(System.out::println);

        /*
            Consumer 接口可以是一个链式调用
            构造一个Consumer对象，通过andThen(Consumer<? super T> consumer)
            会有一个链式的任务，会一次将最初的参数分别给这些任务执行accept方法
            这个的参数就是Optional内部的value
         */
        Consumer<Integer> consumer = value -> System.out.println(value);
        possible.ifPresent(consumer.andThen(System.out::println)
                                   .andThen(System.out::println)
        );
    }

    @Test
    public void test3() {
        Optional<Integer> possible = Optional.of(5);
        /*
            Optional 对象方法
            filter(Predicate<? super T> predicate)
            Predicate 是一个函数式接口 有一个
            boolean test(T value) 方法，代表过滤条件
            如果条件为true，则返回Optional本身，否则，返回Optional.emtpy()
            如果传如的条件就为null，则会抛异常
            如果内部的value为null，则返回Optional.emtpy()
         */
        Optional<Integer> otherPossible = possible.filter(v -> v >= 5);
        assertThat(otherPossible.isPresent(), is(true));

        /*
            Predicate 接口也可以进行组合
            and  &&  与
            or   ||  或
            negate ！非
         */
        Predicate<Integer> predicate1 = v -> v >= 5;
        Predicate<Integer> predicate2 = v -> v > 0;
        Predicate<Integer> predicate3 = v -> v % 3 == 2;
        Predicate<Integer> predicate4 = v -> v < 0;
        assertThat(possible.filter(predicate1
                .and(predicate2)
                .and(predicate3)
                .and(predicate4.negate()))
                .isPresent(), is(true));
    }

    @Test
    public void test4() {
        Optional<Integer> possible = Optional.of(5);
        /*
            Optional 对象方法
            map(Function<? super T, ? extends U> mapper)
            Function 是一个函数式接口 有一个
            R apply(T t); 方法
            作用就是根据输入的参数，经过处理后，转换成另一个对象（也可以是同对象）
            这下面就是将int 换成了 string
            当然前提是传入的 Function 不能为null，否则会抛异常
            如果Optional.isPresent() == false  则会返回Optional.empty()
         */
        Optional<String> other = possible.map(v -> String.valueOf(v) + "haha");
        assertThat(other.isPresent(), is(true));
        assertThat(other.get(), is("5haha"));

        Function<Integer, String> int2string = v -> String.valueOf(++v);
        Function<String, Long> string2long = v -> Long.valueOf(v);
        Function<Integer, Integer> int2int = v -> ++v;
        /*
            Function 接口也支持一堆Function组合
            compose(Function<? super V, ? extends T> before) 代表在此Function前执行
            andThen(Function<? super R, ? extends V> after)  代表在此Function后执行

            下面的组合 执行的顺序是 int2int int2string string2long
            所以最后获得的对象类型是Optional<Long>
         */
        Optional<Long> longValue = possible.map(
                int2string.compose(int2int).andThen(string2long)
        );
        assertThat(longValue.isPresent(), is(true));
        assertThat(longValue.get(), is(7L));
    }

}
