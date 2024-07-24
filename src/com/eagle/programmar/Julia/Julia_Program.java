// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.Julia;

import java.util.ArrayList;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleLanguage;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Julia.Statements.Julia_Function;
import com.eagle.programmar.Julia.Terminals.Julia_Comment;
import com.eagle.programmar.Julia.Terminals.Julia_EOLN;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Julia_Program extends EagleLanguage implements EagleRunnable
{
	public static final String JULIA = "Julia";

	public Julia_Program()
	{
		super(JULIA, new Julia_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return "https://docs.julialang.org/en/v1/";
	}

	public @S(10) TokenList<Julia_Element> elements;

	public static class Julia_Element extends TokenChooser
	{
		public @CHOICE Julia_CommentEoln comment;
		public @CHOICE Julia_Statement stmt;
	}

	public static class Julia_CommentEoln extends TokenSequence
	{
		public @S(10) Julia_Comment comment;
		public @S(20) Julia_EOLN eoln;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// First pass, just collect all the FUNCTION definitions
		interpreter._functionList = new ArrayList<AbstractFunction>();
		for (Julia_Element elt : elements._elements)
		{
			AbstractToken which = elt.getWhich();
			if (which instanceof Julia_Statement)
			{
				Julia_Statement stmt = (Julia_Statement) which;
				which = stmt.getWhich();
				if (which instanceof Julia_Function)
				{
					Julia_Function fn = (Julia_Function) which;
					interpreter._functionList.add(fn);
				}
			}
		}

		// Second pass, execute the program
		for (Julia_Element elt : elements._elements)
		{
			AbstractToken which = elt.getWhich();
			if (which instanceof Julia_Statement)
			{
				Julia_Statement stmt = (Julia_Statement) which;
				interpreter.tryToInterpret(stmt);
			}
		}
	}
}