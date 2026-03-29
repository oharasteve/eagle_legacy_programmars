// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

namespace com.eagle.programmar.CMacro.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using CMacroFunctionParens = com.eagle.programmar.CMacro.CMacroFunctionParens;
	using CMacro_Identifier_Reference = com.eagle.programmar.CMacro.Symbols.CMacro_Identifier_Reference;
	using CMacro_Keyword = com.eagle.programmar.CMacro.Terminals.CMacro_Keyword;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using TokenChooser = com.eagle.tokens.TokenChooser;

	public class CMacro_FunctionCall : PrimaryOperator, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CMacro.Terminals.CMacro_Keyword DEFINED = new com.eagle.programmar.CMacro.Terminals.CMacro_Keyword("defined");
		public CMacro_Keyword DEFINED = new CMacro_Keyword("defined");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) CMacro_FunctionType funcType;
		public CMacro_FunctionType funcType;

		public override void interpret(EagleInterpreter interpreter)
		{
			AbstractToken which = funcType.getWhich();
			string name;
			if (which is CMacro_Identifier_Reference)
			{
				name = ((CMacro_Identifier_Reference) which).ToString();
			}
			else if (which is CMacroFunctionParens)
			{
				name = ((CMacroFunctionParens) which).variable.ToString();
			}
			else
			{
				throw new Exception("Unexpected token: " + which.ToString());
			}
			bool val = interpreter.isSymbolDefined(name);
			interpreter.pushBool(val);
		}

		public class CMacro_FunctionType : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMacro_Identifier_Reference XXvariable;
			public CMacro_Identifier_Reference XXvariable;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMacroFunctionParens XXparams;
			public CMacroFunctionParens XXparams;
		}
	}

}
