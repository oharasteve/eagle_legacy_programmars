// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 29, 2014

namespace com.eagle.programmar.Property
{
	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using Property_Comment = com.eagle.programmar.Property.Terminals.Property_Comment;
	using Property_EndOfLine = com.eagle.programmar.Property.Terminals.Property_EndOfLine;
	using Property_Identifier = com.eagle.programmar.Property.Terminals.Property_Identifier;
	using Property_RestOfLine = com.eagle.programmar.Property.Terminals.Property_RestOfLine;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;

	public class Property_Program : AbstractLanguage
	{
		public const string PROPERTY = "Property";

		public Property_Program() : base(PROPERTY, new Property_Syntax())
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
// ORIGINAL LINE: public @S(10) com.eagle.tokens.TokenList<Property_Element> elements;
		public TokenList<Property_Element> elements;

		public class Property_Element : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Property_Comment XXcomment;
			public Property_Comment XXcomment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Property_Value XXpair;
			public Property_Value XXpair;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Property_EndOfLine XXeoln;
			public Property_EndOfLine XXeoln;
		}

		public class Property_Value : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT PunctuationPeriod period;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT SeparatedList<com.eagle.programmar.Property.Terminals.Property_Identifier, com.eagle.tokens.punctuation.PunctuationPeriod> ids;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationEquals equals;
			public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Property.Terminals.Property_RestOfLine value;
			public Property_RestOfLine value;
		}
	}

}
