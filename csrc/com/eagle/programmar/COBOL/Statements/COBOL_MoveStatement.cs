// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2010

namespace com.eagle.programmar.COBOL.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using COBOL_AbstractStatement = com.eagle.programmar.COBOL.COBOL_AbstractStatement;
	using COBOL_Expression = com.eagle.programmar.COBOL.COBOL_Expression;
	using COBOL_Subscript = com.eagle.programmar.COBOL.COBOL_Subscript;
	using COBOL_Variable = com.eagle.programmar.COBOL.COBOL_Variable;
	using COBOL_UserVariable = com.eagle.programmar.COBOL.COBOL_Variable.COBOL_UserVariable;
	using COBOL_Comment = com.eagle.programmar.COBOL.Terminals.COBOL_Comment;
	using COBOL_Keyword = com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using AssignmentEnum = com.eagle.transform.EagleGenerator.AssignmentEnum;
	using SubscriptEnum = com.eagle.transform.EagleGenerator.SubscriptEnum;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class COBOL_MoveStatement : COBOL_AbstractStatement, EagleRunnable, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("rlpsmove.htm") com.eagle.programmar.COBOL.Terminals.COBOL_Keyword MOVE = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("MOVE");
		public @DOC("rlpsmove.htm") COBOL_Keyword MOVE = new COBOL_Keyword("MOVE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT COBOL_Keyword ALL = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("ALL");
		public @OPT COBOL_Keyword ALL = new COBOL_Keyword("ALL");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.COBOL.COBOL_Expression expr;
		public COBOL_Expression expr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword TO = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("TO");
		public COBOL_Keyword TO = new COBOL_Keyword("TO");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT COBOL_Variable var;
		public @OPT COBOL_Variable var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT TokenList<COBOL_MoveMore> more;
		public @OPT TokenList<COBOL_MoveMore> more;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT @CURIOUS("MOVE: Extra comma") com.eagle.tokens.punctuation.PunctuationComma comma;
		public @OPT PunctuationComma comma;

		public static class COBOL_MoveMore extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT PunctuationComma comma;
			public @OPT PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<com.eagle.programmar.COBOL.Terminals.COBOL_Comment> comments;
			public @OPT TokenList<COBOL_Comment> comments;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.COBOL.COBOL_Variable var;
			public COBOL_Variable var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT COBOL_Subscript subscript;
			public @OPT COBOL_Subscript subscript;
		}

		public void interpret(EagleInterpreter interpreter)
		{
			if (ALL.isPresent())
			{
				throw new Exception("Can't handle MOVE ALL yet");
			}
			if (more != null && more.isPresent() && more.size() > 0)
			{
				throw new Exception("Can't handle multiple MOVEs yet");
			}

			EagleValue val = interpreter.getEagleValue(expr);
			AbstractToken which = var.getWhich();
			if (!(which is COBOL_Variable.COBOL_UserVariable))
			{
				throw new Exception("Unable to handle " + which);
			}
			COBOL_Variable.COBOL_UserVariable variable = (COBOL_Variable.COBOL_UserVariable) which;
			interpreter.setSymbol(variable, variable.id.getValue(), val);
		}

		public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			if (ALL != null && ALL.isPresent())
			{
				throw new Exception("Can't handle MOVE ALL now: " + this);
			}
			if (more != null && more.size() > 0)
			{
				throw new Exception("Can't handle MOVE TO many variables: " + this);
			}
			if (!(var.getWhich() is COBOL_Variable.COBOL_UserVariable))
			{
				throw new Exception("Can only ADD to a Variable: " + this);
			}
			COBOL_Variable.COBOL_UserVariable userVar = (COBOL_Variable.COBOL_UserVariable) var.getWhich();
			if (userVar.subscript != null && userVar.subscript.size() > 0)
			{
				throw new Exception("Can't handle subscripts here: " + this);
			}
			if (userVar.ofList != null && userVar.ofList.size() > 0)
			{
				throw new Exception("Can't handle field OF variable: " + this);
			}

			AbstractExpression value = transformer.transformExpression(generator, expr);
			AbstractExpression asgExpr = generator.newAssignmentExpression(COBOL_Variable.repairName(userVar.id.getValue()), EagleGenerator.SubscriptEnum.FIRST_IS_ONE, null, EagleGenerator.AssignmentEnum.EQUALS, value, this);
			AbstractStatement exprStmt = generator.newExpressionStatement(asgExpr, this);
			return exprStmt;
		}
	}

}
