// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2011

namespace com.eagle.programmar.CMacro
{
	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using CMacro_Pragma_Statement = com.eagle.programmar.CMacro.Statements.CMacro_Pragma_Statement;
	using CMacro_Comment = com.eagle.programmar.CMacro.Terminals.CMacro_Comment;
	using CMacro_EndOfLine = com.eagle.programmar.CMacro.Terminals.CMacro_EndOfLine;
	using CMacro_MultiLineText = com.eagle.programmar.CMacro.Terminals.CMacro_MultiLineText;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class CMacro_Program : AbstractLanguage
	{
		public const string CMACRO = "CMacro";

		public CMacro_Program() : base(CMACRO, new CMacro_Syntax())
		{
		}

		public override string DocRoot
		{
			get
			{
				return "http://gcc.gnu.org/onlinedocs/cpp/";
			}
		}

	//	@Override
	//	public void findClassOverrides(EagleOverrideManager overrideManager)
	//	{
	//		// Instead of creating a bunch of real C statements inside a #if, just use this simple class
	//		overrideManager.override(CMacro_IfElement.class, CMacro_Element.class);
	//		overrideManager.override(CMacro_IfDefElement.class, CMacro_Element.class);
	//	}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.TokenList<CMacro_Element> elements;
		public TokenList<CMacro_Element> elements;

		public class CMacro_Element : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMacro_Pragma_Statement XXpragma;
			public CMacro_Pragma_Statement XXpragma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMacro_CommentLine XXcomment;
			public CMacro_CommentLine XXcomment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMacro_StatementOrComment XXstmt;
			public CMacro_StatementOrComment XXstmt;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMacro_MultiLineText XXtextLine;
			public CMacro_MultiLineText XXtextLine;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST CMacro_EndOfLine XXendOfLine;
			public CMacro_EndOfLine XXendOfLine;
		}

		public class CMacro_CommentLine : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CMacro.Terminals.CMacro_Comment comment;
			public CMacro_Comment comment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CMacro.Terminals.CMacro_EndOfLine endOfLine;
			public CMacro_EndOfLine endOfLine;
		}
	}

}
