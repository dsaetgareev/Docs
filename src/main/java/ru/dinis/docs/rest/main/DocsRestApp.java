package ru.dinis.docs.rest.main;


import ru.dinis.docs.rest.service.FirmRestService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Create by dinis of 24.04.18.
 */
@ApplicationPath("docs")
public class DocsRestApp extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        HashSet<Class<?>> classes = new HashSet<>();
        classes.add(FirmRestService.class);
        return classes;
    }
}
