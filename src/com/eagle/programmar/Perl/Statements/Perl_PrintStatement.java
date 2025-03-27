// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 17, 2011

package com.eagle.programmar.Perl.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Symbols.Perl_Variable_Definition;
import com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice;
import com.eagle.programmar.Perl.Terminals.Perl_Literal;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationComma;

public class Perl_PrintStatement extends TokenSequence implements EagleRunnable, AbstractStatement
{
	public @S(10) Perl_KeywordChoice PRINT = new Perl_KeywordChoice("print", "printf");
	public @S(20) Perl_PrintWhat what;
	
	public static class Perl_PrintWhat extends TokenChooser
	{
		public @FIRST Perl_PrintRedirectInput XXredirectInput;
		public @CHOICE Perl_PrintNormal XXprintNormal;
	}

	public static class Perl_PrintRedirectInput extends TokenSequence
	{
		public @S(10) Perl_Variable_Definition id;
		public @S(20) Perl_Literal multiline;   // With << or <<< to redirect stdin
	}

	public static class Perl_PrintNormal extends TokenSequence
	{
		public @S(10) SeparatedList<Perl_Expression, PunctuationComma> strings;
		public @S(20) @OPT @CURIOUS("Extra comma") PunctuationComma comma;
	}
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (what.getWhich() instanceof Perl_PrintNormal)
		{
			Perl_PrintNormal prt = (Perl_PrintNormal) what.getWhich();
			for (AbstractToken expr : prt.strings._elements)
			{
				String item = interpreter.getStrValue(expr);
				System.out.print(item.replace("\\n", "\n"));
			}
		}
	}
}
