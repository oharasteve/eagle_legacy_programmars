// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 23, 2015

namespace com.eagle.programmar.JavaP
{
	using EagleSyntax = com.eagle.core.EagleSyntax;

	public class JavaP_Syntax : EagleSyntax
	{
		public const bool IS_CASE_SENSITIVE = true;

		public override string syntaxId()
		{
			return "Java";
		}

		public JavaP_Syntax()
		{
			_isCaseSensitive = IS_CASE_SENSITIVE;
			_continuationChar = null;
			_autoAdvance = false;
			_extraCharacters = "_";
			_punctuationExceptions = new string[] {"//", ";;"};

			addReservedWords(keywords);
		}

		private string[] keywords = new string[] {"class", "const", "extends", "implements", "public"};

		public static readonly string[] ACC_CODES = new string[] {"ACC_ABSTRACT", "ACC_ANNOTATION", "ACC_BRIDGE", "ACC_ENUM", "ACC_FINAL", "ACC_INTERFACE", "ACC_PRIVATE", "ACC_PROTECTED", "ACC_PUBLIC", "ACC_STATIC", "ACC_SUPER", "ACC_SYNCHRONIZED", "ACC_SYNTHETIC", "ACC_VARARGS", "ACC_VOLATILE"};
	}

}
