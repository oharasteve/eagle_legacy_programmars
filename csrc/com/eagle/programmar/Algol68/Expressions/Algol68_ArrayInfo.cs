// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 29, 2024

namespace com.eagle.programmar.Algol68.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleString = com.eagle.math.EagleString;
	using EagleValue = com.eagle.math.EagleValue;
	using Algol68_Variable = com.eagle.programmar.Algol68.Algol68_Variable;
	using Algol68_Identifier_Reference = com.eagle.programmar.Algol68.Symbols.Algol68_Identifier_Reference;
	using Algol68_KeywordChoice = com.eagle.programmar.Algol68.Terminals.Algol68_KeywordChoice;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using SubscriptEnum = com.eagle.transform.EagleGenerator.SubscriptEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Algol68_ArrayInfo : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Algol68.Terminals.Algol68_KeywordChoice UPB = new com.eagle.programmar.Algol68.Terminals.Algol68_KeywordChoice("UPB", "LWB");
		public Algol68_KeywordChoice UPB = new Algol68_KeywordChoice("UPB", "LWB");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Algol68.Algol68_Variable arrayName;
		public Algol68_Variable arrayName;

		public override void interpret(EagleInterpreter interpreter)
		{
			Algol68_Identifier_Reference id = arrayName.vars.first();
			EagleValue val = interpreter.findSymbol(id.getValue());

			if (val is EagleString)
			{
				EagleString str = (EagleString) val;
				switch (UPB.getValue())
				{
				case "LWB":
					interpreter.pushInt(1);
					return;
				case "UPB":
					interpreter.pushInt(str.forceStringValue().length());
					return;
				}
			}

			throw new Exception("Unable to handle " + UPB.getValue());
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			switch (UPB.ToString().ToUpper())
			{
			case "UPB":
				AbstractExpression varExpr = generator.newVariableExpression(arrayName.vars.first().getValue(), EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, null, UPB);
				return generator.newLengthFunction(varExpr, this);
			case "LWB":
				return generator.newNumberExpression("1", this);
			default:
				throw new Exception("Unexpected string operator: " + UPB);
			}
		}
	}

}
