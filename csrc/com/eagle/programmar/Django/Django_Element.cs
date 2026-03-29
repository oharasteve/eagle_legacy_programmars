// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 15, 2014

namespace com.eagle.programmar.Django
{
	using Django_Comment = com.eagle.programmar.Django.Terminals.Django_Comment;
	using HTML_Element = com.eagle.programmar.HTML.HTML_Program.HTML_Element;
	using HTML_Syntax = com.eagle.programmar.HTML.HTML_Syntax;
	using HTML_TableRow = com.eagle.programmar.HTML.HTML_TableRow;
	using TokenChooser = com.eagle.tokens.TokenChooser;

	public class Django_Element : TokenChooser
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE @SYNTAX(com.eagle.programmar.HTML.HTML_Syntax.class) com.eagle.programmar.HTML.HTML_TableRow XXtableRow;
		public @SYNTAX(typeof(HTML_Syntax)) HTML_TableRow XXtableRow;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE @SYNTAX(com.eagle.programmar.HTML.HTML_Syntax.class) com.eagle.programmar.HTML.HTML_Program.HTML_Element XXelement;
		public @SYNTAX(typeof(HTML_Syntax)) HTML_Element XXelement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Django_Comment XXcomment;
		public Django_Comment XXcomment;
	}

}
