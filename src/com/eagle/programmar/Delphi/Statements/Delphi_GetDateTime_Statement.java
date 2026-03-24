// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.Delphi.Statements;

import com.eagle.programmar.Delphi.Delphi_Variable;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.AssignmentEnum;
import com.eagle.transform.EagleGenerator.SubscriptEnum;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Delphi_GetDateTime_Statement extends TokenSequence
		implements AbstractStatement, EagleTransformableStatement
{
	public @S(10) Delphi_Keyword GETDATETIME = new Delphi_Keyword("GetDateTime");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) Delphi_Variable var;
	public @S(40) PunctuationRightParen rightParen;

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		String varName = var.var.getValue();
		AbstractExpression getDateTime = generator.newCurrentDatetime();
		AbstractExpression asgExp = generator.newAssignmentExpression(varName,
				SubscriptEnum.FIRST_IS_ZERO, null, AssignmentEnum.EQUALS, getDateTime, var);
		return generator.newExpressionStatement(asgExp, this);
	}
}
