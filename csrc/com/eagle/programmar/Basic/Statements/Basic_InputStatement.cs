// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.IO;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

namespace com.eagle.programmar.Basic.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleDouble = com.eagle.math.EagleDouble;
	using EagleInteger = com.eagle.math.EagleInteger;
	using Basic_Variable = com.eagle.programmar.Basic.Basic_Variable;
	using Basic_KeywordChoice = com.eagle.programmar.Basic.Terminals.Basic_KeywordChoice;
	using Basic_Literal = com.eagle.programmar.Basic.Terminals.Basic_Literal;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;

	public class Basic_InputStatement : TokenSequence, EagleRunnable, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Basic.Terminals.Basic_KeywordChoice INPUT = new com.eagle.programmar.Basic.Terminals.Basic_KeywordChoice("INPUT", "INP");
		public Basic_KeywordChoice INPUT = new Basic_KeywordChoice("INPUT", "INP");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Basic_Literal prompt;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT PunctuationSemicolon semicolon;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.SeparatedList<com.eagle.programmar.Basic.Basic_Variable, com.eagle.tokens.punctuation.PunctuationComma> vars;
		public SeparatedList<Basic_Variable, PunctuationComma> vars;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public static @SKIP BufferedReader _br = null;
		public static StreamReader _br = null;

		// Force it to start reading fom stdin again
		public static void resetReader()
		{
			_br = null;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			if (prompt != null && prompt.isPresent())
			{
				string val = prompt.getValue().replaceAll("\"", "");
				Console.Write(val);
			}
			Console.Write("?");

			if (_br == null)
			{
				_br = new StreamReader(System.in);
			}

			string line = null;
			try
			{
				line = _br.readLine();
			}
			catch (IOException ex)
			{
				throw new Exception("Error reading from stdin", ex);
			}

			if (string.ReferenceEquals(line, null))
			{
				throw new Exception("No input given to INPUT");
			}

			Console.WriteLine(line);
			string[] pieces = line.Split(",", true);
			int piecesGot = pieces.Length;
			int piecesExpected = vars.getPrimaryCount();
			if (piecesGot != piecesExpected)
			{
				throw new Exception("Expected line with #pieces=" + piecesExpected + " but got " + piecesGot);
			}

			for (int i = 0; i < piecesGot; i++)
			{
				Basic_Variable var = vars.getPrimaryElement(i);
				string piece = pieces[i].Trim();
				if (piece.IndexOf('.') >= 0)
				{
					double valDbl = double.Parse(piece);
					var.assignValue(interpreter, new EagleDouble(valDbl));
				}
				else
				{
					int valInt = int.Parse(piece);
					var.assignValue(interpreter, new EagleInteger(valInt));
				}
			}
		}
	}

}
