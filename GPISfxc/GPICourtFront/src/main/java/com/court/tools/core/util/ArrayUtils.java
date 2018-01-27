package com.court.tools.core.util;

import java.lang.reflect.Array;

public abstract class ArrayUtils extends org.apache.commons.lang.ArrayUtils
{
  public static Object[][] dimTransform(Object[][] obj)
  {
    if ((obj == null) || (obj.length <= 0))
    {
      return null;
    }
    Object[][] newArr = new Object[obj[0].length][obj.length];
    for (int i = 0; i < newArr.length; i++)
    {
      for (int j = 0; j < obj.length; j++)
      {
        newArr[i][j] = obj[j][i];
      }
    }
    return newArr;
  }

  public static String[][] dimTransform(String[][] obj)
  {
    if ((obj == null) || (obj.length <= 0))
    {
      return null;
    }
    String[][] newArr = new String[obj[0].length][obj.length];
    for (int i = 0; i < newArr.length; i++)
    {
      for (int j = 0; j < obj.length; j++)
      {
        newArr[i][j] = obj[j][i];
      }
    }
    return newArr;
  }

  public static int[] sort(int[] a)
  {
    if ((a == null) || (a.length <= 0))
    {
      return null;
    }
    for (int i = 0; i < a.length; i++)
    {
      for (int j = 0; j < a.length; j++)
      {
        if (a[i] >= a[j])
          continue;
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
      }
    }

    return a;
  }

  public static String[] sort(String[] a)
  {
    if ((a == null) || (a.length <= 0))
    {
      return null;
    }
    for (int i = 0; i < a.length; i++)
    {
      for (int j = 0; j < a.length; j++)
      {
        if (new Integer(a[i]).intValue() >= new Integer(a[j]).intValue())
          continue;
        String tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
      }
    }

    return a;
  }

  public static String toClearString(Object[] arr)
  {
    String result = toString(arr);
    result = result.substring(1, result.length() - 1);
    return result;
  }

  public static String toCustomString(Object[] array, String split)
  {
    StringBuffer str = new StringBuffer();
    for (int i = 0; i < array.length; i++)
    {
      str.append(array[i]);
      if (i < array.length - 1)
        str.append(".");
    }
    return str.toString();
  }

  public static Object peek(Object[] array)
  {
    if (isEmpty(array))
    {
      return null;
    }

    return array[(array.length - 1)];
  }

  public static <T> T[] add(T[] array, T object, Class<T[]> arrayType)
  {
    if (object == null)
    {
      return array;
    }
    if (array == null)
    {
      array = (T[])Array.newInstance(arrayType.getComponentType(), 0);
    }

    Object[] returnArray = (Object[])Array.newInstance(arrayType.getComponentType(), array.length + 1);
    System.arraycopy(array, 0, returnArray, 0, array.length);
    returnArray[array.length] = object;
    return (T[]) returnArray;
  }

  public static <T> T[] addAll(T[] array, T[] arrayToAdd, Class<T[]> arrayType)
  {
    if (arrayToAdd == null)
    {
      return array;
    }
    if (array == null)
    {
      return arrayToAdd;
    }

    Object[] returnArray = (Object[])Array.newInstance(arrayType.getComponentType(), array.length + arrayToAdd.length);
    System.arraycopy(array, 0, returnArray, 0, array.length);
    System.arraycopy(arrayToAdd, 0, returnArray, array.length, arrayToAdd.length);
    return (T[]) returnArray;
  }

  public static int indexOf(byte[] source, byte[] target)
  {
    return indexOf(source, 0, source.length, target, 0, target.length, 0);
  }

  public static int indexOf(byte[] source, byte[] target, int fromIndex)
  {
    return indexOf(source, 0, source.length, target, 0, target.length, fromIndex);
  }

  public static int indexOf(byte[] s, int so, int sc, byte[] t, int to, int tc, int fi)
  {
    if (fi >= sc)
    {
      return tc == 0 ? sc : -1;
    }
    if (fi < 0)
    {
      fi = 0;
    }
    if (tc == 0)
    {
      return fi;
    }

    byte first = t[to];
    int i = so + fi;
    int max = so + (sc - tc);
    for (; ; )
    {
      i++;

      if ((i > max) || (s[i] == first))
      {
        if (i > max)
        {
          return -1;
        }
        int j = i + 1;
        int end = j + tc - 1;
        int k = to + 1;
        while (true)
        {
          if (s[(j++)] != t[(k++)])
          {
            i++;
          }
          else if (j >= end)
          {
            return i - so;
          }
        }
      }
    }
  }

  public static byte[] replace(byte[] source, byte[] target, byte[] replacement)
  {
    if ((source == null) || (target == null))
    {
      return source;
    }
    if (source.length < target.length)
    {
      return source;
    }
    if (replacement == null)
    {
      replacement = 
        new byte[0];
    }
    int index = indexOf(source, target);
    if (index != -1)
    {
      byte[] tmp = new byte[source.length - target.length + replacement.length];
      System.arraycopy(source, 0, tmp, 0, index);
      System.arraycopy(replacement, 0, tmp, index, replacement.length);
      System.arraycopy(source, index + target.length, tmp, index + replacement.length, 
        source.length - index - target.length);
      return replace(tmp, target, replacement);
    }

    return source;
  }

  private static int compareArray(Double[] a, Double[] b)
  {
    if (a[0].doubleValue() < b[0].doubleValue()) return -1;
    if (a[0].doubleValue() > b[0].doubleValue()) return 1;

    if (a[1].doubleValue() < b[1].doubleValue()) return -1;
    if (a[1].doubleValue() > b[1].doubleValue()) return 1;

    return 0;
  }

  private static void swap(Double[][] list, int a, int b)
  {
    Double[] alist = (Double[])list[a].clone();
    for (int i = 0; i < list[b].length; i++)
    {
      list[a][i] = list[b][i];
    }
    for (int i = 0; i < alist.length; i++)
    {
      list[b][i] = alist[i];
    }
  }

  public static Double[][] sortDoubleArray(Double[][] sortArray)
  {
    int len = sortArray.length;

    for (int i = 0; i < len; i++)
    {
      int min = i;
      for (int j = i + 1; j < len; j++)
      {
        int comp = compareArray(sortArray[min], sortArray[j]);
        if (comp != 1) continue; min = j;
      }
      swap(sortArray, i, min);
    }
    return sortArray;
  }
}
