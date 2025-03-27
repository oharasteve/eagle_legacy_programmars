// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 4, 2022

package com.eagle.programmar.Go;

import com.eagle.core.AbstractLanguage;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Go.Statements.Go_Data;
import com.eagle.programmar.Go.Statements.Go_Function;
import com.eagle.programmar.Go.Statements.Go_Import;
import com.eagle.programmar.Go.Statements.Go_Package;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;

public class Go_Program extends AbstractLanguage implements EagleRunnable
{
	public static final String GO = "Go";

	public Go_Program()
	{
		super(GO, new Go_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return "https://go.dev/ref/spec";
	}

	public @S(10) TokenList<Go_Element> elements;

	public static class Go_Element extends TokenChooser
	{
		public @CHOICE Go_CommentEoln XXcomment;
		public @CHOICE Go_Package XXpkg;
		public @CHOICE Go_Import XXimport;
		public @CHOICE Go_Data XXdata;
		public @CHOICE Go_Function XXfunction;
		public @CHOICE Go_Statement XXstmt;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// First pass, just collect all the FUNCTION definitions
		for (Go_Element element : elements._elements)
		{
			AbstractToken which = element.getWhich();
			if (which instanceof Go_Function)
			{
				Go_Function fn = (Go_Function) which;
				interpreter.addFunction(fn.id.getValue(), fn);
			}
		}

		// Second pass, execute the program
		for (Go_Element element : elements._elements)
		{
			interpreter.tryToInterpret(element);
		}
	}
}
