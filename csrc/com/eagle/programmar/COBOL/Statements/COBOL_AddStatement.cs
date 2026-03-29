// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2010

namespace com.eagle.programmar.COBOL.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleInteger = com.eagle.math.EagleInteger;
	using EagleValue = com.eagle.math.EagleValue;
	using COBOL_AbstractStatement = com.eagle.programmar.COBOL.COBOL_AbstractStatement;
	using COBOL_Expression = com.eagle.programmar.COBOL.COBOL_Expression;
	using COBOL_Statement = com.eagle.programmar.COBOL.COBOL_Statement;
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
	using EagleTransformableStatementList = com.eagle.transform.EagleTransformableStatementList;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class COBOL_AddStatement : COBOL_AbstractStatement, EagleRunnable, EagleTransformableStatementList
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("rlpsadd.htm") com.eagle.programmar.COBOL.Terminals.COBOL_Keyword ADD = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("ADD");
		public @DOC("rlpsadd.htm") COBOL_Keyword ADD = new COBOL_Keyword("ADD");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) COBOL_AddType type;
		public COBOL_AddType type;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<COBOL_AddOnSizeError> onErrorList;
		public @OPT TokenList<COBOL_AddOnSizeError> onErrorList;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT COBOL_Keyword ENDADD = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("END-ADD");
		public @OPT COBOL_Keyword ENDADD = new COBOL_Keyword("END-ADD");

		public static class COBOL_AddType extends TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @FIRST COBOL_AddWithGiving XXaddWithGiving;
			public COBOL_AddWithGiving XXaddWithGiving;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_AddNoGiving XXaddNoGiving;
			public COBOL_AddNoGiving XXaddNoGiving;
		}

		public static class COBOL_AddWithGiving extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.COBOL_Expression expr;
			public COBOL_Expression expr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<COBOL_AddMoreExprs> moreExprs;
			public @OPT TokenList<COBOL_AddMoreExprs> moreExprs;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT COBOL_Keyword TO = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("TO");
			public @OPT COBOL_Keyword TO = new COBOL_Keyword("TO");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT COBOL_Expression toExpr;
			public @OPT COBOL_Expression toExpr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT @CURIOUS("Extra comma") com.eagle.tokens.punctuation.PunctuationComma comma;
			public @OPT PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword GIVING = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("GIVING");
			public COBOL_Keyword GIVING = new COBOL_Keyword("GIVING");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.tokens.TokenList<com.eagle.programmar.COBOL.COBOL_Variable> vars;
			public TokenList<COBOL_Variable> vars;
		}

		public static class COBOL_AddNoGiving extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.COBOL_Expression expr;
			public COBOL_Expression expr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<COBOL_AddMoreExprs> moreExprs;
			public @OPT TokenList<COBOL_AddMoreExprs> moreExprs;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT COBOL_AddTo addTo;
			public @OPT COBOL_AddTo addTo;

			public static class COBOL_AddTo extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT COBOL_Keyword TO = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("TO");
				public @OPT COBOL_Keyword TO = new COBOL_Keyword("TO");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.COBOL_Variable var;
				public COBOL_Variable var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<COBOL_AddMoreVars> moreVars;
				public @OPT TokenList<COBOL_AddMoreVars> moreVars;
			}
		}

		public static class COBOL_AddMoreVars extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT PunctuationComma comma;
			public @OPT PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.COBOL_Variable var;
			public COBOL_Variable var;
		}

		public static class COBOL_AddMoreExprs extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT PunctuationComma comma;
			public @OPT PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.COBOL_Expression expr;
			public COBOL_Expression expr;
		}

		public static class COBOL_AddOnSizeError extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT COBOL_Keyword NOT = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("NOT");
			public @OPT COBOL_Keyword NOT = new COBOL_Keyword("NOT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword ON = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("ON");
			public COBOL_Keyword ON = new COBOL_Keyword("ON");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword SIZE = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("SIZE");
			public COBOL_Keyword SIZE = new COBOL_Keyword("SIZE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword ERROR = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("ERROR");
			public COBOL_Keyword ERROR = new COBOL_Keyword("ERROR");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.TokenList<com.eagle.programmar.COBOL.COBOL_Statement> actions;
			public TokenList<COBOL_Statement> actions;
		}

		public void interpret(EagleInterpreter interpreter)
		{
			AbstractToken which = type.getWhich();
			if (!(which is COBOL_AddNoGiving))
			{
				throw new Exception("Cannot handle " + which + " yet");
			}
			COBOL_AddNoGiving noGiving = (COBOL_AddNoGiving) which;
			if (noGiving.moreExprs != null && noGiving.moreExprs.isPresent() && noGiving.moreExprs.size() > 0)
			{
				throw new Exception("Cannot handle multiple expressions yet");
			}

			AbstractToken which2 = noGiving.addTo.var.getWhich();
			EagleValue val = interpreter.getEagleValue(noGiving.expr);
			int newVal = val.forceIntegerValue();
			if (which2 is COBOL_Variable.COBOL_UserVariable)
			{
				COBOL_Variable.COBOL_UserVariable variable = (COBOL_Variable.COBOL_UserVariable) which2;
				EagleValue oldValue = interpreter.findSymbol(variable.id.getValue());
				interpreter.setSymbol(variable, variable.id.getValue(), new EagleInteger(newVal + oldValue.forceIntegerValue()));
			}
			if (noGiving.addTo.moreVars != null && noGiving.addTo.moreVars.isPresent())
			{
				foreach (COBOL_AddMoreVars more in noGiving.addTo.moreVars._elements)
				{
					AbstractToken which3 = more.var.getWhich();
					if (which3 is COBOL_Variable.COBOL_UserVariable)
					{
						COBOL_Variable.COBOL_UserVariable variable = (COBOL_Variable.COBOL_UserVariable) which3;
						EagleValue oldValue = interpreter.findSymbol(variable.id.getValue());
						interpreter.setSymbol(variable, variable.id.getValue(), new EagleInteger(oldValue.forceIntegerValue() + newVal));
					}
				}
			}
		}

		public List<AbstractStatement> transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			if (!(type.getWhich() is COBOL_AddNoGiving))
			{
				throw new Exception("Can't handle ADD with GIVING: " + this);
			}
			COBOL_AddNoGiving addNoGiving = (COBOL_AddNoGiving) type.getWhich();
			if (addNoGiving.moreExprs != null && addNoGiving.moreExprs.size() > 0)
			{
				throw new Exception("Can't handle multiple arguments to ADD: " + this);
			}
			if (addNoGiving.addTo == null || !addNoGiving.addTo.isPresent())
			{
				throw new Exception("ADD value TO var is required: " + this);
			}

			COBOL_Variable var = addNoGiving.addTo.var;
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

			List<AbstractStatement> results = new List<AbstractStatement>();

			// ADD 1 TO X
			AbstractExpression value = transformer.transformExpression(generator, addNoGiving.expr);
			AbstractExpression asgExpr = generator.newAssignmentExpression(COBOL_Variable.repairName(userVar.id.getValue()), EagleGenerator.SubscriptEnum.FIRST_IS_ONE, null, EagleGenerator.AssignmentEnum.PLUS_EQUALS, value, this);
			AbstractStatement exprStmt = generator.newExpressionStatement(asgExpr, this);
			results.Add(exprStmt);

			// ADD 1 TO X, Y, Z
			if (addNoGiving.addTo.moreVars != null && addNoGiving.addTo.moreVars.size() > 0)
			{
				foreach (COBOL_AddMoreVars more in addNoGiving.addTo.moreVars._elements)
				{
					COBOL_Variable.COBOL_UserVariable moreVar = (COBOL_Variable.COBOL_UserVariable) more.var.getWhich();
					AbstractExpression moreExpr = generator.newAssignmentExpression(COBOL_Variable.repairName(moreVar.id.getValue()), EagleGenerator.SubscriptEnum.FIRST_IS_ONE, null, EagleGenerator.AssignmentEnum.PLUS_EQUALS, value, this);
					AbstractStatement moreStmt = generator.newExpressionStatement(moreExpr, this);
					results.Add(moreStmt);
				}
			}
			return results;
		}
	}

}
