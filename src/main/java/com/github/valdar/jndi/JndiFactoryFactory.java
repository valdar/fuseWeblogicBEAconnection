package com.github.valdar.jndi;

import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jndi.JndiObjectFactoryBean;

public class JndiFactoryFactory {
    public static Logger LOG = LoggerFactory.getLogger(JndiFactoryFactory.class);

    public JndiObjectFactoryBean createJndiObjectFactory() throws IllegalArgumentException, NamingException {
        LOG.info("createJndiObjectFactory() called.");
        LOG.info("TCCL currently set to {}.", Thread.currentThread().getContextClassLoader());
        Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
        LOG.info("Setting TCCL to be the Bundle class loader {}.", Thread.currentThread().getContextClassLoader());
        LOG.info("I got called from Thread {}", Thread.currentThread().getName());
        JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
        return bean;
    }
}
