// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 16, 2013

package com.eagle.programmar.Delphi.Statements;

import com.eagle.generate.EagleGenerator;
import com.eagle.programmar.Delphi.Symbols.Delphi_Identifier_Reference;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Delphi_Close_Statement extends TokenSequence
		implements AbstractStatement, EagleTransformableStatement
{
	public @S(10) @DOC("Standard_Routines_and_Input-Output#The_Close_function") Delphi_Keyword CLOSE = new Delphi_Keyword(
			"Close");
	public @S(20) @OPT Delphi_CloseParams params;

	public static class Delphi_CloseParams extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) Delphi_Identifier_Reference file;
		public @S(30) PunctuationRightParen rightParen;
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		String id = params.file.getValue();
		return generator.newFileCloseStatement(id, this);
	}
}
