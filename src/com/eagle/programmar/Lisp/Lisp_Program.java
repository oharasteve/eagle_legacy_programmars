// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 14, 2013

package com.eagle.programmar.Lisp;

import com.eagle.core.AbstractLanguage;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Lisp.Functions.Lisp_DefunFunction;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableProgram;
import com.eagle.transform.EagleTransformer;

public class Lisp_Program extends AbstractLanguage
		implements EagleRunnable, EagleTransformableProgram
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

	@Override
	public AbstractLanguage transformProgram(EagleTransformer transformer, EagleGenerator generator)
	{
		// First pass, just collect all the FUNCTION, SUBROUTINE and PROGRAM definitions
		for (Lisp_SExprOrComment stmt : elements._elements)
		{
			AbstractToken which1 = stmt.getWhich();
			if (which1 instanceof Lisp_Expression)
			{
				Lisp_Expression fn = (Lisp_Expression) which1;
				AbstractToken which2 = fn.getWhich();
				if (which2 instanceof Lisp_DefunFunction)
				{
					Lisp_DefunFunction defineFunc = (Lisp_DefunFunction) which2;
					defineFunc.transformFunction(transformer, generator);
				}
				else
				{
					
				}
			}
		}

		return generator.getTransfomedProgram();
	}
}
