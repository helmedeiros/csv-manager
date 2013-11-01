package br.com.helmed.csvmanager.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Utility class to help with the Generic works. 
 * 
 * @version 1.0 April 29, 2011
 * @author helmedeiros
 *
 */
public class ReflectionUtils {
	
	/**
	 * Returns the generic parameterized' type for the informed's object's super class.
	 *
	 * @param obj - the object from who want to know the parameterized' type
	 * @return the parameterized' type
	 */
	@SuppressWarnings("unchecked")
	public static <T> Class<T> getParamType(Object obj){
		Class<T> classe = null;
		Type type = obj.getClass().getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			Type[] arguments = ((ParameterizedType)type).getActualTypeArguments();
			for (Type argument : arguments){ 
				if (argument instanceof Class) {
					classe = (Class<T>) argument;								
				}
			}
		}
		return classe;
	}
	
	/**
	 * Generate an instance from an certain class type.
	 *
	 * @param classe - the class from which is need the instance.
	 * @return the new instance object.
	 */
	public static <T> T instantiate(Class<T> classe) {
		try{
			return (T) classe.newInstance();
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
