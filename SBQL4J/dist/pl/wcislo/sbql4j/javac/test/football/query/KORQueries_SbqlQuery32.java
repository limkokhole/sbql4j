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
import pl.wcislo.sbql4j.javac.test.football.model.Druzyna;
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


public class KORQueries_SbqlQuery32 {
    private com.db4o.ObjectContainer dbConn;

    public KORQueries_SbqlQuery32(final com.db4o.ObjectContainer dbConn) {
        this.dbConn = dbConn;
    }

    /**
     * original query='dbConn.( (Druzyna as d where
                            (avg(d.lista_zawodnikow.premia)>20 && avg(d.lista_zawodnikow.premia)<40)
                            && (d.mecz.nazwa_stadionu in (unique(Mecz.nazwa_stadionu)))))'
     *
     * query after optimization='dbConn.( unique Mecz.getNazwa_stadionu() group as _aux0).(Druzyna as d where  avg(d.getLista_zawodnikow().getPremia()) > 20 and  avg(d.getLista_zawodnikow().getPremia()) < 40 and (d.getMecz().getNazwa_stadionu() in _aux0))'
    */
    public java.util.Collection<pl.wcislo.sbql4j.javac.test.football.model.Druzyna> executeQuery() {
        com.db4o.ObjectContainer _ident_dbConn = dbConn;
        java.util.Collection<pl.wcislo.sbql4j.javac.test.football.model.Druzyna> _queryResult =
            _ident_dbConn.query(new KORQueries_SbqlQuery32Db4o0());

        return _queryResult;
    }
}
