package pl.wcislo.sbql4j.javac.test.football.query;

import com.db4o.ObjectContainer;

import org.apache.commons.collections.CollectionUtils;

import pl.wcislo.sbql4j.exception.*;
import pl.wcislo.sbql4j.java.model.compiletime.Signature.SCollectionType;
import pl.wcislo.sbql4j.java.model.runtime.*;
import pl.wcislo.sbql4j.java.model.runtime.Struct;
import pl.wcislo.sbql4j.java.model.runtime.factory.*;
import pl.wcislo.sbql4j.java.utils.ArrayUtils;
import pl.wcislo.sbql4j.java.utils.OperatorUtils;
import pl.wcislo.sbql4j.java.utils.Pair;
import pl.wcislo.sbql4j.javac.test.football.data.*;
import pl.wcislo.sbql4j.javac.test.football.model.*;
import pl.wcislo.sbql4j.javac.test.football.model.Mecz;
import pl.wcislo.sbql4j.lang.codegen.nostacks.*;
import pl.wcislo.sbql4j.lang.codegen.simple.*;
import pl.wcislo.sbql4j.lang.db4o.*;
import pl.wcislo.sbql4j.lang.db4o.codegen.*;
import pl.wcislo.sbql4j.lang.db4o.codegen.interpreter.*;
import pl.wcislo.sbql4j.lang.db4o.codegen.nostacks.*;
import pl.wcislo.sbql4j.lang.parser.expression.*;
import pl.wcislo.sbql4j.lang.parser.expression.OrderByParamExpression.SortType;
import pl.wcislo.sbql4j.lang.parser.terminals.*;
import pl.wcislo.sbql4j.lang.parser.terminals.operators.*;
import pl.wcislo.sbql4j.lang.types.*;
import pl.wcislo.sbql4j.lang.xml.*;
import pl.wcislo.sbql4j.model.*;
import pl.wcislo.sbql4j.model.collections.*;
import pl.wcislo.sbql4j.util.*;
import pl.wcislo.sbql4j.util.Utils;
import pl.wcislo.sbql4j.xml.model.*;
import pl.wcislo.sbql4j.xml.parser.store.*;

import java.lang.Integer;
import java.lang.String;

import java.text.ParseException;

import java.util.*;


public class KORQueries_SbqlQuery37 {
    private com.db4o.ObjectContainer dbConn;
    private java.lang.String nazwadruzyny2;

    public KORQueries_SbqlQuery37(final com.db4o.ObjectContainer dbConn,
        final java.lang.String nazwadruzyny2) {
        this.dbConn = dbConn;
        this.nazwadruzyny2 = nazwadruzyny2;
    }

    /**
     * original query='dbConn.(count(Mecz where druzyna.nazwa == nazwadruzyny2))'
     *
     * query after optimization='dbConn.( count((Mecz where getDruzyna().getNazwa() == nazwadruzyny2)))'
    */
    public java.lang.Integer executeQuery() {
        com.db4o.ObjectContainer _ident_dbConn = dbConn;
        java.lang.Integer _queryResult = _ident_dbConn.query(new KORQueries_SbqlQuery37Db4o0(
                    nazwadruzyny2));

        return _queryResult;
    }
}
