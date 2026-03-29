// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Perl.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Perl_Variable = com.eagle.programmar.Perl.Perl_Variable;
	using Perl_UserVariable = com.eagle.programmar.Perl.Perl_Variable.Perl_UserVariable;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using SubscriptEnum = com.eagle.transform.EagleGenerator.SubscriptEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Perl_VariableExpression : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Perl.Perl_Variable variable;
		public Perl_Variable variable;

		public override void interpret(EagleInterpreter interpreter)
		{
			interpreter.tryToInterpret(variable);
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			if (!(variable.getWhich() is Perl_Variable.Perl_UserVariable))
			{
				throw new Exception("Can only handle simple variables");
			}
			Perl_Variable.Perl_UserVariable userVar = (Perl_Variable.Perl_UserVariable) variable.getWhich();

			AbstractExpression subscr = null;
			if (userVar.subscript != null && userVar.subscript.size() > 0)
			{
				subscr = transformer.transformExpression(generator, userVar.subscript.first().expr);
			}

			string newName = Perl_Variable.repairName(userVar.id.getValue());
			return generator.newVariableExpression(newName, EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, subscr, this);
		}
	}

}
