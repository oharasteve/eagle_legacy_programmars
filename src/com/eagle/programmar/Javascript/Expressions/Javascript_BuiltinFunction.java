// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Javascript.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.programmar.Javascript.Terminals.Javascript_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;

public class Javascript_BuiltinFunction extends PrimaryOperator
{
	public @S(10) Javascript_KeywordChoice fn = new Javascript_KeywordChoice("eval");

	/*******************************************************************************
	 * These are called directly from Javascript_FunctionCall or Javascript_Subfield
	 */
	
	public static void floor(EagleInterpreter interpreter, Javascript_Expression param)
	{
		double val = interpreter.getDoubleValue(param);
		interpreter.pushInt((int) val);
	}
	
	public static void writeln(EagleInterpreter interpreter, Javascript_Expression param)
	{
		String val = interpreter.getStrValue(param);
		if (val.startsWith("<br>")) val = val.substring(4);	// Toss leading <br> if present
		System.out.println(val);
	}

	public static void endsWith1(EagleInterpreter interpreter, String name,
			Javascript_Expression patternExpr)
	{
		EagleValue val = interpreter.findSymbol(name);
		String str = val.forceStringValue();
		String patt = interpreter.getStrValue(patternExpr);
		interpreter.pushBool(str.startsWith(patt));
	}

	public static void endsWith2(EagleInterpreter interpreter, String name,
			Javascript_Expression patternExpr, Javascript_Expression scExpr)
	{
		EagleValue val = interpreter.findSymbol(name);
		String str = val.forceStringValue();
		String patt = interpreter.getStrValue(patternExpr);
		int sc = interpreter.getIntValue(scExpr);
		interpreter.pushBool(str.startsWith(patt, sc));
	}
	
	public static void length(EagleInterpreter interpreter, Javascript_Expression param)
	{
		String val = interpreter.getStrValue(param);
		interpreter.pushInt(val.length());
	}
}
