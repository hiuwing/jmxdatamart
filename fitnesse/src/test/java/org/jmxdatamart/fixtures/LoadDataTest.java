package org.jmxdatamart.fixtures;/*
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

import fitlibrary.DoFixture;
import org.jmxdatamart.common.*;
import org.jmxdatamart.Loader.*;
import org.jmxdatamart.Loader.Setting.DBInfo;

import java.io.File;
import java.sql.*;
import java.util.*;


public class LoadDataTest extends DoFixture {

    public void loadADataFile() throws DBException,SQLException  {

        Properties p = new Properties();
        p.put("user","derbyuser");
        p.put("password","derbyuser");

        String targetDB= "//localhost:1527//wing_homework/Dropbox/winter_2013/cs488/jmxdatamart_3_6/fitnesse/target/derbyDatamartDB";
        Connection datamartConnection = new DerbyHandler().connectDatabase(targetDB,p);

        String sourceDB="//localhost:1527//wing_homework/Dropbox/winter_2013/cs488/jmxdatamart_3_6/fitnesse/target/derbyDB";
        Connection sourceConnection = new DerbyHandler().connectDatabase(sourceDB,p);

        Map<String,Map> sourceSchema = DBHandler.getDatabaseSchema(sourceConnection,"derbyuser",DataType.SupportedDatabase.DERBY);
        int nextTestID = DBHandler.getMaxTestID(datamartConnection, "maintable", "testid");

        Setting s= new Setting();

        Setting.DBInfo sourceDBInfo= s.new DBInfo();
        sourceDBInfo.setDatabaseName(sourceDB);
        sourceDBInfo.setDatabaseType(DataType.SupportedDatabase.DERBY);
        sourceDBInfo.setJdbcUrl("jdbc:derby:"+targetDB);
        sourceDBInfo.setUserInfo(p);
        s.setSource(sourceDBInfo);

        Setting.DBInfo targetDBInfo= s.new DBInfo();
        targetDBInfo.setDatabaseName(targetDB);
        targetDBInfo.setDatabaseType(DataType.SupportedDatabase.DERBY);
        targetDBInfo.setJdbcUrl("jdbc:derby:"+targetDB);
        targetDBInfo.setUserInfo(p);
        s.setTarget(targetDBInfo);

        Properties add = new Properties();
        add.put("owner","tripwire");
        add.put("team","dewberry");
        add.put("version","3.18");
        s.setAdditional(add);


        DB2DB d2d = new DB2DB(s,new File(System.getProperty("user.dir")));
        d2d.loadOneDataFile(datamartConnection,sourceConnection,sourceDB,sourceSchema,nextTestID);


    }



}
