// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 3, 2026

namespace com.eagle.programmar.Rust.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Rust_Expression = com.eagle.programmar.Rust.Rust_Expression;
	using Rust_Type = com.eagle.programmar.Rust.Rust_Type;
	using Rust_TypePrimitive = com.eagle.programmar.Rust.Rust_Type.Rust_TypePrimitive;
	using Rust_Keyword = com.eagle.programmar.Rust.Terminals.Rust_Keyword;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Rust_AsExpression : PrecedenceOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Rust.Rust_Expression expression = new com.eagle.programmar.Rust.Rust_Expression(this, AllowedPrecedence.ATLEAST);
		public Rust_Expression expression = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Rust.Terminals.Rust_Keyword AS = new com.eagle.programmar.Rust.Terminals.Rust_Keyword("as");
		public Rust_Keyword AS = new Rust_Keyword("as");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) Rust_TypeExpression type;
		public Rust_TypeExpression type;

		public override void interpret(EagleInterpreter interpreter)
		{
			AbstractToken which = type.type.getWhich();
			if (which is Rust_Type.Rust_TypePrimitive)
			{
				Rust_Type.Rust_TypePrimitive primitive = (Rust_Type.Rust_TypePrimitive) which;
				string prim = primitive.PRIMITIVE.getValue();
				switch (prim)
				{
				case "i32":
				case "usize":
					int inum = interpreter.getIntValue(expression);
					interpreter.pushInt(inum);
					return;
				case "f64":
					double dnum = interpreter.getIntValue(expression);
					interpreter.pushDouble(dnum);
					return;
				case "String":
				case "&str":
					string str = interpreter.getStrValue(expression);
					interpreter.pushStr(str);
					return;
				}
				throw new Exception("Unable to cast to " + prim);
			}
			throw new Exception("Unable to cast to " + which);
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			// This is not really correct. Need to generate a cast, in some cases. 
			AbstractExpression newExpr = transformer.transformExpression(generator, expression);
			return newExpr;
		}

		public static Rust_AsExpression generateAsExpr(Rust_Expression expr, Rust_Type type, AbstractToken source)
		{
			Rust_TypeExpression typeExpr = new Rust_TypeExpression();
			typeExpr.type = type;
			Rust_AsExpression @as = new Rust_AsExpression();
			@as.expression = expr;
			@as.type = typeExpr;

			@as.setTransformationSource(source);
			return @as;
		}
	}

}
