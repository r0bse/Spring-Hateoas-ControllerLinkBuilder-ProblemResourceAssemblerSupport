# Spring-Hateoas-ResourceAssemblerSupport-Problem
I encapsulated a problem which I ran into while playing around with Spring-Hateoas. When using the toResource() in ResourceAssemblerSupport it throws an exception in ControllerLinkBuilder when trying to extract the variable from URI.

IllegalArgumentException: Not enough variable values available to expand 'var'] with root cause
