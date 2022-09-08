// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 26, 2016

package com.eagle.programmar.CSS;

import com.eagle.programmar.CSS.Directives.CSS_AtApply;
import com.eagle.programmar.CSS.Directives.CSS_AtCharset;
import com.eagle.programmar.CSS.Directives.CSS_AtImport;
import com.eagle.programmar.CSS.Directives.CSS_AtMedia;
import com.eagle.programmar.CSS.Directives.CSS_AtMozDocument;
import com.eagle.programmar.CSS.Directives.CSS_AtNamespace;
import com.eagle.programmar.CSS.Directives.CSS_AtProvide;
import com.eagle.programmar.CSS.Directives.CSS_AtRequire;
import com.eagle.programmar.CSS.Directives.CSS_AtSupports;
import com.eagle.tokens.TokenChooser;

public class CSS_AtEntry extends TokenChooser
{
	public static final String LAST_EDIT = "2022-09-08 06:57:00 CDT";

	public @CHOICE CSS_AtApply atApply;
	public @CHOICE CSS_AtCharset atCharset;
	public @CHOICE CSS_AtImport atImport;
	public @CHOICE CSS_AtMedia atMedia;
	public @CHOICE CSS_AtMozDocument atMozDocument;
	public @CHOICE CSS_AtNamespace atNamespace;
	public @CHOICE CSS_AtProvide atProvide;
	public @CHOICE CSS_AtRequire atRequire;
	public @CHOICE CSS_AtSupports atSupports;
}
