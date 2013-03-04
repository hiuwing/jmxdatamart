package org.jmxdatamart.Loader;/*
 * Copyright (c) 2013, Tripwire, Inc.
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
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Enumeration;
import java.util.Properties;

public class Setting {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
    public class DBInfo {
        private String databaseType;
        private String jdbcUrl=null;
        private String databaseName=null;
        private Properties userInfo;

        public String getDatabaseName() {
            return databaseName;
        }

        public void setDatabaseName(String databaseName) {
            this.databaseName = databaseName;
        }


        public String getDatabaseType() {
            return databaseType;
        }

        public void setDatabaseType(String databaseType) {
            this.databaseType = databaseType;
        }

        public Properties getUserInfo() {
            return userInfo;
        }


        public String getJdbcUrl() {
            return jdbcUrl;
        }

        public void setJdbcUrl(String jdbcUrl) {
            this.jdbcUrl = jdbcUrl;
        }
   };

    private DBInfo source, target;
    private Properties required, optional;

    public DBInfo getSource() {
        return source;
    }


    public DBInfo getTarget() {
        return target;
    }


    public Properties getRequired() {
        return required;
    }


    public Properties getOptional() {
        return optional;
    }



    public Setting(String filePath) {
        source = new DBInfo();
        target = new DBInfo();
        required =new Properties();
        optional = new Properties();
        source.userInfo = new Properties();
        target.userInfo = new Properties();
        Properties props = new Properties();
        InputStream in=null;

        try {
            in = new BufferedInputStream(new FileInputStream(filePath));
            props.load(in);

            Enumeration en = props.propertyNames();
            String key,property,section,keyname;
            while (en.hasMoreElements()) {
                key = (String) en.nextElement();
                property = props.getProperty(key);
                section = key.split("\\.")[0];
                keyname = key.split("\\.")[1];

                if (section.equalsIgnoreCase("required"))
                    required.put(keyname, property);
                else if(section.equalsIgnoreCase("optional"))
                    optional.put(keyname, property);
                else{
                    if (key.equalsIgnoreCase("source.type"))
                        source.setDatabaseType(property);
                    else if(key.equalsIgnoreCase("source.JDBCurl"))
                        source.setJdbcUrl(property);
                    else if(key.equalsIgnoreCase("source.databasename"))
                        source.setDatabaseName(property);
                    else if(key.equalsIgnoreCase("source.user"))
                        source.userInfo.put("user", property);
                    else if(key.equalsIgnoreCase("source.password"))
                        source.userInfo.put("password", property);
                    else if (key.equalsIgnoreCase("target.type"))
                        target.setDatabaseType(property);
                    else if(key.equalsIgnoreCase("target.JDBCurl"))
                        target.setJdbcUrl(property);
                    else if(key.equalsIgnoreCase("target.databasename"))
                        target.setDatabaseName(property);
                    else if(key.equalsIgnoreCase("target.user"))
                        target.userInfo.put("user",property);
                    else if(key.equalsIgnoreCase("target.password"))
                        target.userInfo.put("password", property);
                }
            }
        }
        catch (FileNotFoundException fe){
            logger.error("Can't read the setting file:" + fe.getMessage(), fe);
        }
        catch (IOException ie){
            logger.error("Can't read the setting file:" + ie.getMessage(), ie);
        }
        finally {
            try{
                if (in!=null) in.close();
            }
            catch (IOException ie){

            }
        }
    }
}


