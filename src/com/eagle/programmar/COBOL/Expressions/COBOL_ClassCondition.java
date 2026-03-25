// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.COBOL.Expressions;

import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.RelationalEnum;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class COBOL_ClassCondition extends PrecedenceOperator
		implements EagleTransformableExpression
{
	public @S(10) COBOL_Expression expr = new COBOL_Expression(this, AllowedPrecedence.ATLEAST);
	// public @S(10) COBOL_Variable var; // Cannot use a COBOL_Expression here --
	// infinite loop
	public @S(20) @OPT COBOL_Keyword IS = new COBOL_Keyword("IS");
	public @S(30) @OPT COBOL_Keyword NOT = new COBOL_Keyword("NOT");
	public @S(40) COBOL_KeywordChoice type = new COBOL_KeywordChoice("ALPHABETIC", "ALPHABETIC-LOWER",
			"ALPHABETIC-UPPER", "NEGATIVE", "NUMERIC", "POSITIVE", "ZERO");

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression theExpr = transformer.transformExpression(generator, expr);

		String oper = type.getValue();
		RelationalEnum newOper;

		if (NOT.isPresent())
		{
			switch (oper.toUpperCase())
			{
			case "NEGATIVE":
				newOper = RelationalEnum.GREATER_EQUALS;
				break;
			case "POSITIVE":
				newOper = RelationalEnum.LESS_EQUALS;
				break;
			case "ZERO":
				newOper = RelationalEnum.NOT_EQUALS;
				break;
			default:
				throw new RuntimeException("Unexpected relational operator: NOT " + oper);
			}
		}
		else
		{
			switch (oper.toUpperCase())
			{
			case "NEGATIVE":
				newOper = RelationalEnum.LESS_THAN;
				break;
			case "POSITIVE":
				newOper = RelationalEnum.GREATER_THAN;
				break;
			case "ZERO":
				newOper = RelationalEnum.EQUALS;
				break;
			default:
				throw new RuntimeException("Unexpected relational operator: " + oper);
			}
		}
		AbstractExpression zero = generator.newNumberExpression("0", null);
		Oper2Types types = new Oper2Types(TypeEnum.INTEGER, TypeEnum.INTEGER);
		return generator.newRelationalExpression(types, theExpr, newOper, zero, this);
	}
}