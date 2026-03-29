// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 6, 2024

namespace com.eagle.programmar.Python
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using Python_Variable_Definition = com.eagle.programmar.Python.Symbols.Python_Variable_Definition;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;

	public class Python_Data : TokenSequence, EagleRunnable, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @NEWLINE Python_Variable_Definition id;
		public  NEWLINE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationColon colon;
		public PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) Python_Type type;
		public Python_Type type;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT Python_DataInitialValue initialValue;
		public  OPT;

		public class Python_DataInitialValue : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationEquals equals;
			public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Python_Expression expression;
			public Python_Expression expression;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			EagleValue value = interpreter.getEagleValue(initialValue);
			interpreter.setSymbol(id, id.ToString(), value);
		}

		public static Python_Data newDataDeclaration(string name, Python_Expression size, Python_Type type, Python_Expression initial, AbstractToken source)
		{
			if (initial == null)
			{
				// Don't bother with declarations without initial values
				// Python ignores them at runtime anyways
				return null;
			}

			if (name.Equals("true", StringComparison.OrdinalIgnoreCase) || name.Equals("false", StringComparison.OrdinalIgnoreCase))
			{
				// Sorry, cannot redefine true or false
				return null;
			}

			Python_Data data = new Python_Data();

			// Set data name and type
			data.id = new Python_Variable_Definition();
			data.id.setValue(name);
			// data.colon = new PunctuationColon();
			// data.type = type;

			// Set the initial value
			Python_DataInitialValue init = new Python_DataInitialValue();
			init.setPresent(true);
			init.equals = new PunctuationEquals();
			init.expression = initial;
			data.initialValue = init;
			data.initialValue.setPresent(true);

			data.setTransformationSource(source);
			return data;
		}
	}

}
