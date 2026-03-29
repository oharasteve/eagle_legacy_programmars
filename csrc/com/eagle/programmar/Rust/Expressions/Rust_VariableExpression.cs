// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Rust.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Rust_Expression = com.eagle.programmar.Rust.Rust_Expression;
	using Rust_Generator = com.eagle.programmar.Rust.Rust_Generator;
	using Rust_Variable = com.eagle.programmar.Rust.Rust_Variable;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using SubscriptEnum = com.eagle.transform.EagleGenerator.SubscriptEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Rust_VariableExpression : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Rust.Rust_Variable variable;
		public Rust_Variable variable;

		public override void interpret(EagleInterpreter interpreter)
		{
			interpreter.tryToInterpret(variable);
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			return generator.newVariableExpression(variable.var.getValue(), EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, null, this);
		}

		public static Rust_Expression generateVariableExpression(string name, EagleGenerator.SubscriptEnum offset, Rust_Expression subscrExpr, AbstractToken source)
		{
			Rust_VariableExpression var = new Rust_VariableExpression();
			var.variable = Rust_Variable.generateVariable(name);
			var.setTransformationSource(source);

	//		if (subscrExpr != null)
	//		{
	//			if (offset == SubscriptEnum.IT_IS_A_HASHMAP)
	//			{
	//				Rust_MethodInvocation invoke = new Rust_MethodInvocation();
	//				Rust_Variable var = Rust_Variable.newVariable(name + ".get");
	//				ArrayList<Rust_Expression> args = new ArrayList<Rust_Expression>();
	//				args.add(subscrExpr);
	//				return invoke.generateInvocation(var, args, source);
	//			}
	//
	//			Rust_Subscript subscript = new Rust_Subscript();
	//			subscript.leftBracket = new PunctuationLeftBracket();
	//			subscript.rightBracket = new PunctuationRightBracket();
	//
	//			if (offset == SubscriptEnum.FIRST_IS_ONE)
	//			{
	//				Rust_Number num = new Rust_Number();
	//				Rust_Expression one = Rust_Generator.wrapExpression(num.generateNumber("1", source));
	//				Rust_AdditiveExpression addExp = new Rust_AdditiveExpression();
	//				Oper2Types types = new Oper2Types(EagleInteger.INTEGER, EagleInteger.INTEGER);
	//				Rust_Expression minusOne = addExp.generateAdditive(types, subscrExpr,
	//						AdditiveEnum.MINUS, one, source);
	//				subscript.expr = minusOne;
	//			}
	//			else
	//			{
	//				subscript.expr = subscrExpr;
	//			}
	//			subscript.expr.setPresent(true);
	//
	//			this.variable.subscript = new TokenList<Rust_Subscript>();
	//			this.variable.subscript.addToken(subscript);
	//		}

			return Rust_Generator.wrapExpression(var);
		}
	}

}
