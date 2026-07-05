// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 19, 2026

package com.eagle.programmar.Ada.Statements;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.PragmaEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.programmar.Ada.Terminals.Ada_Keyword;
import com.eagle.programmar.Ada.Terminals.Ada_KeywordChoice;
import com.eagle.programmar.Ada.Terminals.Ada_Literal;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Ada_PragmaStatement extends TokenSequence
		implements EagleRunnableWithResult, AbstractStatement, EagleTransformableStatement
{
	// pragma Warnings (Off, "unreachable code")
	public @S(10) Ada_Keyword PRAGMA = new Ada_Keyword("pragma");
	public @S(20) Ada_KeywordChoice WARNINGS = new Ada_KeywordChoice("Warnings");
	public @S(30) PunctuationLeftParen leftParen;
	public @S(40) Ada_KeywordChoice OFF = new Ada_KeywordChoice("Off");
	public @S(50) PunctuationComma comma;
	public @S(60) Ada_Literal literal;
	public @S(70) PunctuationRightParen rightParen;
	public @S(80) PunctuationSemicolon semicolon;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		return null;	// No action needed. Only care in transformation
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		return generator.newPragma(PragmaEnum.IGNORE_UNREACHABLE_CODE, this);
	}
}
