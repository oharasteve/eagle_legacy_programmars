// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.Ada;

import java.util.ArrayList;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleLanguage;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Ada.Statements.Ada_Function;
import com.eagle.programmar.Ada.Statements.Ada_Procedure;
import com.eagle.programmar.Ada.Terminals.Ada_Comment;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;

public class Ada_Program extends EagleLanguage implements EagleRunnable
{
	public static final String ADA = "Ada";

	public Ada_Program()
	{
		super(ADA, new Ada_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return "https://www.adaic.org/resources/add_content/standards/05rm/RM-Final.pdf";
	}

	public @S(10) TokenList<Ada_Element> elements;

	public static class Ada_Element extends TokenChooser
	{
		public @CHOICE Ada_Comment XXcomment;
		public @CHOICE Ada_Statement XXstmt;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// First pass, just collect all the FUNCTION definitions
		interpreter._functionList = new ArrayList<AbstractFunction>();
		for (Ada_Element element : elements._elements)
		{
			AbstractToken which = element.getWhich();
			if (which instanceof Ada_Statement)
			{
				Ada_Statement statement = (Ada_Statement) which;
				which = statement.getWhich();
				if (which instanceof Ada_Procedure)
				{
					Ada_Procedure proc = (Ada_Procedure) which;
					if (interpreter._TRACE)
					{
						System.err.println("*** Found Ada procedure " + proc.id.getValue());
					}
					for (Ada_Statement stmt : proc.stmts1._elements)
					{
						if (stmt.getWhich() instanceof Ada_Function)
						{
							Ada_Function fn = (Ada_Function) stmt.getWhich();
							interpreter._functionList.add(fn);
							if (interpreter._TRACE)
							{
								System.err.println("*** Found Ada function " + fn.id.getValue());
							}
						}
						if (stmt.getWhich() instanceof Ada_Procedure)
						{
							Ada_Procedure fn = (Ada_Procedure) stmt.getWhich();
							interpreter._functionList.add(fn);
							if (interpreter._TRACE)
							{
								System.err.println("*** Found Ada procedure " + fn.id.getValue());
							}
						}
					}
				}
			}
		}

		// Second pass, execute the program
		for (Ada_Element element : elements._elements)
		{
			AbstractToken which = element.getWhich();
			if (which instanceof Ada_Statement)
			{
				Ada_Statement stmt = (Ada_Statement) which;
				interpreter.tryToInterpret(stmt);
			}
		}
	}
}
