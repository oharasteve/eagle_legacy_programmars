// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 6, 2024

namespace com.eagle.programmar.Java
{
	using Java_Identifier = com.eagle.programmar.Java.Terminals.Java_Identifier;
	using Java_Keyword = com.eagle.programmar.Java.Terminals.Java_Keyword;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;

	public class Java_Package : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @BLANKLINE Java_Keyword PACKAGE = new com.eagle.programmar.Java.Terminals.Java_Keyword("package");
		public  BLANKLINE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Java.Terminals.Java_Identifier id;
		public Java_Identifier id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<Java_MorePackageIds> moreIds;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @NOSPACE PunctuationSemicolon semicolon;
		public  NOSPACE;

		public class Java_MorePackageIds : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @NOSPACE PunctuationPeriod dot;
			public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE Java_Identifier id;
			public  NOSPACE;
		}

		public static Java_Package newPackage(string pkgName)
		{
			Java_Package pkg = new Java_Package();
			string[] pieces = pkgName.Split("\\.", true);
			pkg.id = new Java_Identifier();
			pkg.id.setValue(pieces[0]);

			if (pieces.Length > 1)
			{
				pkg.moreIds = new TokenList<Java_MorePackageIds>();
				pkg.moreIds.setPresent(true);
				bool skip = true;
				foreach (string piece in pieces)
				{
					if (skip)
					{
						// Already did first piece
						skip = false;
						continue;
					}

					Java_MorePackageIds more = new Java_MorePackageIds();
					more.dot = new PunctuationPeriod();
					more.id = new Java_Identifier();
					more.id.setValue(piece);
					pkg.moreIds.addToken(more);
				}
			}

			pkg.semicolon = new PunctuationSemicolon();
			return pkg;
		}
	}

}
