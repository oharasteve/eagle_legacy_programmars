// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

namespace com.eagle.programmar.COBOL.Expressions
{
	using Oper2Types = com.eagle.metrics.Operator2Metrics.Oper2Types;
	using COBOL_Expression = com.eagle.programmar.COBOL.COBOL_Expression;
	using COBOL_Keyword = com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
	using COBOL_KeywordChoice = com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using RelationalEnum = com.eagle.transform.EagleGenerator.RelationalEnum;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class COBOL_ClassCondition : PrecedenceOperator, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.COBOL_Expression expr = new com.eagle.programmar.COBOL.COBOL_Expression(this, AllowedPrecedence.ATLEAST);
		public COBOL_Expression expr = new COBOL_Expression(this, AllowedPrecedence.ATLEAST);
		// public @S(10) COBOL_Variable var; // Cannot use a COBOL_Expression here --
		// infinite loop
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT COBOL_Keyword IS = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("IS");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT COBOL_Keyword NOT = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("NOT");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice type = new com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice("ALPHABETIC", "ALPHABETIC-LOWER", "ALPHABETIC-UPPER", "NEGATIVE", "NUMERIC", "POSITIVE", "ZERO");
		public COBOL_KeywordChoice type = new COBOL_KeywordChoice("ALPHABETIC", "ALPHABETIC-LOWER", "ALPHABETIC-UPPER", "NEGATIVE", "NUMERIC", "POSITIVE", "ZERO");

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression theExpr = transformer.transformExpression(generator, expr);

			string oper = type.getValue();
			EagleGenerator.RelationalEnum newOper;

			if (NOT.isPresent())
			{
				switch (oper.ToUpper())
				{
				case "NEGATIVE":
					newOper = EagleGenerator.RelationalEnum.GREATER_EQUALS;
					break;
				case "POSITIVE":
					newOper = EagleGenerator.RelationalEnum.LESS_EQUALS;
					break;
				case "ZERO":
					newOper = EagleGenerator.RelationalEnum.NOT_EQUALS;
					break;
				default:
					throw new Exception("Unexpected relational operator: NOT " + oper);
				}
			}
			else
			{
				switch (oper.ToUpper())
				{
				case "NEGATIVE":
					newOper = EagleGenerator.RelationalEnum.LESS_THAN;
					break;
				case "POSITIVE":
					newOper = EagleGenerator.RelationalEnum.GREATER_THAN;
					break;
				case "ZERO":
					newOper = EagleGenerator.RelationalEnum.EQUALS;
					break;
				default:
					throw new Exception("Unexpected relational operator: " + oper);
				}
			}
			AbstractExpression zero = generator.newNumberExpression("0", null);
			Oper2Types types = new Oper2Types(EagleGenerator.TypeEnum.INTEGER, EagleGenerator.TypeEnum.INTEGER);
			return generator.newRelationalExpression(types, theExpr, newOper, zero, this);
		}
	}
}
