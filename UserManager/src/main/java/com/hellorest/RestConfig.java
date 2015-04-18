package com.hellorest;

import resources.Resource;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author Jason
 */
@ApplicationPath("v1")
public class RestConfig extends Application{
    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<Class<?>>();
        // Register resources
        classes.add(Resource.class);
        return classes;        
    }
}
