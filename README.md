# fuseWeblogicBEAconnection
An example on how to connect to weblogic BEA with camel Jms component on Jboss Fuse.

The main issue to overcome in order to have a jms BEA connector deployed in Jboss fuse is about OSGi compliance of Weblogic JMS driver, basically what has been described in:
- https://issues.jboss.org/browse/ENTESB-1567
- https://issues.jboss.org/browse/ENTESB-3699

## Solution/Workaround

The solution is based on:
  
  - Force the weblogic driver to use the correct classloader by using the com.github.valdar.jndi.JndiFactoryFactory class to instantiate the Weblogic Jms connection factory:
```<bean id="factory" class="com.github.valdar.jndi.JndiFactoryFactory"/>
<bean id="webLogicJmsConnectionFactory" factory-ref="factory" factory-method="createJndiObjectFactory" init-method="afterPropertiesSet">
    <property name="jndiTemplate" ref="jndiWebLogicTemplate"/>
    <property name="jndiName" value="WWPConnectionFactory"/>
    <property name="proxyInterface" value="javax.jms.ConnectionFactory"/>
</bean>```

  - Be sure that weblogic wrapped bundle doesn't export javax.* package by: osgi:install wrap:file://<projecthome>/wlthint3client.jar,file://wlthint3client.bnd/wlthint3client.bnd 
this is done with the setting in wlthint3client.bnd.

## Build instructions

  1. To build this project be sure to have maven 3.x installed and from the <projecthome> directory run:
    `mvn clean package`

  2. Download from Oracle the `wlthint3client.jar` jar.

  3. To deploy the example to JBoss Fuse 6.2.1 container. You will run the following command from karaf shell:
    ```features:install activemq-camel
    osgi:install wrap:file://<projecthome>/wlthint3client.jar,file://<projecthome>/wlthint3client.bnd
    osgi:install -s file://<projecthome>/target/fuseWeblogicBEAconnection-1.0.0-SNAPSHOT.jar```


