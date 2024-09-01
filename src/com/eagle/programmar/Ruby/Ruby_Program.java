// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.Ruby;

import com.eagle.core.EagleLanguage;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Ruby.Statements.Ruby_Function;
import com.eagle.programmar.Ruby.Terminals.Ruby_Comment;
import com.eagle.programmar.Ruby.Terminals.Ruby_EOLN;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Ruby_Program extends EagleLanguage implements EagleRunnable
{
	public static final String RUBY = "Ruby";

	public Ruby_Program()
	{
		super(RUBY, new Ruby_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return "https://docs.ruby-lang.org/en/2.4.0/syntax/";
	}

	public @S(10) TokenList<Ruby_Element> elements;

	public static class Ruby_Element extends TokenChooser
	{
		public @CHOICE Ruby_CommentEoln XXcomment;
		public @CHOICE Ruby_Statement XXstmt;
	}

	public static class Ruby_CommentEoln extends TokenSequence implements EagleRunnable
	{
		public @S(10) Ruby_Comment comment;
		public @S(20) Ruby_EOLN eoln;

		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			// Nothing to do here
		}
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// First pass, just collect all the FUNCTION definitions
		for (Ruby_Element elt : elements._elements)
		{
			AbstractToken which = elt.getWhich();
			if (which instanceof Ruby_Statement)
			{
				Ruby_Statement stmt = (Ruby_Statement) which;
				which = stmt.getWhich();
				if (which instanceof Ruby_Function)
				{
					Ruby_Function fn = (Ruby_Function) which;
					interpreter.addFunction(fn.id.getValue(), fn);
				}
			}
		}

		// Second pass, execute the program
		for (Ruby_Element elt : elements._elements)
		{
			AbstractToken which = elt.getWhich();
			if (which instanceof Ruby_Statement)
			{
				Ruby_Statement stmt = (Ruby_Statement) which;
				interpreter.tryToInterpret(stmt);
			}
		}
	}
}