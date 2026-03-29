// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 18, 2011

namespace com.eagle.programmar.PLI.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using PLI_Expression = com.eagle.programmar.PLI.PLI_Expression;
	using PLI_Label = com.eagle.programmar.PLI.PLI_Label;
	using PLI_Subscript = com.eagle.programmar.PLI.PLI_Subscript;
	using PLI_Identifier_Reference = com.eagle.programmar.PLI.Symbols.PLI_Identifier_Reference;
	using PLI_Comment = com.eagle.programmar.PLI.Terminals.PLI_Comment;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using AssignmentEnum = com.eagle.transform.EagleGenerator.AssignmentEnum;
	using SubscriptEnum = com.eagle.transform.EagleGenerator.SubscriptEnum;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class PLI_AssignmentStatement : TokenSequence, AbstractStatement, EagleRunnable, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT PLI_Label label;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.PLI.Symbols.PLI_Identifier_Reference var;
		public PLI_Identifier_Reference var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT PLI_Subscript subscript;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationEquals equals;
		public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.PLI.PLI_Expression expression;
		public PLI_Expression expression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT PLI_Comment comment;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
		public PunctuationSemicolon semicolon;

		public override void interpret(EagleInterpreter interpreter)
		{
			if (subscript != null && subscript.isPresent())
			{
				throw new Exception("Can't handle subscripts yet");
			}

			EagleValue val = interpreter.getEagleValue(expression);
			interpreter.setSymbol(var, var.getValue(), val);
		}

		public override AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression subscrExpr = null;
			if (subscript != null && subscript.isPresent())
			{
				throw new Exception("Can't handle subscripts yet");
			}

			AbstractExpression value = transformer.transformExpression(generator, expression);
			AbstractExpression asgExpr = generator.newAssignmentExpression(var.getValue(), EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, subscrExpr, EagleGenerator.AssignmentEnum.EQUALS, value, this);
			return generator.newExpressionStatement(asgExpr, this);
		}
	}

}
