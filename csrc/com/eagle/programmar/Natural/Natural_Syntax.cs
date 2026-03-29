// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Reflection;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2012

namespace com.eagle.programmar.Natural
{
	using EagleSyntax = com.eagle.core.EagleSyntax;
	using Natural_OptionChoice = com.eagle.programmar.Natural.Natural_Option.Natural_OptionChoice;

	public class Natural_Syntax : EagleSyntax
	{
		public const bool IS_CASE_SENSITIVE = true;

		public override string syntaxId()
		{
			return "Natural";
		}

		public Natural_Syntax()
		{
			_isCaseSensitive = IS_CASE_SENSITIVE;
			_continuationChar = null;
			_extraCharacters = "-";
			_punctuationExceptions = new string[] {"/*"};

			addReservedWords(Natural_Reserved_Words.RESERVED_WORDS);

			// Careful, some start with a Label and don't get picked up.
			findFirstWords(typeof(Natural_Statement));

			// Pick up all the Option keywords used in DISPLAY and WRITE statements
			foreach (Type cls in typeof(Natural_OptionChoice).GetNestedTypes(BindingFlags.Public | BindingFlags.NonPublic))
			{
				string name = cls.GetFields(BindingFlags.DeclaredOnly | BindingFlags.Public | BindingFlags.NonPublic | BindingFlags.Static | BindingFlags.Instance)[0].getName();
				// System.out.println("**** Adding Natural_Option Natural_Keyword: " + name);
				addReservedWord(name);
			}

			// Pick up the built-in functions
			foreach (string name in Natural_Functions.builtinFunctions)
			{
				addReservedWord(name);
			}
		}
	}

}
