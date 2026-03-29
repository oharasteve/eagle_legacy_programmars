// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 26, 2011

namespace com.eagle.programmar.CMD.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleInteger = com.eagle.math.EagleInteger;
	using EagleString = com.eagle.math.EagleString;
	using EagleValue = com.eagle.math.EagleValue;
	using CMD_Expression = com.eagle.programmar.CMD.CMD_Expression;
	using CMD_Format = com.eagle.programmar.CMD.CMD_Format;
	using CMD_Variable = com.eagle.programmar.CMD.CMD_Variable;
	using CMD_Keyword = com.eagle.programmar.CMD.Terminals.CMD_Keyword;
	using CMD_PunctuationChoice = com.eagle.programmar.CMD.Terminals.CMD_PunctuationChoice;
	using CMD_RestOfLine = com.eagle.programmar.CMD.Terminals.CMD_RestOfLine;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;
	using PunctuationSlash = com.eagle.tokens.punctuation.PunctuationSlash;

	public class CMD_Set_Statement : TokenSequence, EagleRunnable, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("set.mspx") com.eagle.programmar.CMD.Terminals.CMD_Keyword SET = new com.eagle.programmar.CMD.Terminals.CMD_Keyword("set");
		public @DOC("set.mspx") CMD_Keyword SET = new CMD_Keyword("set");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) CMD_Set_What setWhat;
		public CMD_Set_What setWhat;

		public static class CMD_Set_Regular extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CMD.CMD_Variable var;
			public CMD_Variable var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationEquals equals;
			public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.CMD.Terminals.CMD_RestOfLine value;
			public CMD_RestOfLine value;
		}

		public static class CMD_Set_Assigment extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationSlash slash;
			public PunctuationSlash slash;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CMD.Terminals.CMD_Keyword A = new com.eagle.programmar.CMD.Terminals.CMD_Keyword("a");
			public CMD_Keyword A = new CMD_Keyword("a");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.CMD.CMD_Variable var;
			public CMD_Variable var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.CMD.Terminals.CMD_PunctuationChoice operator = new com.eagle.programmar.CMD.Terminals.CMD_PunctuationChoice("=", "+=");
			public CMD_PunctuationChoice @operator = new CMD_PunctuationChoice("=", "+=");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.CMD.CMD_Expression expr;
			public CMD_Expression expr;
		}

		public static class CMD_Set_Prompt extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationSlash slash;
			public PunctuationSlash slash;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CMD.Terminals.CMD_Keyword P = new com.eagle.programmar.CMD.Terminals.CMD_Keyword("p");
			public CMD_Keyword P = new CMD_Keyword("p");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.CMD.CMD_Variable var;
			public CMD_Variable var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationEquals equals;
			public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.CMD.Terminals.CMD_RestOfLine value;
			public CMD_RestOfLine value;
		}

		public static class CMD_Set_What extends TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMD_Set_Regular XXsetRegular;
			public CMD_Set_Regular XXsetRegular;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMD_Set_Assigment XXsetAssignment;
			public CMD_Set_Assigment XXsetAssignment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMD_Set_Prompt XXsetPrompt;
			public CMD_Set_Prompt XXsetPrompt;
		}

		private static string getName(EagleInterpreter interpreter, CMD_Variable var)
		{
			string name = var.id.getValue();
			if (var.subscript != null && var.subscript.isPresent())
			{
				int sub = interpreter.getIntValue(var.subscript.expr);
				name += "[" + sub + "]";
			}
			return name;
		}

		public void interpret(EagleInterpreter interpreter)
		{
			AbstractToken which = setWhat.getWhich();
			if (which is CMD_Set_Regular)
			{
				CMD_Set_Regular cmd = (CMD_Set_Regular) which;
				string name = getName(interpreter, cmd.var);
				string formatted = CMD_Format.format(interpreter, cmd.value.getValue());
				interpreter.setSymbol(cmd.var, name, new EagleString(formatted));
			}
			else if (which is CMD_Set_Assigment)
			{
				CMD_Set_Assigment setA = (CMD_Set_Assigment) which;
				string name = getName(interpreter, setA.var);
				switch (setA.@operator.getValue())
				{
				case "=":
					EagleValue newVal = interpreter.getEagleValue(setA.expr);
					interpreter.setSymbol(setA.var, name, newVal);
					break;
				case "+=":
					int intVal = interpreter.getIntValue(setA.expr);
					EagleValue oldVar = interpreter.findSymbol(setA.var.id.getValue());
					EagleInteger newValue = new EagleInteger(intVal + oldVar.forceIntegerValue());
					interpreter.setSymbol(setA.var, name, newValue);
					break;
				default:
					throw new Exception("Unable to handle operator " + setA.@operator.getValue());
				}
			}
			else
			{
				throw new Exception("Unexpected assignment variable: " + which);
			}
		}
	}

}
