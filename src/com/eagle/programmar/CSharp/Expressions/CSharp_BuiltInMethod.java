// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2024

package com.eagle.programmar.CSharp.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.Expressions.CSharp_BuiltInMethod.CSharp_BuiltinMethods.CSharp_BuiltinNoArgs;
import com.eagle.programmar.CSharp.Expressions.CSharp_BuiltInMethod.CSharp_BuiltinMethods.CSharp_BuiltinOneArg;
import com.eagle.programmar.CSharp.Symbols.CSharp_Identifier_Reference;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CSharp_BuiltInMethod extends PrimaryOperator implements EagleRunnable
{
	public @S(10) CSharp_Identifier_Reference id;
	public @S(20) TokenList<CSharp_BuiltinMethods> builtIns;
	
	public static class CSharp_BuiltinMethods extends TokenChooser
	{
		public @CHOICE static class CSharp_BuiltinNoArgs extends TokenSequence
		{
			public @S(10) PunctuationPeriod dot;
			public @S(20) CSharp_Keyword builtin = new CSharp_Keyword("Length");
		}
		
		public @CHOICE static class CSharp_BuiltinOneArg extends TokenSequence
		{
			public @S(10) PunctuationPeriod dot;
			public @S(20) CSharp_KeywordChoice builtins = new CSharp_KeywordChoice("StartsWith", "Substring");
			public @S(30) PunctuationLeftParen leftParen;
			public @S(40) CSharp_Expression param;
			public @S(50) PunctuationRightParen rightParen;
		}
	}
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue leftVal = interpreter._symbolTable.findSymbol(id.getValue());
		String leftStr = leftVal.forceStringValue();
		String name = "";
		for (CSharp_BuiltinMethods builtin : builtIns._elements)
		{
			if (builtin.getWhich() instanceof CSharp_BuiltinOneArg)
			{
				CSharp_BuiltinOneArg oneArg = (CSharp_BuiltinOneArg) builtin.getWhich();
				name = oneArg.builtins.getValue();
				switch (name)
				{
				case "StartsWith":
					String patt = interpreter.getStrValue(oneArg.param);
					interpreter.pushBool(leftStr.startsWith(patt));
					return;
				case "Substring":
					int sc = interpreter.getIntValue(oneArg.param);
					interpreter.pushStr(leftStr.substring(sc));
					continue;
				}
			}
			else if (builtin.getWhich() instanceof CSharp_BuiltinNoArgs)
			{
				CSharp_BuiltinNoArgs noArgs = (CSharp_BuiltinNoArgs) builtin.getWhich();
				name = noArgs.builtin.getValue();
				switch (name)
				{
				case "Length":
					interpreter.pushInt(leftStr.length());
					return;
				}
			}
		}
		
		throw new RuntimeException("Can't handle BuiltIn method: " + name);
	}
}
