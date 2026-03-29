// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 19, 2014

namespace com.eagle.programmar.Django
{
	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using TokenList = com.eagle.tokens.TokenList;

	public class Django_Program : AbstractLanguage
	{
		public const string DJANGO = "Django";

		public Django_Program() : base(DJANGO, new Django_Syntax())
		{
		}

		public override string DocRoot
		{
			get
			{
				return "Unknown";
			}
		}

		// Components of a Django Program
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.TokenList<Django_Element> elements;
		public TokenList<Django_Element> elements;
	}
}
