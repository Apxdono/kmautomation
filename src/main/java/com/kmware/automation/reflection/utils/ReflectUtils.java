package com.kmware.automation.reflection.utils;

import com.kmware.automation.elements.base.JQElement;
import com.kmware.automation.jquery.jQuery;
import com.kmware.automation.jquery.jQueryFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created with IntelliJ IDEA.
 * User: Oleg
 * Date: 26.07.13 13:31
 */
public class ReflectUtils {

    public static String getExtraSelector(Class<?> klazz){
        JQElement annot = klazz.getAnnotation(JQElement.class);
        if(annot == null) return "";
        return annot.extraSelector();
    }

    public static <E> E getAs(jQuery jq , Class<E> klazz){
        E result = null;
        try {
            Class<?>[] argsTypes = new Class[]{jQuery.class};
            Constructor constr = klazz.getDeclaredConstructor(argsTypes);
            Object o = constr.newInstance(jq);
            result = (E) o;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static <E> E getAs(jQueryFactory fact, long ref, String selector, Class<E> klazz){
        E result = null;
        try {
            Class<?>[] argsTypes = new Class[]{jQueryFactory.class,long.class,String.class};
            Constructor constr = klazz.getDeclaredConstructor(argsTypes);
            Object o = constr.newInstance(fact,ref,selector);
            result = (E) o;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return result;
    }



}
