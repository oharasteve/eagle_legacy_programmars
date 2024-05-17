// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2022

package com.eagle.programmar.Ada.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.IntegerValue;
import com.eagle.programmar.Ada.Ada_Expression;
import com.eagle.programmar.Ada.Ada_Type;
import com.eagle.programmar.Ada.Symbols.Ada_Variable_Definition;
import com.eagle.programmar.Ada.Terminals.Ada_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Ada_Data extends TokenSequence implements EagleRunnable
{
	public @S(10) SeparatedList<Ada_Variable_Definition,PunctuationComma> ids;
	public @S(20) PunctuationColon colon;
	public @S(30) Ada_Type type;
	public @S(40) @OPT Ada_DataInitialValue init;
	public @S(50) PunctuationSemicolon semicolon;
	
	public static class Ada_DataInitialValue extends TokenSequence
	{
		public @S(10) Ada_Punctuation colonEquals = new Ada_Punctuation(":=");
		public @S(20) Ada_Expression value;
	}
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (init.isPresent())
		{
			int x = interpreter.getIntValue(init.value);
			IntegerValue val = new IntegerValue(x);
			Ada_Variable_Definition var = (Ada_Variable_Definition) ids.first();
			interpreter._symbolTable.setSymbol(var.getFileName(), var.getStartLine(),
					var.getStartChar(), var.getValue(), val);
		}
	}
}