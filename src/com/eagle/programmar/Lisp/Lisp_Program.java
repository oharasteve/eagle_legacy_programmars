// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 14, 2013

package com.eagle.programmar.Lisp;

import com.eagle.core.EagleLanguage;
import com.eagle.tokens.TokenList;

public class Lisp_Program extends EagleLanguage
{
	public static final String LISP = "Lisp";
	
	public Lisp_Program()
	{
		super(LISP, new Lisp_Syntax());
	}
	
	@Override
	public String getDocRoot()
	{
		return "http://www.lispworks.com/documentation/HyperSpec/Body/";
	}
	
	public @S(10) TokenList<Lisp_SExpr> sexprs;
}
