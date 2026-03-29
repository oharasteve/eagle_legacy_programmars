// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 18, 2013

namespace com.eagle.programmar.Python.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleInteger = com.eagle.math.EagleInteger;
	using EagleValue = com.eagle.math.EagleValue;
	using Python_Expression = com.eagle.programmar.Python.Python_Expression;
	using Python_Subscript = com.eagle.programmar.Python.Python_Subscript;
	using Python_Type = com.eagle.programmar.Python.Python_Type;
	using Python_VariableList = com.eagle.programmar.Python.Python_VariableList;
	using Python_Just_Var = com.eagle.programmar.Python.Python_VariableList.Python_Just_Var;
	using Python_VariableAndSubscript = com.eagle.programmar.Python.Python_VariableList.Python_VariableAndSubscript;
	using Python_VariableOrList = com.eagle.programmar.Python.Python_VariableList.Python_VariableOrList;
	using Python_Assignment_Expression = com.eagle.programmar.Python.Expressions.Python_Assignment_Expression;
	using Python_VariableExpression = com.eagle.programmar.Python.Expressions.Python_VariableExpression;
	using Python_Identifier_Reference = com.eagle.programmar.Python.Symbols.Python_Identifier_Reference;
	using Python_Comment = com.eagle.programmar.Python.Terminals.Python_Comment;
	using Python_Keyword = com.eagle.programmar.Python.Terminals.Python_Keyword;
	using Python_PunctuationChoice = com.eagle.programmar.Python.Terminals.Python_PunctuationChoice;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using AssignmentEnum = com.eagle.transform.EagleGenerator.AssignmentEnum;
	using SubscriptEnum = com.eagle.transform.EagleGenerator.SubscriptEnum;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Python_Assignment : TokenSequence, EagleRunnable, AbstractStatement, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @NOSPACE Python_VariableList varList;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<com.eagle.programmar.Python.Python_Subscript> subscripts;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Python_ResultType resultType;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Python.Terminals.Python_PunctuationChoice operator = new com.eagle.programmar.Python.Terminals.Python_PunctuationChoice("=", "+=", "-=", "*=", "/=", "%=", "&=", "|=", "^=", "<<=", ">>=", "**=", "//=");
		public Python_PunctuationChoice @operator = new Python_PunctuationChoice("=", "+=", "-=", "*=", "/=", "%=", "&=", "|=", "^=", "<<=", ">>=", "**=", "//=");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT Python_Keyword AWAIT = new com.eagle.programmar.Python.Terminals.Python_Keyword("await");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.Python.Python_Expression expression;
		public Python_Expression expression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT TokenList<Python_MoreAsgExpressions> moreExpressions;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) @OPT Python_Comment comment;
		public  OPT;

		public class Python_MoreAsgExpressions : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationComma comma;
			public PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Python_Expression expr;
			public  OPT;
		}

		public class Python_ResultType : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationColon colon;
			public PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Python.Python_Type type;
			public Python_Type type;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			Python_VariableList.Python_VariableOrList vars = varList.vars.first();
			if (!(vars.getWhich() is Python_VariableList.Python_Just_Var))
			{
				throw new Exception("Unexpected assignment variable: " + vars.getWhich());
			}
			Python_VariableList.Python_Just_Var justVar = (Python_VariableList.Python_Just_Var) vars.getWhich();
			Python_VariableList.Python_VariableAndSubscript var = justVar.variable.first();

			if (var.variable.var.getWhich() is Python_Identifier_Reference)
			{
				Python_Identifier_Reference id = (Python_Identifier_Reference) var.variable.var.getWhich();
				switch (@operator.getValue())
				{
				case "=":
					EagleValue val = interpreter.getEagleValue(expression);
					interpreter.setSymbol(var, id.getValue(), val);
					break;
				case "+=":
					int newVal1 = interpreter.getIntValue(expression);
					EagleValue oldVar1 = interpreter.findSymbol(id.ToString());
					EagleInteger newValue1 = new EagleInteger(oldVar1.forceIntegerValue() + newVal1);
					interpreter.setSymbol(var, id.getValue(), newValue1);
					break;
				case "-=":
					int newVal2 = interpreter.getIntValue(expression);
					EagleValue oldVar2 = interpreter.findSymbol(id.ToString());
					EagleInteger newValue2 = new EagleInteger(oldVar2.forceIntegerValue() - newVal2);
					interpreter.setSymbol(var, id.getValue(), newValue2);
					break;
				default:
					throw new Exception("Unexpected assignment operator: " + @operator.getValue());
				}
			}
		}

		public override AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			Python_VariableList.Python_VariableOrList vars = varList.vars.first();
			if (!(vars.getWhich() is Python_VariableList.Python_Just_Var))
			{
				throw new Exception("Unexpected assignment variable: " + vars.getWhich());
			}
			Python_VariableList.Python_Just_Var justVar = (Python_VariableList.Python_Just_Var) vars.getWhich();
			Python_VariableList.Python_VariableAndSubscript var = justVar.variable.first();

			if (!(var.variable.var.getWhich() is Python_Identifier_Reference))
			{
				throw new Exception("Assigment must be to a variable");
			}
			Python_Identifier_Reference id = (Python_Identifier_Reference) var.variable.var.getWhich();
			EagleGenerator.AssignmentEnum asg;
			switch (@operator.getValue())
			{
			case "=":
				asg = EagleGenerator.AssignmentEnum.EQUALS;
				break;
			case "+=":
				asg = EagleGenerator.AssignmentEnum.PLUS_EQUALS;
				break;
			case "-=":
				asg = EagleGenerator.AssignmentEnum.MINUS_EQUALS;
				break;
			default:
				throw new Exception("Unexpected assignment operator: " + @operator.getValue());
			}

			AbstractExpression subscrExpr = null;
			AbstractExpression value = transformer.transformExpression(generator, expression);
			AbstractExpression asgExpr = generator.newAssignmentExpression(id.getValue(), EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, subscrExpr, asg, value, this);
			return generator.newExpressionStatement(asgExpr, this);
		}

		public static Python_ExpressionStatement generateAssignment(string name, EagleGenerator.SubscriptEnum offset, Python_Expression subscript, EagleGenerator.AssignmentEnum oper, Python_Expression expression, string comment, AbstractToken source)
		{
			if (oper != EagleGenerator.AssignmentEnum.EQUALS)
			{
				throw new Exception("Unexpected assigment operator: " + oper.ToString());
			}

			Python_Assignment_Expression asgExpr = new Python_Assignment_Expression();
			asgExpr.left = Python_VariableExpression.generateVariableExpression(name, offset, subscript, source);
			asgExpr.@operator = new Python_PunctuationChoice("=");
			asgExpr.right = expression;

			Python_ExpressionStatement exprStmt = new Python_ExpressionStatement();
			Python_Expression expr = new Python_Expression();
			expr.setWhich(asgExpr);
			exprStmt.expression = expr;
			if (!string.ReferenceEquals(comment, null))
			{
				exprStmt.comment = new Python_Comment(comment);
			}

			return exprStmt;
		}
	}

}
