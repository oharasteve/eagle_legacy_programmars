// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 13, 2015

package com.eagle.programmar.Powershell;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Powershell.Symbols.Powershell_Variable_Reference;
import com.eagle.programmar.Powershell.Terminals.Powershell_KeywordChoice;
import com.eagle.programmar.Powershell.Terminals.Powershell_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Powershell_Variable extends TokenSequence implements EagleRunnable
{
	public @S(10) Powershell_Punctuation DOLLAR = new Powershell_Punctuation("$");
	public @S(20) @OPT Powershell_VariableScope scope;
	public @S(30) Powershell_Variable_Reference id;
	public @S(40) @OPT TokenList<Powershell_VariableDotField> fields;
	public @S(50) @OPT Powershell_Subscript subscript;

	public static class Powershell_Subscript extends TokenSequence
	{
		public @S(10) PunctuationLeftBracket leftBracket;
		public @S(20) Powershell_Expression subscr;
		public @S(30) PunctuationRightBracket rightBracket;
	}

	public static class Powershell_VariableDotField extends TokenSequence
	{
		public @S(10) PunctuationPeriod dot;
		public @S(20) Powershell_Variable_Reference id;
	}

	public static class Powershell_VariableScope extends TokenSequence
	{
		public @S(10) Powershell_KeywordChoice SCRIPT = new Powershell_KeywordChoice("env", "global", "script",
				"variable");
		public @S(20) PunctuationColon colon;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue value = interpreter._symbolTable.findSymbol(id.toString());
		interpreter.pushEagleValue(value);
	}
}
