package com.yuanjk.orient;

import com.orientechnologies.orient.core.config.OGlobalConfiguration;
import com.orientechnologies.orient.core.db.ODatabasePool;
import com.orientechnologies.orient.core.db.OrientDB;
import com.orientechnologies.orient.core.db.OrientDBConfig;
import com.orientechnologies.orient.core.db.document.ODatabaseDocument;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SchemaSynTest {
    private static SchemaSyn schemaSyn;

    @Before
    public void setUp() throws Exception {
        schemaSyn = new SchemaSyn();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetClass() throws InterruptedException {
        String className = "test_class_1113_21";
        boolean flag = true;
        OGlobalConfiguration.NETWORK_REQUEST_TIMEOUT.setValue(120000);
        OrientDB orientDB = new OrientDB("remote:localhost", "root", "wyc", OrientDBConfig.defaultConfig());
        ODatabasePool pool = new ODatabasePool(orientDB, "test", "root", "wyc");
//        OGlobalConfiguration.NETWORK_REQUEST_TIMEOUT.setValue(120000);
        while (flag) {
            try (ODatabaseDocument db = pool.acquire()) {
                OClass oClass = schemaSyn.getClass(db, className);

                if (oClass == null) {
                    System.out.println("class not found: [" + className + "]");
                } else {
                    System.out.println("class is already exists: [" + className + "]");
                }
            }
            Thread.sleep(10 * 1000L);
        }
        pool.close();
        orientDB.close();
    }
}