Fuse Readiness Training - Module 2 - Advanced Services Exercises
=======================

Fuse Readiness Training Module 2 Advanced Services training exercises

# Exercises

The module consists of the following exercises

##  Basic service discovery

1. Create a bundle project that contains a service activator using the low level OSGi APIs
2. Create a bundle project that contains a service consumer activator using the low level OSGi API
3. Deploy both bundles in Fuse
	1. Service first, then consumer
4. Investigation
	1. Undeploy the bundles if deployed
	2. Deploy consumer first then service
	3. What happens?

## Service Filtering

1. Basic Service Filtering Exercise
	1. Create a service bundle with a service interface and multiple implementations
	2. Create a consumer bundle that filters on a specific implementation from the service bundle
	3. Deploy the bundles and investigate the results

2. Service Filtering Breakfix
	1. Create a service bundle with a service interface and implementation
	2. Create a bundle with the same interface as above and a new implementation
	3. In the first bundle, attempt to filter for the service implementation from the second bundle
	4. Deploy the bundles and investigate the results

 
## Service Listeners

1. Reuse service bundle from Service Registration and Discovery exercise
2. Create a bundle that contains a ServiceListener that filters for the service from the service bundle
3. Deploy the bundles and investigate the results

## ServiceTracker

1. Reuse service bundle from Service Registration and Discovery exercise
2. Create a bundle that utilizes a ServiceTracker that filters for the service from the service bundle
3. Deploy the bundles and investigate the results

## Managed Services

1. ManagedService Exercise
	1. Reuse the consumer bundle from the ServiceTracker exercise
	2. Create a bundle that exposes a ManagedService
	3. Create a Configuration in the Fuse container
	4. Deploy the bundles and investigate
		1. What occurs when there is no configuration
2. ManagedServiceFactory Exercise
	1. Reuse the consumer bundle from the ServiceTracker exercise
	2. Create a bundle that exposes a ManagedServiceFactory
	3. Create a Configuration in the Fuse container
	4. Create a second Configuration in the Fuse container
	5. Deploy the bundles and investigate
		1. How many services did the consumer receive?


## Whiteboard Pattern

1. Review BreakFix exercise from ServiceListener section
2. Fix the implementation using the Whiteboard pattern
3. Deploy the bundles and investigate 




