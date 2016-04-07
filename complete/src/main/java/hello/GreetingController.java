package hello;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@ExposesResourceFor(GreetingResource.class)
@RequestMapping("/{var}")
public class GreetingController {

    @Autowired
    private BeanFactory beanFactory;
    @Autowired
    private GreetingAssembler greetingAssembler;

    private static final String TEMPLATE = "Hello, %s!";

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping("/greeting")
    public GreetingResource greeting( @PathVariable("var") String var ) {

        GreetingEntity greetingEntity = beanFactory.getBean(GreetingEntity.class, String.format(TEMPLATE, var));
        GreetingResource greeting = greetingAssembler.toResource(greetingEntity);
        greeting.add(linkTo(methodOn(GreetingController.class).greeting(var)).withSelfRel());

        return greeting;
    }
}
