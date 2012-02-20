/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.deltaspike.cdise.api;


import java.lang.annotation.Annotation;
import javax.enterprise.inject.spi.BeanManager;


/**
 * <p>A CdiTestContainer provides access to an underlying JSR-299 (CDI)
 * Container. It allows starting and stopping the container and to start
 * and stop the built-in contexts of that container.</p>
 *
 * <p>The intention is to provide a portable control for CDI containers in
 * Java SE environments. It is <b>not</b> intended for environments in which the
 * CDI container is under full control of the server already, e.g. in
 * EE-containers.</p>
 */
public interface CdiContainer
{
    /**
     * Booting the CdiTestContainer will scan the whole classpath
     * for Beans and extensions available.
     * The container might throw a DeploymentException or similar on startup.
     */
    void bootContainer();
    
    /**
     * This will shutdown the underlying CDI container.
     */
    void shutdownContainer();
    
    /**
     * This will start all container built-in Contexts
     */
    void startContexts();
    
    /**
     * Stop all container built-in Contexts and destroy all beans properly
     */
    void stopContexts();

    /**
     * Start the specified scope. This only works for scopes which are handled
     * by the CDI container itself. Custom scoped of 3rd party
     * Context implementations shall be started directly (they are portable anyway).
     * 
     * @param scopeClass e.g. RequestScoped.class
     */
    void startContext(Class<? extends Annotation> scopeClass);

    /**
     * Stop the specified scope. This only works for scopes which are handled
     * by the CDI container itself. Custom scoped of 3rd party
     * Context implementations shall be stopped directly (they are portable anyway).
     * 
     * @param scopeClass e.g. RequestScoped.class
     */
    void stopContext(Class<? extends Annotation> scopeClass);

    /**
     * @return the {@link BeanManager} or <code>null</code> it not available
     */
    BeanManager getBeanManager();

}