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
import pl.wcislo.sbql4j.javac.test.football.model.Pilkarz;
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


public class KORQueries_SbqlQuery20 {
    private com.db4o.ObjectContainer dbConn;

    public KORQueries_SbqlQuery20(final com.db4o.ObjectContainer dbConn) {
        this.dbConn = dbConn;
    }

    /**
     * original query='dbConn.(
                                    ((0 as i close by (i+100 where i <= max(Pilkarz.pensja)) as i)
                                    join (count(Pilkarz where pensja >= i and pensja < i+100) as c)).
                                    (c + " PilkarzY zarabia  pomiedzy  "+ i +" i " + (i+99)) as message
                            )'
     *
     * query after optimization='dbConn.((0 as i close by (i + 100 where i <=  max(Pilkarz.getPensja())) as i join  count((Pilkarz where getPensja() >= i and getPensja() < i + 100)) as c).(c + " PilkarzY zarabia  pomiedzy  " + i + " i " + i + 99) as message)'
    */
    public java.util.List<java.lang.String> executeQuery() {
        com.db4o.ObjectContainer _ident_dbConn = dbConn;
        java.util.List<java.lang.String> _queryResult = _ident_dbConn.query(new KORQueries_SbqlQuery20Db4o0());

        return _queryResult;
    }
}
