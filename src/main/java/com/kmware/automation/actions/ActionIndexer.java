package com.kmware.automation.actions;

import net.sf.extcos.ComponentQuery;
import net.sf.extcos.ComponentScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: oleg
 * Date: 7/22/13
 * Time: 3:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class ActionIndexer {

    protected Map<String,ActionWrap> actions = new HashMap<String, ActionWrap>();
    protected Logger log = LoggerFactory.getLogger(getClass());

    public Map<String,ActionWrap> scanActions(final String[] packages){
        Set<Class<?>> classes = new HashSet<Class<?>>();

        ComponentScanner scanner = new ComponentScanner();
        classes =  scanner.getClasses(new ComponentQuery() {
            @Override
            protected void query() {
                select().
                        from(packages).returning(allAnnotatedWith(Action.class));
            }
        });
        if(classes==null) return actions;
        for (Class<?> aClass : classes) {
            register(aClass);
        }
        log.info("Actions indexed: {}",actions.size());
        return actions;
    }

    public Map<String, ActionWrap> getActions() {
        return actions;
    }

    public void setActions(Map<String, ActionWrap> actions) {
        this.actions = actions;
    }

    public IAction getAction(String id){
        ActionWrap pw = actions.get(id);
        if(pw == null) return null;
        return pw.getAction();
    }

    public boolean register(Object target) {
        if (target == null) {
            log.error("Nothing to register");
            return false;
        }
        if (target instanceof Class<?>) {
            Class<?> c = (Class<?>) target;
            Action annot = c.getAnnotation(Action.class);
            if (annot == null || !Arrays.asList(c.getInterfaces()).contains(IAction.class)) {
                log.error("The class is not marked as @Action or doesn't implement IAction interface.");
                return false;
            }
            if (actions.containsKey(annot.id())){
                log.error("Cannot register action. Action is already registered. Action id: {}",annot.id());
                return false;
            }
            actions.put(annot.id(), new ActionWrap(c));
            return true;
        } else if (target instanceof IAction) {
            IAction action = (IAction) target;
            if (actions.containsKey(action.getId())){
                log.error("Cannot register action. Action is already registered. Action id: {}",action.getId());
                return false;
            }
            actions.put(action.getId(),new ActionWrap(action.getClass(),action));
            return true;
        }
        log.error("The object is not marked as @Action or doesn't implement IAction interface.");
        return false;
    }

    public class ActionWrap{
        private Class<?> actionClass;
        private IAction instance;
        ActionWrap(Class<?> klazz){
            actionClass = klazz;
            instance = null;
        }

        ActionWrap(Class<?> klazz, IAction inst){
            actionClass = klazz;
            instance = inst;
        }

        public IAction getAction(){
            if(instance==null){
                try {
                    Object buf = actionClass.newInstance();
                    if(buf instanceof IAction){
                        instance = (IAction) buf;
                    }
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            instance.init();
            return instance;
        }
    }
}
