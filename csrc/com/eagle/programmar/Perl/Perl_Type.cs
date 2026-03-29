// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 27, 2014

namespace com.eagle.programmar.Perl
{
	using Perl_Variable_Definition = com.eagle.programmar.Perl.Symbols.Perl_Variable_Definition;
	using Perl_KeywordChoice = com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice;
	using Perl_Punctuation = com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;

	public class Perl_Type : TokenChooser
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Perl_KeywordChoice XXbase = new com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice("array", "bool", "int", "string");
		public Perl_KeywordChoice XXbase = new Perl_KeywordChoice("array", "bool", "int", "string");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Perl_Variable_Definition XXtype;
		public Perl_Variable_Definition XXtype;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Perl_CompoundType extends com.eagle.tokens.TokenSequence
		public class Perl_CompoundType : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Perl.Terminals.Perl_Punctuation backSlash = new com.eagle.programmar.Perl.Terminals.Perl_Punctuation('\\');
			public Perl_Punctuation backSlash = new Perl_Punctuation('\\');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Perl.Symbols.Perl_Variable_Definition type;
			public Perl_Variable_Definition type;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<Perl_MoreTypeName> more;
			public  OPT;

			public class Perl_MoreTypeName : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Perl.Terminals.Perl_Punctuation backSlash = new com.eagle.programmar.Perl.Terminals.Perl_Punctuation('\\');
				public Perl_Punctuation backSlash = new Perl_Punctuation('\\');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Perl.Symbols.Perl_Variable_Definition type;
				public Perl_Variable_Definition type;
			}
		}

		public static TypeEnum findType(Perl_Type type)
		{
			if (type.getWhich() is Perl_KeywordChoice)
			{
				Perl_KeywordChoice typeName = (Perl_KeywordChoice) type.getWhich();
				switch (typeName.getValue())
				{
				case "array":
					return TypeEnum.ARRAY;
				case "bool":
					return TypeEnum.BOOLEAN;
				case "int":
					return TypeEnum.INTEGER;
				case "string":
					return TypeEnum.STRING;
				}
			}
			return TypeEnum.VOID;
		}
	}

}
