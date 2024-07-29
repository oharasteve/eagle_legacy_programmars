// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

package com.eagle.programmar.Powershell;

import java.util.ArrayList;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleLanguage;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Powershell.Statements.Powershell_FunctionStatement;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.TokenList;

public class Powershell_Program extends EagleLanguage implements EagleRunnable
{
	public static final String POWERHSELL = "Powershell";

	public Powershell_Program()
	{
		super(POWERHSELL, new Powershell_Syntax());
	}

	@Override
	public String booleanName(boolean flag)
	{
		if (flag) return "$True";
		return "$False";
	}

	@Override
	public String getDocRoot()
	{
		return "https://docs.microsoft.com/en-us/powershell/scripting/lang-spec/";
	}

	public @S(10) @OPT TokenList<Powershell_CommentEoln> comments1;
	public @S(20) @OPT TokenList<Powershell_Directive> directives;
	public @S(30) @OPT Powershell_CmdletBinding cmdletBinding;
	public @S(40) @OPT Powershell_Parameters parameters;
	public @S(50) @OPT TokenList<Powershell_CommentEoln> comments2;
	public @S(60) @OPT TokenList<Powershell_Statement> statements;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// First pass, just collect all the FUNCTION definitions
		interpreter._functionList = new ArrayList<AbstractFunction>();
		for (Powershell_Statement stmt : statements._elements)
		{
			if (stmt.element.getWhich() instanceof Powershell_FunctionStatement)
			{
				Powershell_FunctionStatement fn = (Powershell_FunctionStatement) stmt.element.getWhich();
				if (interpreter._TRACE) System.err.println("**** Found function " + fn.name.getValue());
				interpreter._functionList.add(fn);
			}
		}

		// Second pass, execute the program
		for (Powershell_Statement stmt : statements._elements)
		{
			interpreter.tryToInterpret(stmt.element);
		}
	}
}