// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 10, 2010

namespace com.eagle.programmar.COBOL.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleInteger = com.eagle.math.EagleInteger;
	using EagleValue = com.eagle.math.EagleValue;
	using COBOL_AbstractStatement = com.eagle.programmar.COBOL.COBOL_AbstractStatement;
	using COBOL_Expression = com.eagle.programmar.COBOL.COBOL_Expression;
	using COBOL_Variable = com.eagle.programmar.COBOL.COBOL_Variable;
	using COBOL_UserVariable = com.eagle.programmar.COBOL.COBOL_Variable.COBOL_UserVariable;
	using COBOL_Keyword = com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenChooser = com.eagle.tokens.TokenChooser;
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

	public class COBOL_SubtractStatement : COBOL_AbstractStatement, EagleRunnable, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("rlpssubt.htm") com.eagle.programmar.COBOL.Terminals.COBOL_Keyword SUBTRACT = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("SUBTRACT");
		public @DOC("rlpssubt.htm") COBOL_Keyword SUBTRACT = new COBOL_Keyword("SUBTRACT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.COBOL_Expression expr;
		public COBOL_Expression expr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword FROM = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("FROM");
		public COBOL_Keyword FROM = new COBOL_Keyword("FROM");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) COBOL_SubtractType type;
		public COBOL_SubtractType type;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT COBOL_Keyword ROUNDED = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("ROUNDED");
		public @OPT COBOL_Keyword ROUNDED = new COBOL_Keyword("ROUNDED");

		public static class COBOL_SubtractMoreVars extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT PunctuationComma comma;
			public @OPT PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.COBOL_Variable var;
			public COBOL_Variable var;
		}

		public static class COBOL_SubtractType extends TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @FIRST COBOL_SubtractWithGiving XXwithGiving;
			public COBOL_SubtractWithGiving XXwithGiving;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_SubtractNoGiving XXnoGiving;
			public COBOL_SubtractNoGiving XXnoGiving;
		}

		public static class COBOL_SubtractNoGiving extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.COBOL_Variable var;
			public COBOL_Variable var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<COBOL_SubtractMoreVars> moreVars;
			public @OPT TokenList<COBOL_SubtractMoreVars> moreVars;
		}

		public static class COBOL_SubtractWithGiving extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.COBOL_Expression expr;
			public COBOL_Expression expr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<COBOL_SubtractMoreExprs> moreExprs;
			public @OPT TokenList<COBOL_SubtractMoreExprs> moreExprs;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword GIVING = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("GIVING");
			public COBOL_Keyword GIVING = new COBOL_Keyword("GIVING");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.COBOL.COBOL_Variable result;
			public COBOL_Variable result;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT TokenList<COBOL_SubtractMoreVars> moreVars;
			public @OPT TokenList<COBOL_SubtractMoreVars> moreVars;

			public static class COBOL_SubtractMoreExprs extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT PunctuationComma comma;
				public @OPT PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.COBOL_Expression expr;
				public COBOL_Expression expr;
			}
		}

		public void interpret(EagleInterpreter interpreter)
		{
			AbstractToken which = type.getWhich();
			if (!(which is COBOL_SubtractNoGiving))
			{
				throw new Exception("Cannot handle " + which + " yet");
			}
			COBOL_SubtractNoGiving noGiving = (COBOL_SubtractNoGiving) which;
			if (noGiving.moreVars != null && noGiving.moreVars.isPresent() && noGiving.moreVars.size() > 0)
			{
				throw new Exception("Cannot handle multiple expressions yet");
			}

			AbstractToken which2 = noGiving.var.getWhich();
			EagleValue val = interpreter.getEagleValue(expr);
			int newVal = val.forceIntegerValue();
			if (which2 is COBOL_Variable.COBOL_UserVariable)
			{
				COBOL_Variable.COBOL_UserVariable variable = (COBOL_Variable.COBOL_UserVariable) which2;
				EagleValue oldValue = interpreter.findSymbol(variable.id.getValue());
				interpreter.setSymbol(variable, variable.id.getValue(), new EagleInteger(oldValue.forceIntegerValue() - newVal));
			}
		}

		public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			if (!(type.getWhich() is COBOL_SubtractNoGiving))
			{
				throw new Exception("Can't handle SUBTRACT with GIVING: " + this);
			}
			COBOL_SubtractNoGiving subtractNoGiving = (COBOL_SubtractNoGiving) type.getWhich();
			if (subtractNoGiving.moreVars != null && subtractNoGiving.moreVars.size() > 0)
			{
				throw new Exception("Can't handle multiple arguments to SUBTRACT: " + this);
			}

			COBOL_Variable var = subtractNoGiving.var;
			if (!(var.getWhich() is COBOL_Variable.COBOL_UserVariable))
			{
				throw new Exception("Can only SUBTRACT from a Variable: " + this);
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
			AbstractExpression asgExpr = generator.newAssignmentExpression(userVar.id.getValue(), EagleGenerator.SubscriptEnum.FIRST_IS_ONE, null, EagleGenerator.AssignmentEnum.MINUS_EQUALS, value, this);
			AbstractStatement exprStmt = generator.newExpressionStatement(asgExpr, this);
			return exprStmt;
		}
	}

}
