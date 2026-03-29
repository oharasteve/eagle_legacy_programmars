// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 15, 2014

namespace com.eagle.programmar.Django
{
	using Django_AutoEscapeControl = com.eagle.programmar.Django.Controls.Django_AutoEscapeControl;
	using Django_BlockControl = com.eagle.programmar.Django.Controls.Django_BlockControl;
	using Django_BlockTransControl = com.eagle.programmar.Django.Controls.Django_BlockTransControl;
	using Django_CommentControl = com.eagle.programmar.Django.Controls.Django_CommentControl;
	using Django_ExtendsControl = com.eagle.programmar.Django.Controls.Django_ExtendsControl;
	using Django_ForControl = com.eagle.programmar.Django.Controls.Django_ForControl;
	using Django_FromControl = com.eagle.programmar.Django.Controls.Django_FromControl;
	using Django_IfControl = com.eagle.programmar.Django.Controls.Django_IfControl;
	using Django_ImportControl = com.eagle.programmar.Django.Controls.Django_ImportControl;
	using Django_LoadControl = com.eagle.programmar.Django.Controls.Django_LoadControl;
	using Django_MacroControl = com.eagle.programmar.Django.Controls.Django_MacroControl;
	using Django_SetControl = com.eagle.programmar.Django.Controls.Django_SetControl;
	using Django_SpacelessControl = com.eagle.programmar.Django.Controls.Django_SpacelessControl;
	using Django_TransControl = com.eagle.programmar.Django.Controls.Django_TransControl;
	using HTML_Punctuation = com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationHyphen = com.eagle.tokens.punctuation.PunctuationHyphen;

	public class Django_Control : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.HTML.Terminals.HTML_Punctuation bracePercent = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation("{%");
		public HTML_Punctuation bracePercent = new HTML_Punctuation("{%");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT PunctuationHyphen dash1;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) Django_ControlChoices which;
		public Django_ControlChoices which;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT PunctuationHyphen dash2;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.HTML.Terminals.HTML_Punctuation percentBrace = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation("%}");
		public HTML_Punctuation percentBrace = new HTML_Punctuation("%}");

		public class Django_ControlChoices : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Django_AutoEscapeControl XXautoescapeControl;
			public Django_AutoEscapeControl XXautoescapeControl;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Django_BlockControl XXblockControl;
			public Django_BlockControl XXblockControl;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Django_BlockTransControl XXblockTransControl;
			public Django_BlockTransControl XXblockTransControl;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Django_CommentControl XXcommentControl;
			public Django_CommentControl XXcommentControl;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Django_ExtendsControl XXextendsControl;
			public Django_ExtendsControl XXextendsControl;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Django_ForControl XXforControl;
			public Django_ForControl XXforControl;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Django_FromControl XXfromControl;
			public Django_FromControl XXfromControl;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Django_IfControl XXifControl;
			public Django_IfControl XXifControl;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Django_ImportControl XXimportControl;
			public Django_ImportControl XXimportControl;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Django_LoadControl XXloadControl;
			public Django_LoadControl XXloadControl;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Django_MacroControl XXmacroControl;
			public Django_MacroControl XXmacroControl;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Django_SetControl XXsetControl;
			public Django_SetControl XXsetControl;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Django_SpacelessControl XXspacelessControl;
			public Django_SpacelessControl XXspacelessControl;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Django_TransControl XXtransControl;
			public Django_TransControl XXtransControl;
		}
	}

}
