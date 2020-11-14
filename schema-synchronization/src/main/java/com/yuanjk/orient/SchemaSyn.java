package com.yuanjk.orient;

import com.orientechnologies.orient.core.db.document.ODatabaseDocument;
import com.orientechnologies.orient.core.metadata.schema.OClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SchemaSyn {

    private static final Logger log = LoggerFactory.getLogger(SchemaSyn.class);

    public OClass getClass(ODatabaseDocument database, String className) {
        OClass oClass = database.getClass(className);
        if (oClass == null) {
            log.error("class not found: [{}]", className);
        } else {
            log.info("class is already exists: [{}]", className);
        }

        return oClass;
    }

}
