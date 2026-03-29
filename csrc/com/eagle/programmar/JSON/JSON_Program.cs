// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 28, 2014

namespace com.eagle.programmar.JSON
{
	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using JSON_Comment = com.eagle.programmar.JSON.Terminals.JSON_Comment;
	using JSON_Punctuation = com.eagle.programmar.JSON.Terminals.JSON_Punctuation;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;

	public class JSON_Program : AbstractLanguage
	{
		public const string JSON = "JSON";

		public JSON_Program() : base(JSON, new JSON_Syntax())
		{
		}

		public override string DocRoot
		{
			get
			{
				return "http://www.w3schools.com/json/";
			}
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT @CURIOUS("Strange file header") com.eagle.programmar.JSON.Terminals.JSON_Punctuation header = new com.eagle.programmar.JSON.Terminals.JSON_Punctuation(")]}'");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.TokenList<JSON_ElementOrComment> elements;
		public TokenList<JSON_ElementOrComment> elements;

		public class JSON_ElementOrComment : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE JSON_Comment XXcomment;
			public JSON_Comment XXcomment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE JSON_Element XXelement;
			public JSON_Element XXelement;
		}
	}
}
