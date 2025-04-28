// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 22, 2010

package com.eagle.programmar.Java.Statements;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.Statements.Eagle_Generate_Break;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.programmar.Java.Java_Generator;
import com.eagle.programmar.Java.Java_Statement;
import com.eagle.programmar.Java.Symbols.Java_Identifier_Reference;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Java_BreakStatement extends TokenSequence
		implements EagleRunnableWithResult, AbstractStatement,
				EagleTransformableStatement, Eagle_Generate_Break<Java_Statement>
{
	public @S(10) @NEWLINE @DOC("statements.html#14.15") Java_Keyword BREAK = new Java_Keyword("break");
	public @S(20) @OPT Java_Identifier_Reference label;
	public @S(30) @NOSPACE PunctuationSemicolon semicolon;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		return Eagle_Statement_Result.BREAK;
	}
	
	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator generator)
	{
		return generator.newBreakStatement(this);
	}
	
	@Override
	public Java_Statement generateBreak(AbstractToken source)
	{
		this.setTransformationSource(source);
		return Java_Generator.wrapStatement(this);
	}
}
