// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 11, 2024

namespace com.eagle.programmar.Ada.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleString = com.eagle.math.EagleString;
	using EagleValue = com.eagle.math.EagleValue;
	using Ada_Expression = com.eagle.programmar.Ada.Ada_Expression;
	using Ada_VariableExpression = com.eagle.programmar.Ada.Expressions.Ada_VariableExpression;
	using Ada_Identifier_Reference = com.eagle.programmar.Ada.Symbols.Ada_Identifier_Reference;
	using Ada_Keyword = com.eagle.programmar.Ada.Terminals.Ada_Keyword;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using AssignmentEnum = com.eagle.transform.EagleGenerator.AssignmentEnum;
	using SubscriptEnum = com.eagle.transform.EagleGenerator.SubscriptEnum;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Ada_AppendStatement : TokenSequence, EagleRunnable, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Ada.Terminals.Ada_Keyword APPEND = new com.eagle.programmar.Ada.Terminals.Ada_Keyword("Append");
		public Ada_Keyword APPEND = new Ada_Keyword("Append");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Ada.Expressions.Ada_VariableExpression varExpr;
		public Ada_VariableExpression varExpr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationComma comma;
		public PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.Ada.Ada_Expression expr;
		public Ada_Expression expr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
		public PunctuationSemicolon semicolon;

		public override void interpret(EagleInterpreter interpreter)
		{
			Ada_Identifier_Reference id = varExpr.variable.vars.first();
			EagleValue var = interpreter.findSymbol(id.ToString());
			string val = interpreter.getStrValue(expr);
			EagleString v = new EagleString(var.forceStringValue() + val);
			interpreter.setSymbol(varExpr, id.getValue(), v);
		}

		public override AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			string varName = varExpr.variable.vars.first().getValue();
			AbstractExpression theVarExpr = generator.newVariableExpression(varName, EagleGenerator.SubscriptEnum.FIRST_IS_ONE, null, varExpr);
			AbstractExpression theExpr = transformer.transformExpression(generator, expr);
			AbstractExpression appExpr = generator.newAppendExpression(null, theVarExpr, theExpr, this);
			AbstractExpression asgExpr = generator.newAssignmentExpression(varName, null, null, EagleGenerator.AssignmentEnum.EQUALS, appExpr, this);
			return generator.newExpressionStatement(asgExpr, this);
		}
	}

}
