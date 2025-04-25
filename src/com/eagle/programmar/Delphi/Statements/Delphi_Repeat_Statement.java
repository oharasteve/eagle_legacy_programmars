// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.Delphi.Statements;

import java.util.ArrayList;

import com.eagle.generate.EagleGenerator;
import com.eagle.programmar.Delphi.Delphi_Expression;
import com.eagle.programmar.Delphi.Delphi_Statement_List;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Delphi_Repeat_Statement extends TokenSequence
		implements AbstractStatement, EagleTransformableStatement
{
	public @S(10) @DOC("Declarations_and_Statements_(Delphi)#Repeat_Statements") Delphi_Keyword REPEAT = new Delphi_Keyword(
			"Repeat");
	public @S(20) Delphi_Statement_List statements;
	public @S(30) Delphi_Keyword UNTIL = new Delphi_Keyword("Until");
	public @S(40) Delphi_Expression condition;
	
	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator generator)
	{
		AbstractExpression cond = transformer.transformExpression(generator, condition);
		ArrayList<AbstractStatement> action = transformer.transformStatement(generator,
				statements);
		return generator.newDoUntilStatement(cond, action, this);
	}
}
