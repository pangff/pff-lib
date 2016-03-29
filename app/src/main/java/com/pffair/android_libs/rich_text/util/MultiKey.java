package com.pffair.android_libs.rich_text.util;

import java.util.Arrays;

public class MultiKey {
  private Object[] keys;
  private int hashCode;

  public MultiKey(Object key1, Object key2) {
    this(new Object[] {key1, key2}, false);
  }

  public MultiKey(Object key1, Object key2, Object key3) {
    this(new Object[] {key1, key2, key3}, false);
  }

  public MultiKey(Object key1, Object key2, Object key3, Object key4) {
    this(new Object[] {key1, key2, key3, key4}, false);
  }

  public MultiKey(Object key1, Object key2, Object key3, Object key4, Object key5) {
    this(new Object[] {key1, key2, key3, key4, key5}, false);
  }

  public MultiKey(Object[] keys) {
    this(keys, true);
  }

  public MultiKey(Object[] keys, boolean makeClone) {
    init(keys, makeClone);
  }

  private void init(Object[] keys, boolean makeClone) {
    if (keys == null) {
      throw new IllegalArgumentException("The array of keys must not be null");
    }
    if (makeClone)
      this.keys = ((Object[]) keys.clone());
    else {
      this.keys = keys;
    }

    int total = 0;
    for (int i = 0; i < keys.length; i++) {
      if (keys[i] != null) {
        total ^= keys[i].hashCode();
      }
    }
    this.hashCode = total;
  }

  public void reset(Object key1, Object key2) {
    init(new Object[] {key1, key2}, false);
  }

  public Object[] getKeys() {
    return (Object[]) this.keys.clone();
  }

  public Object getKey(int index) {
    return this.keys[index];
  }

  public int size() {
    return this.keys.length;
  }

  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if ((other instanceof MultiKey)) {
      MultiKey otherMulti = (MultiKey) other;
      return Arrays.equals(this.keys, otherMulti.keys);
    }
    return false;
  }

  public int hashCode() {
    return this.hashCode;
  }

  public String toString() {
    return "MultiKey" + Arrays.asList(this.keys).toString();
  }
}
