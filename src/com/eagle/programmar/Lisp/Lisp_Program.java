// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 14, 2013

package com.eagle.programmar.Lisp;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleLanguage;
import com.eagle.core.EagleRunnable;
import com.eagle.tokens.TokenList;

public class Lisp_Program extends EagleLanguage implements EagleRunnable
{
	public static final String LISP = "Lisp";

	public Lisp_Program()
	{
		super(LISP, new Lisp_Syntax());
	}

	@Override
	public String booleanName(boolean flag)
	{
		if (flag) return "T";
		return "NIL";
	}

	@Override
	public String getDocRoot()
	{
		return "http://www.lispworks.com/documentation/HyperSpec/Body/";
	}

	public @S(10) TokenList<Lisp_SExprOrComment> elements;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// Don't need two passes for Lisp. Functions cannot be called before defined.
		for (Lisp_SExprOrComment elt : elements._elements)
		{
			interpreter.tryToInterpret(elt);
		}
	}
}
