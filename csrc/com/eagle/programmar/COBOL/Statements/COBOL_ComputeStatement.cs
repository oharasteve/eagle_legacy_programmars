// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 11, 2010

namespace com.eagle.programmar.COBOL.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using COBOL_AbstractStatement = com.eagle.programmar.COBOL.COBOL_AbstractStatement;
	using COBOL_Expression = com.eagle.programmar.COBOL.COBOL_Expression;
	using COBOL_Subscript = com.eagle.programmar.COBOL.COBOL_Subscript;
	using COBOL_Variable = com.eagle.programmar.COBOL.COBOL_Variable;
	using COBOL_Modifiable_Identifier = com.eagle.programmar.COBOL.Symbols.COBOL_Modifiable_Identifier;
	using COBOL_Keyword = com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using AssignmentEnum = com.eagle.transform.EagleGenerator.AssignmentEnum;
	using SubscriptEnum = com.eagle.transform.EagleGenerator.SubscriptEnum;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class COBOL_ComputeStatement : COBOL_AbstractStatement, EagleRunnable, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("rlpscomp.htm") com.eagle.programmar.COBOL.Terminals.COBOL_Keyword COMPUTE = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("COMPUTE");
		public @DOC("rlpscomp.htm") COBOL_Keyword COMPUTE = new COBOL_Keyword("COMPUTE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Symbols.COBOL_Modifiable_Identifier var;
		public COBOL_Modifiable_Identifier var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT COBOL_Subscript subscript;
		public @OPT COBOL_Subscript subscript;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT COBOL_Keyword ROUNDED = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("ROUNDED");
		public @OPT COBOL_Keyword ROUNDED = new COBOL_Keyword("ROUNDED");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationEquals equals;
		public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.COBOL.COBOL_Expression expr;
		public COBOL_Expression expr;

		public void interpret(EagleInterpreter interpreter)
		{
			EagleValue val = interpreter.getEagleValue(expr);
			interpreter.setSymbol(var, var.getValue(), val);
		}

		public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			if (subscript != null && subscript.isPresent())
			{
				throw new Exception("Can't handle subscripts here: " + this);
			}

			AbstractExpression value = transformer.transformExpression(generator, expr);
			AbstractExpression asgExpr = generator.newAssignmentExpression(COBOL_Variable.repairName(var.getValue()), EagleGenerator.SubscriptEnum.FIRST_IS_ONE, null, EagleGenerator.AssignmentEnum.EQUALS, value, this);
			AbstractStatement exprStmt = generator.newExpressionStatement(asgExpr, this);
			return exprStmt;
		}
	}

}
