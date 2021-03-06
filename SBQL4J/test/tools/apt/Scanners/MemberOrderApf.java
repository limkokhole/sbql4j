/*
 * Copyright 2004-2007 Sun Microsystems, Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Sun Microsystems, Inc., 4150 Network Circle, Santa Clara,
 * CA 95054 USA or visit www.sun.com if you need additional information or
 * have any questions.
 */


import pl.wcislo.sbql4j.mirror.apt.*;
import pl.wcislo.sbql4j.mirror.declaration.*;
import pl.wcislo.sbql4j.mirror.type.*;
import pl.wcislo.sbql4j.mirror.util.*;

import java.util.Collection;
import java.util.Set;
import java.util.Arrays;

import static java.util.Collections.*;
import static pl.wcislo.sbql4j.mirror.util.DeclarationVisitors.*;

/*
 * The processor of this factory verifies class members are returned
 * in source-code order.
 */
public class MemberOrderApf implements AnnotationProcessorFactory {
    // Process any set of annotations
    private static final Collection<String> supportedAnnotations
        = unmodifiableCollection(Arrays.asList("*"));

    // No supported options
    private static final Collection<String> supportedOptions = emptySet();

    public Collection<String> supportedAnnotationTypes() {
        return supportedAnnotations;
    }

    public Collection<String> supportedOptions() {
        return supportedOptions;
    }

    public AnnotationProcessor getProcessorFor(
            Set<AnnotationTypeDeclaration> atds,
            AnnotationProcessorEnvironment env) {
        return new MemberOrderAp(env);
    }

    private static class MemberOrderAp implements AnnotationProcessor {
        private final AnnotationProcessorEnvironment env;
        MemberOrderAp(AnnotationProcessorEnvironment env) {
            this.env = env;
        }

        private void verifyOrder(Collection<? extends Declaration> decls) {
            int count = 0;
            for(Declaration decl: decls) {
                VisitOrder order = decl.getAnnotation(VisitOrder.class);
                if (order.value() <= count)
                    throw new RuntimeException("Out of order declarations");
                count = order.value();
            }
        }

        public void process() {
            for(TypeDeclaration td: env.getSpecifiedTypeDeclarations()) {
                verifyOrder(td.getFields());
                verifyOrder(td.getMethods());
            }
        }
    }
}
