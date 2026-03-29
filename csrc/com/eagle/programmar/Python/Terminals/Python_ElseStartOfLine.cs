// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 25, 2024

namespace com.eagle.programmar.Python.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;

	public class Python_ElseStartOfLine : Python_StartOfLine
	{
		private const bool DBG = false;

		public override bool parse(EagleFileReader lines)
		{
			if (findStart(lines) == FOUND.EOF)
			{
				return false;
			}

			if (DBG)
			{
				Console.WriteLine("******* ElseStartOfLine: Checking " + (_currentLine + 1) + "/" + (_currentChar + 1));
			}
			AbstractToken parent = this;
			while (parent != null)
			{
				if (DBG)
				{
// JAVA TO C# CONVERTER WARNING: The .NET Type.FullName property will not always yield results identical to the Java Class.getName method:
					Console.WriteLine("******* Parent is " + parent.GetType().FullName);
				}
				if (parent is AbstractStatement)
				{
					break;
				}
				parent = parent.getParent();
			}

			/////// The KEY Line ///////
			if (_currentChar != parent.getStartChar())
			{
				if (DBG)
				{
					Console.WriteLine("******* IF FAIL: Comparing " + (_currentLine + 1) + "/" + (_currentChar + 1) + " to " + (parent.getStartLine() + 1) + "/" + (parent.getStartChar() + 1));
				}
				return false;
			}

			if (DBG)
			{
				Console.WriteLine("******* IF MATCH: Comparing " + (_currentLine + 1) + "/" + (_currentChar + 1) + " to " + (parent.getStartLine() + 1) + "/" + (parent.getStartChar() + 1));
			}
			foundIt(_currentLine, _currentChar - 1);
			return true;
		}

	//	@Override
	//	public String toString()
	//	{
	//		return super.toString();
	//	}
	}
}
