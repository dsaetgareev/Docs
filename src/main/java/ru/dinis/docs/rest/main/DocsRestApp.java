package ru.dinis.docs.rest.main;


import ru.dinis.docs.rest.service.EmplRestService;
import ru.dinis.docs.rest.service.FirmRestService;
import ru.dinis.docs.rest.service.SubdivRestService;
import ru.dinis.docs.rest.service.TaskRestService;

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
        Set<Class<?>> classes = new HashSet<>();
        classes.add(FirmRestService.class);
        classes.add(SubdivRestService.class);
        classes.add(EmplRestService.class);
        classes.add(TaskRestService.class);

        return classes;
    }
}
