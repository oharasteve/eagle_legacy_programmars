// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 9, 2011

namespace com.eagle.programmar.Perl
{
	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using TokenList = com.eagle.tokens.TokenList;

	public class Perl_Program : AbstractLanguage
	{
		public const string PERL = "Perl";

		public Perl_Program() : base(PERL, new Perl_Syntax())
		{
		}

		public override string DocRoot
		{
			get
			{
				return "http://perldoc.perl.org/";
			}
		}

		public static readonly string[] MODIFIERS = new string[] {"abstract", "const", "final", "private", "protected", "public", "static", "var"};

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.TokenList<Perl_StatementOrComment> statements;
		public TokenList<Perl_StatementOrComment> statements;
	}

}
