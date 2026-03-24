// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 17, 2022

package com.eagle.programmar.CSharp.Directives;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice;
import com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class CSharp_PragmaDirective extends TokenSequence
		implements EagleRunnable, EagleTransformableStatement
{
	public @S(10) CSharp_Punctuation pound1 = new CSharp_Punctuation('#');
	public @S(20) CSharp_Keyword PRAGMA = new CSharp_Keyword("pragma");
	public @S(30) CSharp_Keyword WARNING = new CSharp_Keyword("warning");
	public @S(40) CSharp_KeywordChoice DISABLE = new CSharp_KeywordChoice("disable", "restore");
	public @S(50) SeparatedList<CSharp_Expression, PunctuationComma> numbers;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// Nothing important here
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		// Nothing important here
		return null;
	}
}
