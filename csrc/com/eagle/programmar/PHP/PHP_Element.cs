// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 29, 2024

namespace com.eagle.programmar.PHP
{
	using Perl_StatementOrComment = com.eagle.programmar.Perl.Perl_StatementOrComment;
	using Perl_Syntax = com.eagle.programmar.Perl.Perl_Syntax;
	using TokenChooser = com.eagle.tokens.TokenChooser;

	public class PHP_Element : TokenChooser
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE @SYNTAX(com.eagle.programmar.Perl.Perl_Syntax.class) com.eagle.programmar.Perl.Perl_StatementOrComment XXstatement;
		public @SYNTAX(typeof(Perl_Syntax)) Perl_StatementOrComment XXstatement;
	}

}
