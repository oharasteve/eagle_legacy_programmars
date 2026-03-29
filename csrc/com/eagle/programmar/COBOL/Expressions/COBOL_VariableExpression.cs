// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

namespace com.eagle.programmar.COBOL.Expressions
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleArray = com.eagle.math.EagleArray;
	using EagleValue = com.eagle.math.EagleValue;
	using COBOL_Subscript = com.eagle.programmar.COBOL.COBOL_Subscript;
	using COBOL_RegularSubscript = com.eagle.programmar.COBOL.COBOL_Subscript.COBOL_RegularSubscript;
	using COBOL_Variable = com.eagle.programmar.COBOL.COBOL_Variable;
	using COBOL_Identifier_Reference = com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
	using COBOL_Keyword = com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using SubscriptEnum = com.eagle.transform.EagleGenerator.SubscriptEnum;
	using SubstringECEnum = com.eagle.transform.EagleGenerator.SubstringECEnum;
	using SubstringSCEnum = com.eagle.transform.EagleGenerator.SubstringSCEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class COBOL_VariableExpression : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) COBOL_VariableRef variable;
		public COBOL_VariableRef variable;

		public class COBOL_VariableRef : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference id;
			public COBOL_Identifier_Reference id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<com.eagle.programmar.COBOL.COBOL_Subscript> subscripts;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<COBOL_OfVariableRef> ofLists;
			public  OPT;

			public class COBOL_OfVariableRef : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword OF = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("OF");
				public COBOL_Keyword OF = new COBOL_Keyword("OF");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference id;
				public COBOL_Identifier_Reference id;
			}
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			if (variable.ofLists != null && variable.ofLists.isPresent() && variable.ofLists.size() > 0)
			{
				throw new Exception("Cannot handle field references");
			}

			string varName = variable.id.getValue();
			EagleValue val = interpreter.findSymbol(varName);
			if (val == null)
			{
				throw new Exception("Unable to find a variable named " + varName);
			}

			if (variable.subscripts != null && variable.subscripts.isPresent() && variable.subscripts.size() == 1)
			{
				if (val.isArray())
				{
					List<EagleValue> avals = ((EagleArray) val).getArrayValue();
					int subscript = variable.subscripts.first().getSubscriptValue(interpreter);
					interpreter.pushEagleValue(avals[subscript - 1]);
					return;
				}

				string str = null;
				if (val.isInteger())
				{
					int num = val.forceIntegerValue();
					str = string.Format("{0:D5}", Convert.ToInt32(num));
				}
				else if (val.isString())
				{
					str = val.forceStringValue();
				}

				if (!string.ReferenceEquals(str, null))
				{
					AbstractToken which = variable.subscripts.first().type.getWhich();
					if (which is COBOL_Subscript.COBOL_RegularSubscript)
					{
						COBOL_Subscript.COBOL_RegularSubscript subscript = (COBOL_Subscript.COBOL_RegularSubscript) which;
						if (subscript.range.isPresent())
						{
							int len = str.Length;
							int sc = interpreter.getIntValue(subscript.expr);
							int nc = interpreter.getIntValue(subscript.range.expr);
							int ec = sc + nc - 1;
							if (ec > len)
							{
								ec = len;
							}
							string piece = str.Substring(sc - 1, ec - (sc - 1));
							interpreter.pushStr(piece);
							return;
						}
					}
				}
				throw new Exception("Cannot have a subscript on " + varName);
			}

			interpreter.pushEagleValue(val);
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			if (variable.subscripts != null && variable.subscripts.size() > 0)
			{
				if (variable.subscripts.size() == 1)
				{
					COBOL_Subscript sub = variable.subscripts._elements.get(0);
					if (sub.type.getWhich() is COBOL_Subscript.COBOL_RegularSubscript)
					{
						COBOL_Subscript.COBOL_RegularSubscript regular = (COBOL_Subscript.COBOL_RegularSubscript) sub.type.getWhich();

						// This is actually a subscript range
						if (regular.range != null && regular.range.isPresent())
						{
							AbstractExpression sc = transformer.transformExpression(generator, regular.expr);
							AbstractExpression nc = transformer.transformExpression(generator, regular.range.expr);
							AbstractExpression varExp = generator.newVariableExpression(COBOL_Variable.repairName(variable.id.getValue()), EagleGenerator.SubscriptEnum.FIRST_IS_ONE, null, this);
							AbstractExpression subscrExpr = generator.newSubstringFunction(varExp, sc, EagleGenerator.SubstringSCEnum.FIRST_CHAR_IS_ONE, EagleGenerator.SubstringECEnum.GIVEN_NC, nc, true, this);
							return subscrExpr;
						}

						AbstractExpression newSub = transformer.transformExpression(generator, regular.expr);
						return generator.newVariableExpression(variable.id.getValue(), EagleGenerator.SubscriptEnum.FIRST_IS_ONE, newSub, this);
					}
				}

				throw new Exception("Can't handle this subscript: " + variable.subscripts);
			}

			return generator.newVariableExpression(COBOL_Variable.repairName(variable.id.getValue()), EagleGenerator.SubscriptEnum.FIRST_IS_ONE, null, this);
		}
	}

}
