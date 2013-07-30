package com.kmware.automation.reflection.utils;

import com.kmware.automation.elements.base.JQElement;
import com.kmware.automation.jquery.jQuery;
import com.kmware.automation.jquery.jQueryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;

/**
 * Created with IntelliJ IDEA.
 * User: Oleg
 * Date: 26.07.13 13:31
 */
public class ReflectUtils {
    static final Logger log = LoggerFactory.getLogger(ReflectUtils.class);

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
        } catch (Exception e) {
            log.error("Problem when initializing new object from jquery object.",e);
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
        } catch (Exception e) {
            log.error("Problem when initialising new object from parameters.",e);
        }
        return result;
    }
}
