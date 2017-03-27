package com.luolei.jdk.utils;

import static com.luolei.jdk.utils.PreConditions.checkNotNull;

import java.io.IOException;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Joiner {
  /**
   * 得到一个Joiner实例，分隔符为	separator
   * @param separator	分隔符
   * @return	Joiner实例
   */
  public static Joiner on(String separator) {
    return new Joiner(separator);
  }

  /**
   * 得到一个Joiner实例，分隔符为	separator
   * @param separator	分隔符
   * @return	Joiner实例
   */
  public static Joiner on(char separator) {
    return new Joiner(String.valueOf(separator));
  }
  
  private final String separator;

  private Joiner(String separator) {
    this.separator = checkNotNull(separator);
  }

  private Joiner(Joiner prototype) {
    this.separator = prototype.separator;
  }

  /**
   * 连接集合里面的元素，用分隔符	separator 连接
   * @param appendable	StringBuilder 或者 StringBuffer
   * @param parts	集合
   * @return	实现Appendable 的类 如	StringBuilder 或者 StringBuffer
   * @throws IOException
   */
  public <A extends Appendable> A appendTo(A appendable, Iterable<?> parts) throws IOException {
    return appendTo(appendable, parts.iterator());
  }

  /**
   * 连接集合里面的元素，用分隔符	separator 连接
   * @param appendable	StringBuilder 或者 StringBuffer
   * @param parts	遍历
   * @return	实现Appendable 的类 如	StringBuilder 或者 StringBuffer
   * @throws IOException
   */
  public <A extends Appendable> A appendTo(A appendable, Iterator<?> parts) throws IOException {
    checkNotNull(appendable);
    if (parts.hasNext()) {
      appendable.append(toString(parts.next()));
      while (parts.hasNext()) {
        appendable.append(separator);
        appendable.append(toString(parts.next()));
      }
    }
    return appendable;
  }

  /**
   * 连接集合里面的元素，用分隔符	separator 连接
   * @param appendable	StringBuilder 或者 StringBuffer
   * @param parts	数组
   * @return	实现Appendable 的类 如	StringBuilder 或者 StringBuffer
   * @throws IOException
   */
  public final <A extends Appendable> A appendTo(A appendable, Object[] parts) throws IOException {
    return appendTo(appendable, Arrays.asList(parts));
  }

  /**
   * 连接集合里面的元素，用分隔符	separator 连接
   * @param appendable	StringBuilder 或者 StringBuffer
   * @param first	第一个参数
   * @param second	第二个参数
   * @param rest	可变参数
   * @return	实现Appendable 的类 如	StringBuilder 或者 StringBuffer
   * @throws IOException
   */
  public final <A extends Appendable> A appendTo(
      A appendable, Object first, Object second, Object... rest)
          throws IOException {
    return appendTo(appendable, iterable(first, second, rest));
  }

  /**
   * 连接集合里面的元素，用分隔符	separator 连接
   * @param builder	StringBuilder
   * @param parts	集合
   * @return	StringBuilder
   */
  public final StringBuilder appendTo(StringBuilder builder, Iterable<?> parts) {
    return appendTo(builder, parts.iterator());
  }

  /**
   * 连接集合里面的元素，用分隔符	separator 连接
   * @param builder	StringBuilder
   * @param parts	集合	
   * @return	StringBuilder
   */
  public final StringBuilder appendTo(StringBuilder builder, Iterator<?> parts) {
    try {
      appendTo((Appendable) builder, parts);
    } catch (IOException impossible) {
      throw new AssertionError(impossible);
    }
    return builder;
  }

  /**
   * 
   * @param builder
   * @param parts
   * @return
   */
  public final StringBuilder appendTo(StringBuilder builder, Object[] parts) {
    return appendTo(builder, Arrays.asList(parts));
  }

  /**
   * 
   * @param builder
   * @param first
   * @param second
   * @param rest
   * @return
   */
  public final StringBuilder appendTo(
      StringBuilder builder, Object first, Object second, Object... rest) {
    return appendTo(builder, iterable(first, second, rest));
  }

  /**
   * 
   * @param parts
   * @return
   */
  public final String join(Iterable<?> parts) {
    return join(parts.iterator());
  }

  /**
   * 
   * @param parts
   * @return
   */
  public final String join(Iterator<?> parts) {
    return appendTo(new StringBuilder(), parts).toString();
  }

  /**
   * 
   * @param parts
   * @return
   */
  public final String join(Object[] parts) {
    return join(Arrays.asList(parts));
  }

  /**
   * 
   * @param first
   * @param second
   * @param rest
   * @return
   */
  public final String join(Object first, Object second, Object... rest) {
    return join(iterable(first, second, rest));
  }

  /**
   * 
   * @param nullText
   * @return
   */
  public Joiner useForNull(final String nullText) {
    checkNotNull(nullText);
    return new Joiner(this) {
      @Override CharSequence toString(Object part) {
        return (part == null) ? nullText : Joiner.this.toString(part);
      }

      @Override public Joiner useForNull(String nullText) {
        throw new UnsupportedOperationException("already specified useForNull");
      }

      @Override public Joiner skipNulls() {
        throw new UnsupportedOperationException("already specified useForNull");
      }
    };
  }

  /**
   * 
   * @return
   */
  public Joiner skipNulls() {
    return new Joiner(this) {
      @Override public <A extends Appendable> A appendTo(A appendable, Iterator<?> parts)
          throws IOException {
        checkNotNull(appendable, "appendable");
        checkNotNull(parts, "parts");
        while (parts.hasNext()) {
          Object part = parts.next();
          if (part != null) {
            appendable.append(Joiner.this.toString(part));
            break;
          }
        }
        while (parts.hasNext()) {
          Object part = parts.next();
          if (part != null) {
            appendable.append(separator);
            appendable.append(Joiner.this.toString(part));
          }
        }
        return appendable;
      }

      @Override public Joiner useForNull(String nullText) {
        throw new UnsupportedOperationException("already specified skipNulls");
      }

      @Override public MapJoiner withKeyValueSeparator(String kvs) {
        throw new UnsupportedOperationException("can't use .skipNulls() with maps");
      }
    };
  }

  /**
   * 
   * @param keyValueSeparator
   * @return
   */
  public MapJoiner withKeyValueSeparator(String keyValueSeparator) {
    return new MapJoiner(this, keyValueSeparator);
  }

 /**
  * 
  * @author luolei
  * @Date 2015年8月12日
  */
  public static final class MapJoiner {
    private final Joiner joiner;
    private final String keyValueSeparator;

    private MapJoiner(Joiner joiner, String keyValueSeparator) {
      this.joiner = joiner; // only "this" is ever passed, so don't checkNotNull
      this.keyValueSeparator = checkNotNull(keyValueSeparator);
    }

    /**
     * 
     * @param appendable
     * @param map
     * @return
     * @throws IOException
     */
    public <A extends Appendable> A appendTo(A appendable, Map<?, ?> map) throws IOException {
      return appendTo(appendable, map.entrySet());
    }

    /**
     * 
     * @param builder
     * @param map
     * @return
     */
    public StringBuilder appendTo(StringBuilder builder, Map<?, ?> map) {
      return appendTo(builder, map.entrySet());
    }

    /**
     * 
     * @param map
     * @return
     */
    public String join(Map<?, ?> map) {
      return join(map.entrySet());
    }

    /**
     * 
     * @param appendable
     * @param entries
     * @return
     * @throws IOException
     */
    public <A extends Appendable> A appendTo(A appendable, Iterable<? extends Entry<?, ?>> entries)
        throws IOException {
      return appendTo(appendable, entries.iterator());
    }

    /**
     * 
     * @param appendable
     * @param parts
     * @return
     * @throws IOException
     */
    public <A extends Appendable> A appendTo(A appendable, Iterator<? extends Entry<?, ?>> parts)
        throws IOException {
      checkNotNull(appendable);
      if (parts.hasNext()) {
        Entry<?, ?> entry = parts.next();
        appendable.append(joiner.toString(entry.getKey()));
        appendable.append(keyValueSeparator);
        appendable.append(joiner.toString(entry.getValue()));
        while (parts.hasNext()) {
          appendable.append(joiner.separator);
          Entry<?, ?> e = parts.next();
          appendable.append(joiner.toString(e.getKey()));
          appendable.append(keyValueSeparator);
          appendable.append(joiner.toString(e.getValue()));
        }
      }
      return appendable;
    }

    /**
     * 
     * @param builder
     * @param entries
     * @return
     */
    public StringBuilder appendTo(StringBuilder builder, Iterable<? extends Entry<?, ?>> entries) {
      return appendTo(builder, entries.iterator());
    }

    /**
     * 
     * @param builder
     * @param entries
     * @return
     */
    public StringBuilder appendTo(StringBuilder builder, Iterator<? extends Entry<?, ?>> entries) {
      try {
        appendTo((Appendable) builder, entries);
      } catch (IOException impossible) {
        throw new AssertionError(impossible);
      }
      return builder;
    }

    /**
     * 
     * @param entries
     * @return
     */
    public String join(Iterable<? extends Entry<?, ?>> entries) {
      return join(entries.iterator());
    }

    /**
     * 
     * @param entries
     * @return
     */
    public String join(Iterator<? extends Entry<?, ?>> entries) {
      return appendTo(new StringBuilder(), entries).toString();
    }

    /**
     * 
     * @param nullText
     * @return
     */
    public MapJoiner useForNull(String nullText) {
      return new MapJoiner(joiner.useForNull(nullText), keyValueSeparator);
    }
  }

  CharSequence toString(Object part) {
    checkNotNull(part);  // checkNotNull for GWT (do not optimize).
    return (part instanceof CharSequence) ? (CharSequence) part : part.toString();
  }

  private static Iterable<Object> iterable(
      final Object first, final Object second, final Object[] rest) {
    checkNotNull(rest);
    return new AbstractList<Object>() {
      @Override public int size() {
        return rest.length + 2;
      }

      @Override public Object get(int index) {
        switch (index) {
          case 0:
            return first;
          case 1:
            return second;
          default:
            return rest[index - 2];
        }
      }
    };
  }
}

