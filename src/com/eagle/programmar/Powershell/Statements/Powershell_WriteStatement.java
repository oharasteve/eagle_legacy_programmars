// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 29, 2022

package com.eagle.programmar.Powershell.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Powershell.Powershell_Expression;
import com.eagle.programmar.Powershell.Terminals.Powershell_Keyword;
import com.eagle.programmar.Powershell.Terminals.Powershell_KeywordChoice;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationComma;

public class Powershell_WriteStatement extends TokenSequence implements EagleRunnable, AbstractStatement
{
	public @S(10) Powershell_KeywordChoice WRITE = new Powershell_KeywordChoice("Write-Host", "Write-Output");
	public @S(20) @OPT TokenList<Powershell_WriteOption> options1;
	public @S(30) SeparatedList<Powershell_Expression,PunctuationComma> exprs;
	public @S(40) @OPT TokenList<Powershell_WriteOption> options2;

	public static class Powershell_WriteOption extends TokenChooser
	{
		public @CHOICE Powershell_KeywordChoice NONEWLINE = new Powershell_KeywordChoice("-NoNewLine");

		public @CHOICE static class Powershell_WriteOptionColor extends TokenSequence
		{
			public @S(10) Powershell_Keyword FGColor = new Powershell_Keyword("-ForegroundColor");
			public @S(20) Powershell_KeywordChoice COLOR = new Powershell_KeywordChoice("Green", "Yellow");
		}
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		for (int i = 0; i < exprs.getPrimaryCount(); i++)
		{
			String result = interpreter.getStrValue(exprs.getPrimaryElement(i));
			System.out.println(result);
		}
	}
}
