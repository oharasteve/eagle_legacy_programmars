// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 22, 2010

package com.eagle.programmar.CSharp.Statements;

import com.eagle.generate.Statements.Eagle_Generate_Break;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.programmar.CSharp.CSharp_Generator;
import com.eagle.programmar.CSharp.CSharp_Statement;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class CSharp_BreakStatement extends TokenSequence
		implements EagleRunnableWithResult, AbstractStatement,
				Eagle_Generate_Break<CSharp_Statement>
{
	public @S(10) @NEWLINE @OPT CSharp_Keyword YIELD = new CSharp_Keyword("yield");
	public @S(20) @DOC("statements.html#14.15") CSharp_Keyword BREAK = new CSharp_Keyword("break");
	public @S(30) @NOSPACE PunctuationSemicolon semicolon;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		return Eagle_Statement_Result.BREAK;
	}
	
	@Override
	public CSharp_Statement generateBreak(AbstractToken source)
	{
		this.semicolon = new PunctuationSemicolon();
		this.setTransformationSource(source);
		return CSharp_Generator.wrapStatement(this);
	}
}
