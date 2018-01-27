package com.pro.tools.core.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 反射工具类
 * 
 * @author vurtne
 * 
 * @param <T>
 */
public class ReflectUtil {

	public static <T> void setProperty(T t, String fieldName, Object value) {

		try {

			Field field = t.getClass().getDeclaredField(fieldName);

			field.set(t, value);

		} catch (SecurityException e) {

			e.printStackTrace();

		} catch (NoSuchFieldException e) {

			e.printStackTrace();

		} catch (IllegalArgumentException e) {

			e.printStackTrace();

		} catch (IllegalAccessException e) {

			e.printStackTrace();

		}

	}

	/**
	 * 根据对象和对象属性名称取得属性值
	 * 
	 * @param <Property>
	 *            属性类型
	 * @param t
	 *            对象
	 * @param property
	 *            属性名称
	 * @return 返回属性值
	 */
	public static <T> Object getProperty(T t, String property) {

		Object value = null;
		
		String subProperty=null;

		try {

			if (!property.equals("serialVersionUID")) {
				
				if(property.indexOf(".")>-1){
				
					subProperty=property.substring(property.indexOf(".")+1);
					
					property=property.substring(0,property.indexOf("."));
					
				}

				Field field = t.getClass().getDeclaredField(property);

				value = t.getClass().getMethod(getter(field))
						.invoke(t, new Object[0]);
				
				if(subProperty==null||value==null){
					
					return value;
					
				}else{
					
					return getProperty(value,subProperty);
					
				}

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return null;

	}

	/**
	 * 取得所有属性和属性值
	 * 
	 * @param t
	 * @return
	 */
	public <T> Map<String, Object> getProperties(T t) {

		Map<String, Object> properties = new HashMap<String, Object>();

		Field[] fields = t.getClass().getDeclaredFields();

		for (Field field : fields) {

			try {

				Object value = t.getClass().getMethod(getter(field))
						.invoke(t, new Object[0]);

				properties.put(field.getName(), value);

			} catch (Exception e) {

				e.printStackTrace();

			}

		}

		return properties;

	}

	/**
	 * 这里保证第三个及以后个参数不能为空。即构造方法参数必须一一对应
	 * 
	 * @param <T>
	 * @param t
	 *            父类或父接口
	 * @param className
	 *            类名
	 * @param initargs
	 *            构造器的参数
	 * @return 实体类对象
	 */
	public static <T> T createObject(Class<T> t, Map<String, Object> map) {

		T instance = null;

		try {

			instance = t.newInstance();

		} catch (InstantiationException e) {

			e.printStackTrace();

		} catch (IllegalAccessException e) {

			e.printStackTrace();

		}

		Field[] fields = t.getDeclaredFields();

		for (Field field : fields) {

			try {

				Method method = t.getDeclaredMethod(("set" + new StringUtil()
						.upperCaseFirstChar(field.getName())), field.getType());

				Object parameter = null;

				if (("java.lang.Integer".equals(field.getType().getName())
						| "java.lang.Double".equals(field.getType().getName()) | "java.lang.Float"
						.equals(field.getType().getName()))
						&& map.get(field.getName()) != null) {

					parameter = createConstructObject(field.getType(),
							map.get(field.getName()));

				} else {

					parameter = map.get(field.getName());

				}

				method.invoke(instance, parameter);

			} catch (SecurityException e) {

				e.printStackTrace();

			} catch (NoSuchMethodException e) {

				e.printStackTrace();

			} catch (IllegalArgumentException e) {

				e.printStackTrace();

			} catch (IllegalAccessException e) {

				e.printStackTrace();

			} catch (InvocationTargetException e) {

				e.printStackTrace();

			}

		}

		return instance;

	}

	/**
	 * 这里保证第三个及以后个参数不能为空。即构造方法参数必须一一对应
	 * 
	 * @param <T>
	 * @param t
	 *            父类或父接口
	 * @param className
	 *            类名
	 * @param initargs
	 *            构造器的参数
	 * @return 实体类对象
	 */
	public static <T> T createConstructObject(Class<T> t, Object... initargs) {

		if (initargs == null) {

			return createObject(t, t.getName(), null, null);

		}

		Class<?>[] parameterTypes = new Class<?>[initargs.length];

		for (int i = 0; i < initargs.length; i++) {

			parameterTypes[i] = initargs[i].getClass();

		}

		return createObject(t, t.getName(), parameterTypes, initargs);

	}

	/**
	 * 
	 * @param <T>
	 * @param t
	 * @param className
	 * @param parameterTypes
	 * @param initargs
	 * @return
	 */
	public static <T> T createObject(Class<T> t, String className,
			Class<?>[] parameterTypes, Object[] initargs) {

		try {

			Class<?> instanceClass = Class.forName(className);

			if (parameterTypes != null && initargs != null) {

				if (parameterTypes.length == initargs.length) {

					Constructor<?> constructor = instanceClass
							.getConstructor(parameterTypes);

					return t.cast(constructor.newInstance(initargs));

				}

			} else {

				return t.cast(instanceClass.newInstance());

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return null;

	}

	public static String getter(Field field) {

		if (field.getType().getName().equals("boolean")) {
			
			if(field.getType().getName().startsWith("is"))

				return field.getName();
			
			else
				
				return "is"+new StringUtil().upperCaseFirstChar(field.getName());

		} else {

			return "get" + new StringUtil().upperCaseFirstChar(field.getName());

		}

	}

	public String setter(String str) {

		return "set" + new StringUtil().upperCaseFirstChar(str);

	}

}
