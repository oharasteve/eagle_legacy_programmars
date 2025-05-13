// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.Delphi;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Delphi.Symbols.Delphi_Identifier_Reference;
import com.eagle.programmar.Delphi.Terminals.Delphi_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.AbstractToken.OPT;
import com.eagle.tokens.TokenSequence.S;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Delphi_Variable extends TokenSequence implements EagleRunnable, AbstractVariable
{
	public @S(10) Delphi_Identifier_Reference var;
	public @S(20) @OPT TokenList<Delphi_Extended_Variable> extensions;

	public static class Delphi_Extended_Variable extends TokenChooser
	{
		public @CHOICE Delphi_DotName XXdotName;
		public @CHOICE Delphi_Subscript XXsubscript;
	}

	public static class Delphi_DotName extends TokenSequence
	{
		public @S(10) @OPT Delphi_Punctuation caret = new Delphi_Punctuation("^");
		public @S(20) PunctuationPeriod dot;
		public @S(30) Delphi_Identifier_Reference var;
	}

	public static class Delphi_Subscript extends TokenSequence
	{
		public @S(10) PunctuationLeftBracket leftBracket;
		public @S(20) SeparatedList<Delphi_Expression, PunctuationComma> exprs;
		public @S(30) PunctuationRightBracket rightBracket;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue value = interpreter.findSymbol(var.getValue());
		
		if (extensions != null)
		{
			for (Delphi_Extended_Variable ext : extensions._elements)
			{
				if (ext.getWhich() instanceof Delphi_Subscript)
				{
					EagleArray array = (EagleArray) value;
					Delphi_Subscript subscript = (Delphi_Subscript) ext.getWhich();
					int subscr = interpreter.getIntValue(subscript.exprs.first());
					EagleValue val = array.getValue(subscr);
					interpreter.pushEagleValue(val);
					return;
				}
			}
		}

		interpreter.pushEagleValue(value);
	}

	public static Delphi_Variable newVariable(String name)
	{
		Delphi_Variable var = new Delphi_Variable();
		Delphi_Extended_Variable extVar = new Delphi_Extended_Variable();
		Delphi_DotName dotName = new Delphi_DotName();
		dotName.dot = new PunctuationPeriod();
		Delphi_Identifier_Reference id = new Delphi_Identifier_Reference();
		id.setValue(name);
		dotName.var = id;
		extVar.setWhich(dotName);

		var.extensions = new TokenList<Delphi_Extended_Variable>();
		var.extensions.addToken(extVar);
		return var;
	}
}
