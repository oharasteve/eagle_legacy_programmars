// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 1, 2022

namespace com.eagle.programmar.Rust
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using Rust_Identifier_Reference = com.eagle.programmar.Rust.Symbols.Rust_Identifier_Reference;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;

	public class Rust_Variable : TokenSequence, AbstractVariable, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Rust.Symbols.Rust_Identifier_Reference var;
		public Rust_Identifier_Reference var;
	//	public @S(20) @OPT Rust_Subscript subscript;
	//
	//	public static class Rust_Subscript extends TokenSequence
	//	{
	//		public @S(10) PunctuationLeftBracket leftBracket;
	//		public @S(20) Rust_Expression expr;
	//		public @S(30) PunctuationRightBracket rightBracket;
	//	}

		public override void interpret(EagleInterpreter interpreter)
		{
			EagleValue value = interpreter.findSymbol(var.getValue());

	//		if (subscript.isPresent() && value instanceof EagleArray)
	//		{
	//			int subscr = interpreter.getIntValue(subscript.expr);
	//			EagleArray val = (EagleArray) value;
	//			interpreter.pushEagleValue(val.getValue(subscr));
	//			return;
	//		}

			interpreter.pushEagleValue(value);
		}

		public static Rust_Variable generateVariable(string name)
		{
			Rust_Variable var = new Rust_Variable();
			var.var = new Rust_Identifier_Reference();
			if (name.Length == 1)
			{
				// Rust does not allow single letter variable names
				var.var.setValue(name + name);
			}
			else
			{
				var.var.setValue(name);
			}
			return var;
		}
	}

}
