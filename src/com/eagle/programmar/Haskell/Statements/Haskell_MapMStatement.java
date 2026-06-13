// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 12, 2026

package com.eagle.programmar.Haskell.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Haskell.Haskell_Expression;
import com.eagle.programmar.Haskell.Haskell_Variable;
import com.eagle.programmar.Haskell.Expressions.Haskell_RangeExpression;
import com.eagle.programmar.Haskell.Symbols.Haskell_Identifier_Reference;
import com.eagle.programmar.Haskell.Terminals.Haskell_Keyword;
import com.eagle.programmar.Haskell.Terminals.Haskell_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationBackSlash;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Haskell_MapMStatement extends TokenSequence
		implements EagleRunnable // , EagleTransformableStatementList
{
	// mapM_ (\k -> testFromRoman (snd (toRoman k)) k False) [1..3999]
	public @S(10) Haskell_Keyword MAPM = new Haskell_Keyword("mapM_");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) PunctuationBackSlash backSlash;
	public @S(40) Haskell_Variable variable;
	public @S(50) Haskell_Punctuation arrow = new Haskell_Punctuation("->");
	public @S(60) Haskell_Identifier_Reference funcName;
	public @S(70) TokenList<Haskell_Expression> arguments;
	public @S(80) PunctuationRightParen rightParen;
	public @S(90) Haskell_RangeExpression rangeExpr;
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		throw new RuntimeException("Need to implement");
	}
}
