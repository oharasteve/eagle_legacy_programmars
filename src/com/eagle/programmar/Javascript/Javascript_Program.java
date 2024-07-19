// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.Javascript;

import java.util.ArrayList;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleLanguage;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Javascript.Symbols.Javascript_Function_Definition;
import com.eagle.programmar.Javascript.Terminals.Javascript_Comment;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;

public class Javascript_Program extends EagleLanguage implements EagleRunnable
{
	public static final String JAVASCRIPT = "Javascript";

	public Javascript_Program()
	{
		super(JAVASCRIPT, new Javascript_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return "http://www.w3schools.com/js/";
	}

	public @S(10) @OPT TokenList<Javascript_Element> elements;

	public static class Javascript_Element extends TokenChooser
	{
		public @CHOICE Javascript_Comment comment;
		public @CHOICE Javascript_Function function;
		public @CHOICE Javascript_Statement statement;
		public @CHOICE Javascript_Class clss;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// First pass, just collect all the method definitions
		interpreter._functionList = new ArrayList<AbstractFunction>();
		for (Javascript_Element element : elements._elements)
		{
			if (element.getWhich() instanceof Javascript_Function)
			{
				Javascript_Function func = (Javascript_Function) element.getWhich();
				Javascript_Function_Definition functionName = func.implementation.functionName;
				if (functionName != null && functionName.isPresent())
				{
					interpreter._functionList.add(func);
					if (interpreter._TRACE)
					{
						System.err.println("*** Found Javascript function " + functionName.getValue());
					}
				}
			}
		}

		// Second pass, run everything
		for (Javascript_Element element : elements._elements)
		{
			interpreter.tryToInterpret(element);
		}
	}
}
