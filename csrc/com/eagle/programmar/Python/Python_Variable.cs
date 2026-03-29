// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 17, 2013

namespace com.eagle.programmar.Python
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using Python_Identifier_Reference = com.eagle.programmar.Python.Symbols.Python_Identifier_Reference;
	using Python_Keyword = com.eagle.programmar.Python.Terminals.Python_Keyword;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;

	public class Python_Variable : TokenSequence, AbstractVariable, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) Python_SelfOrVariable var;
		public Python_SelfOrVariable var;

		public class Python_SelfOrVariable : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Python_Keyword XXSELF = new com.eagle.programmar.Python.Terminals.Python_Keyword("self");
			public Python_Keyword XXSELF = new Python_Keyword("self");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Python_Identifier_Reference XXid;
			public Python_Identifier_Reference XXid;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			Python_Identifier_Reference which = (Python_Identifier_Reference) var.getWhich();
			EagleValue value = interpreter.findSymbol(which.ToString());
			interpreter.pushEagleValue(value);
		}

		public static Python_Variable newVariable(string name)
		{
			Python_Variable var = new Python_Variable();
			var.var = new Python_SelfOrVariable();
			Python_Identifier_Reference id = new Python_Identifier_Reference();
			id.setValue(name);
			var.var.setWhich(id);
			return var;
		}
	}

}
