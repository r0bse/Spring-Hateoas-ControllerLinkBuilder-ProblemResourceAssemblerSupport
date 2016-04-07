package hello;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * assemble all needed information to build a greeting resource
 */
@Service
public class GreetingAssembler extends ResourceAssemblerSupport<GreetingEntity, GreetingResource> {

    @Autowired
    private BeanFactory beanFactory;

    /**
     * constructor must call super to define which controller to link to
     * and to define the resource
     */
    public GreetingAssembler() {
        super(GreetingController.class, GreetingResource.class);
    }


    /**
     * based on configuration, build the resource
     *
     * @param entity
     * @return
     */
    @Override
    public GreetingResource toResource(GreetingEntity entity) {

        GreetingResource resource = createResourceWithId( entity.getId(),
                                                          entity);

        return resource;
    }

    /**
     * same as the method in ResourceAssemblerSupport, overwritten to evaluate the problem
     *
     * @param id
     * @param entity
     * @param parameters
     * @return
     */
    @Override
    protected GreetingResource createResourceWithId(Object id, GreetingEntity entity, Object... parameters) {

        Assert.notNull(entity);
        Assert.notNull(id);

        GreetingResource instance = instantiateResource(entity);
        ControllerLinkBuilder builder = linkTo(GreetingController.class, parameters);
        builder = builder.slash(id);
        Link link = builder.withSelfRel();
        instance.add(link);
        return instance;
    }

    /**
     * overwrite the default method to be able to call the class from aplicationcontext with params
     *
     * @param entity
     * @return
     */
    @Override
    protected GreetingResource instantiateResource(GreetingEntity entity) {

        return beanFactory.getBean(GreetingResource.class, entity.getContent());
    }
}

