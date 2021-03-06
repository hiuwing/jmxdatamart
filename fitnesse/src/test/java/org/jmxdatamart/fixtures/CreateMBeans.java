/*
 * Copyright (c) 2012, Tripwire, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 *  o Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 *  o Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.jmxdatamart.fixtures;

import fitlibrary.SetUpFixture;

import javax.management.*;
import java.lang.management.ManagementFactory;

public class CreateMBeans extends SetUpFixture {

  public void beanClassBeanName(String beanClass, String beanName) throws MalformedObjectNameException, MBeanRegistrationException, InstanceAlreadyExistsException, ClassNotFoundException, NotCompliantMBeanException, InstantiationException, IllegalAccessException {
    ObjectName name = new ObjectName(beanName);
    createAndRegisterMBean(beanClass, name);
  }

  private void createAndRegisterMBean(String beanClassName, ObjectName name) throws ClassNotFoundException, IllegalAccessException, InstantiationException, MBeanRegistrationException, InstanceAlreadyExistsException, NotCompliantMBeanException {
    Class beanClass = Class.forName(beanClassName);
    ManagementFactory.getPlatformMBeanServer().registerMBean(beanClass.newInstance(), name);
  }

}
