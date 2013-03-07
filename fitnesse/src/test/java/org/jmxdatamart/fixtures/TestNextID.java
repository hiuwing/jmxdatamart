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
import fitlibrary.*;
import org.jmxdatamart.common.*;
import java.sql.*;
import java.util.*;


public class TestNextID extends DoFixture {



    public void getNextTestID(){

        Properties p = new Properties();
        p.put("user","derbyuser");
        p.put("password","derbyuser");
        String db= "//localhost:1527//wing_homework/Dropbox/winter_2013/cs488/jmxdatamart_3_6/fitnesse/target/derbyDatamartDB;create=true";
        Connection datamartConnection = new DerbyHandler().connectDatabase(db,p);

        nextTestID = DBHandler.getMaxTestID(datamartConnection,"maintable","testid");
        System.out.println(nextTestID);

    }

    public TestNextID getOrgDotJmxdatamartDotFixturesDotDoLoaderTest() {
        // The particular version of fitnesse which is published to the central Maven
        // repository requires this method.  Lame.
        return this;
    }

    public TestNextID orgDotJmxdatamartDotFixturesDotDoLoaderTest(){
        return this;
    }


    public int nextTestID;

    public boolean nextIDIs( int kk){
        return nextTestID ==kk;
    }

    public int nextID(){
        return nextTestID ;
    }

}
