// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.Delphi.Statements;

import com.eagle.programmar.Delphi.Symbols.Delphi_Identifier_Reference;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.programmar.Delphi.Terminals.Delphi_Literal;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Delphi_Rewrite_Statement extends TokenSequence
		implements AbstractStatement, EagleTransformableStatement
{
	public @S(10) @DOC("System.Rewrite") Delphi_Keyword REWRITE = new Delphi_Keyword("ReWrite");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) Delphi_Identifier_Reference file;
	public @S(40) PunctuationComma comma;
	public @S(50) Delphi_Literal fileName;
	public @S(60) PunctuationRightParen rightParen;

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator generator)
	{
		String id = this.file.getValue();
		String fname = this.fileName.getValue().replaceAll("'", "");
		return generator.newFileOpenStatement(id, fname, this);
	}
}
