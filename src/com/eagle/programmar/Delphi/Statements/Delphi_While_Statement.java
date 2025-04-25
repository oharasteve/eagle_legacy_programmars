// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 15, 2011

package com.eagle.programmar.Delphi.Statements;

import com.eagle.generate.EagleGenerator;
import com.eagle.programmar.Delphi.Delphi_Expression;
import com.eagle.programmar.Delphi.Delphi_Statement;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Delphi_While_Statement extends TokenSequence
		implements AbstractStatement, EagleTransformableStatement
{
	public @S(10) @DOC("Declarations_and_Statements_(Delphi)#While_Statements") Delphi_Keyword WHILE = new Delphi_Keyword(
			"While");
	public @S(20) Delphi_Expression condition;
	public @S(30) Delphi_Keyword DO = new Delphi_Keyword("Do");
	public @S(40) Delphi_Statement stmt;

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator generator)
	{
		AbstractExpression cond = transformer.transformExpression(generator, condition);
		AbstractStatement action = transformer.transformStatement1(generator, stmt);
		return generator.newWhileStatement1(cond, action, this);
	}
}
