// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2011

namespace com.eagle.programmar.CMacro.Statements
{
	using CMacro_Preprocess = com.eagle.preprocess.CMacro.CMacro_Preprocess;
	using CMacro_Processable = com.eagle.programmar.CMacro.CMacro_Processable;
	using CMacro_Pragma_CLang = com.eagle.programmar.CMacro.Pragmas.CMacro_Pragma_CLang;
	using CMacro_Pragma_CodeSeg = com.eagle.programmar.CMacro.Pragmas.CMacro_Pragma_CodeSeg;
	using CMacro_Pragma_Comment = com.eagle.programmar.CMacro.Pragmas.CMacro_Pragma_Comment;
	using CMacro_Pragma_Export = com.eagle.programmar.CMacro.Pragmas.CMacro_Pragma_Export;
	using CMacro_Pragma_Intrinsic = com.eagle.programmar.CMacro.Pragmas.CMacro_Pragma_Intrinsic;
	using CMacro_Pragma_Mark = com.eagle.programmar.CMacro.Pragmas.CMacro_Pragma_Mark;
	using CMacro_Pragma_Message = com.eagle.programmar.CMacro.Pragmas.CMacro_Pragma_Message;
	using CMacro_Pragma_Pack = com.eagle.programmar.CMacro.Pragmas.CMacro_Pragma_Pack;
	using CMacro_Pragma_Region = com.eagle.programmar.CMacro.Pragmas.CMacro_Pragma_Region;
	using CMacro_Pragma_RuntimeChecks = com.eagle.programmar.CMacro.Pragmas.CMacro_Pragma_RuntimeChecks;
	using CMacro_Pragma_STDC = com.eagle.programmar.CMacro.Pragmas.CMacro_Pragma_STDC;
	using CMacro_Pragma_Unroll = com.eagle.programmar.CMacro.Pragmas.CMacro_Pragma_Unroll;
	using CMacro_Pragma_Warn = com.eagle.programmar.CMacro.Pragmas.CMacro_Pragma_Warn;
	using CMacro_Pragma_Warning = com.eagle.programmar.CMacro.Pragmas.CMacro_Pragma_Warning;
	using CMacro_Keyword = com.eagle.programmar.CMacro.Terminals.CMacro_Keyword;
	using CMacro_Punctuation = com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	//
	// Still a couple in Chromium:
	//             #pragma map    /chromium-main/third_party/protobuf/objectivec/GPBArray.h
	//             #pragma map    /chromium-main/third_party/protobuf/objectivec/GPBDictionary.h
	//             #pragma map    /chromium-main/third_party/protobuf/objectivec/GPBWellKnownTypes.h
	//   #pragma   data_seg(      /chromium-main/sandbox/win/tests/integration_tests/hooking_dll.cc
	//   #pragma   optimize(      /chromium-main/base/third_party/double_conversion/double-conversion/string-to-double.cc
	//   #pragma   push_macro     /chromium-main/ui/gl/init/gl_initializer_angle.cc
	//   #pragma   section(       /chromium-main/third_party/crashpad/crashpad/snapshot/crashpad_info_size_test_module.cc
	//

	public class CMacro_Pragma_Statement : TokenSequence, CMacro_Processable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation pound = new com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation('#');
		public CMacro_Punctuation pound = new CMacro_Punctuation('#');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @DOC("Pragmas.html") com.eagle.programmar.CMacro.Terminals.CMacro_Keyword PRAGMA = new com.eagle.programmar.CMacro.Terminals.CMacro_Keyword("pragma");
		public @DOC("Pragmas.html") CMacro_Keyword PRAGMA = new CMacro_Keyword("pragma");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) CMacro_Pragma_Type what;
		public CMacro_Pragma_Type what;

		public static class CMacro_Pragma_Type extends TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMacro_Keyword XXONCE = new com.eagle.programmar.CMacro.Terminals.CMacro_Keyword("once");
			public CMacro_Keyword XXONCE = new CMacro_Keyword("once");

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMacro_Pragma_CLang XXclang;
			public CMacro_Pragma_CLang XXclang;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMacro_Pragma_CodeSeg XXcodeSeg;
			public CMacro_Pragma_CodeSeg XXcodeSeg;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMacro_Pragma_Comment XXcomment;
			public CMacro_Pragma_Comment XXcomment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMacro_Pragma_Export XXexprt;
			public CMacro_Pragma_Export XXexprt;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMacro_Pragma_Intrinsic XXintrinsic;
			public CMacro_Pragma_Intrinsic XXintrinsic;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMacro_Pragma_Mark XXmark;
			public CMacro_Pragma_Mark XXmark;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMacro_Pragma_Message XXmessage;
			public CMacro_Pragma_Message XXmessage;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMacro_Pragma_Pack XXpack;
			public CMacro_Pragma_Pack XXpack;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMacro_Pragma_Region XXregion;
			public CMacro_Pragma_Region XXregion;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMacro_Pragma_RuntimeChecks XXruntimeChecks;
			public CMacro_Pragma_RuntimeChecks XXruntimeChecks;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMacro_Pragma_STDC XXstdc;
			public CMacro_Pragma_STDC XXstdc;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMacro_Pragma_Unroll XXunroll;
			public CMacro_Pragma_Unroll XXunroll;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMacro_Pragma_Warn XXwarn;
			public CMacro_Pragma_Warn XXwarn;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CMacro_Pragma_Warning XXwarning;
			public CMacro_Pragma_Warning XXwarning;
		}

		public bool processMacro(CMacro_Preprocess preprocessor)
		{
			// Nothing to do
			return false; // false means we didn't change anything
		}
	}

}
