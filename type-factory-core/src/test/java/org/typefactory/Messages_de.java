/*
   Copyright 2021-2022 Evan Toliopoulos (typefactory.org)

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
package org.typefactory;

import java.util.ListResourceBundle;

public class Messages_de extends ListResourceBundle {

  @Override
  protected Object[][] getContents() {
    return new Object[][]{
        {"aa"  , "einige Fehlermeldung-A."},
        {"bb"  , "einige Fehlermeldung-B."},
        {"cc.c", "einige Fehlermeldung-C."},
        {"dc.d", "einige Fehlermeldung-D."},
        {"ee-e", "einige Fehlermeldung-E."},
        {"ff_f", "einige Fehlermeldung-F."},
        {"gggg", "einige Fehlermeldung-G."},
        {"hhhh", "einige Fehlermeldung-H."},

        {"ss"  , "einige Parser-Fehlermeldungen-S."},
        {"tt"  , "einige Parser-Fehlermeldungen-T."},
        {"uu.u", "einige Parser-Fehlermeldungen-U."},
        {"vv.v", "einige Parser-Fehlermeldungen-V."},
        {"ww-w", "einige Parser-Fehlermeldungen-W mit Wert {0}."},
        {"xx_x", "einige Parser-Fehlermeldungen-X mit Wert {0}."},
        {"yyyy", "einige Parser-Fehlermeldungen-Y mit Wert {0}."},
        {"zzzz", "einige Parser-Fehlermeldungen-Z mit Wert {0}."},
    };
  }
}
