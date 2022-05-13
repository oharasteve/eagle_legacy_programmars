// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2011

package com.eagle.programmar.CMacro.Statements;

import com.eagle.preprocess.CMacro.CMacro_Preprocess;
import com.eagle.programmar.CMacro.CMacro_Processable;
import com.eagle.programmar.CMacro.Terminals.CMacro_CommentRestOfLine;
import com.eagle.programmar.CMacro.Terminals.CMacro_Identifier;
import com.eagle.programmar.CMacro.Terminals.CMacro_Keyword;
import com.eagle.programmar.CMacro.Terminals.CMacro_KeywordChoice;
import com.eagle.programmar.CMacro.Terminals.CMacro_Literal;
import com.eagle.programmar.CMacro.Terminals.CMacro_Number;
import com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationHyphen;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

//
// Still a couple in Chromium:
//             #pragma ma    /chromium-main/third_party/protobuf/objectivec/GPBArray.h
//             #pragma ma    /chromium-main/third_party/protobuf/objectivec/GPBDictionary.h
//             #pragma ma    /chromium-main/third_party/protobuf/objectivec/GPBWellKnownTypes.h
//   #pragma   data_seg(     /chromium-main/sandbox/win/tests/integration_tests/hooking_dll.cc
//   #pragma   optimize(     /chromium-main/base/third_party/double_conversion/double-conversion/string-to-double.cc
//   #pragma   push_macro    /chromium-main/ui/gl/init/gl_initializer_angle.cc
//   #pragma   section(      /chromium-main/third_party/crashpad/crashpad/snapshot/crashpad_info_size_test_module.cc
//

public class CMacro_Pragma_Statement extends TokenSequence implements CMacro_Processable
{
	public @S(10) CMacro_Punctuation pound = new CMacro_Punctuation('#'); 
	public @S(20) @DOC("Pragmas.html") CMacro_Keyword PRAGMA = new CMacro_Keyword("pragma");
	public @S(30) CMacro_Pragma_Type what;
	
	public static class CMacro_Pragma_Type extends TokenChooser
	{
		public @CHOICE CMacro_Keyword ONCE = new CMacro_Keyword("once");

		// PLEASE KEEP THESE IN ALPHANETICAL ORDER, on class name
		
		public @CHOICE static class CMacro_Pragma_CLang extends TokenSequence
		{
			public @S(10) CMacro_KeywordChoice CLANG = new CMacro_KeywordChoice("clang", "GCC");
			public @S(20) CMacro_KeywordChoice DIAGNOSTIC = new CMacro_KeywordChoice("diagnostic", "optimize");
			public @S(30) CMacro_Pragma_CLang_What what;
			
			public static class CMacro_Pragma_CLang_What extends TokenChooser
			{
				public @CHOICE CMacro_Literal literal;
				
				public @CHOICE CMacro_KeywordChoice PUSH = new CMacro_KeywordChoice("push", "pop");
				
				public @CHOICE static class CMacro_Pragma_CLangOptimize extends TokenSequence
				{
					public @S(10) PunctuationLeftParen leftParen;
					public @S(20) CMacro_Literal literal;	// Such as "fp-contract=off"
					public @S(30) PunctuationRightParen rightParen;
				}
				
				public @CHOICE static class CMacro_Pragma_CLangIgnored extends TokenSequence
				{
					public @S(10) CMacro_Keyword IGNORED = new CMacro_Keyword("ignored");
					public @S(20) CMacro_Literal warning;	// e.g., "-Wunguarded-availability"
				}
			}
		}
		
		public @CHOICE static class CMacro_Pragma_Comment extends TokenSequence
		{
			public @S(10) CMacro_Keyword COMMENT = new CMacro_Keyword("comment");
			public @S(20) PunctuationLeftParen leftParen;
			public @S(30) CMacro_Keyword LIB = new CMacro_Keyword("lib");
			public @S(40) PunctuationComma comma;
			public @S(50) CMacro_Literal literal;
			public @S(60) PunctuationRightParen rightParen;
		}
		
		public @CHOICE static class CMacro_Pragma_Export extends TokenSequence
		{
			public @S(10) CMacro_KeywordChoice EXPORT = new CMacro_KeywordChoice("export", "import");
			public @S(20) CMacro_KeywordChoice ON = new CMacro_KeywordChoice("on", "off");
		}
		
		public @CHOICE static class CMacro_Pragma_Intrinsic extends TokenSequence
		{
			public @S(10) CMacro_Keyword INTRINSIC = new CMacro_Keyword("intrinsic");
			public @S(20) PunctuationLeftParen leftParen;
			public @S(30) @OPT CMacro_KeywordChoice ROT =
					new CMacro_KeywordChoice("rotl", "rotr", "_rotl", "_rotr",
							"_BitScanReverse",
							"_InterlockedCompareExchange", "_InterlockedCompareExchangePointer",
							"_umul128");
			public @S(40) PunctuationRightParen rightParen;
		}
		
		public @CHOICE static class CMacro_Pragma_Mark extends TokenSequence
		{
			public @S(10) CMacro_Keyword MARK = new CMacro_Keyword("mark");
			public @S(20) @OPT PunctuationHyphen minus;
			public @S(30) @OPT CMacro_CommentRestOfLine what;
		}
		
		public @CHOICE static class CMacro_Pragma_Message extends TokenSequence
		{
			public @S(10) CMacro_Keyword MESSAGE = new CMacro_Keyword("message");
			public @S(20) @OPT CMacro_Keyword DISABLE = new CMacro_Keyword("disable");
			public @S(30) PunctuationLeftParen leftParen;
			public @S(40) SeparatedList<CMacro_PragmaMsg, PunctuationComma> codes;
			public @S(50) PunctuationRightParen rightParen;
			
			public static class CMacro_PragmaMsg extends TokenChooser
			{
				public @CHOICE CMacro_Literal literal;
				public @CHOICE CMacro_KeywordChoice UNDERFLOW =
						new CMacro_KeywordChoice("UNDERFLOW", "FLOATOVERFL", "nosimpint");
			}
		}

		public @CHOICE static class CMacro_Pragma_Pack extends TokenSequence
		{
			public @S(10) CMacro_Keyword PACK = new CMacro_Keyword("pack");
			public @S(20) PunctuationLeftParen leftParen;
			public @S(30) @OPT CMacro_KeywordChoice PUSH = new CMacro_KeywordChoice("push", "pop");
			public @S(40) @OPT PunctuationComma comma;
			public @S(50) @OPT CMacro_Number number;	// 1 perhaps
			public @S(60) PunctuationRightParen rightParen;
		}
		
		public @CHOICE static class CMacro_Pragma_Region extends TokenSequence
		{
			public @S(10) CMacro_Keyword REGION = new CMacro_Keyword("region");
			public @S(20) @OPT CMacro_CommentRestOfLine what;
		}

		public @CHOICE static class CMacro_Pragma_RuntimeChecks extends TokenSequence
		{
			public @S(10) CMacro_Keyword RUNTIMECHECKS = new CMacro_Keyword("runtime_checks");
			public @S(20) PunctuationLeftParen leftParen;
			public @S(30) CMacro_Literal what;
			public @S(40) @OPT PunctuationComma comma1;
			public @S(50) @OPT CMacro_Keyword OFF = new CMacro_Keyword("off");
			public @S(60) @OPT PunctuationComma comma2;
			public @S(70) @OPT CMacro_Number number;	// 1 perhaps
			public @S(80) PunctuationRightParen rightParen;
		}

		public @CHOICE static class CMacro_Pragma_STDC extends TokenSequence
		{
			public @S(10) CMacro_Keyword STDC = new CMacro_Keyword("STDC");
			public @S(20) CMacro_Keyword FP_CONTRACT = new CMacro_Keyword("FP_CONTRACT");
			public @S(30) CMacro_Keyword OFF = new CMacro_Keyword("OFF");
		}
		
		public @CHOICE static class CMacro_Pragma_Unroll extends TokenSequence
		{
			public @S(10) CMacro_Keyword UNROLL = new CMacro_Keyword("unroll");
			public @S(20) CMacro_Number what;	// 1 perhaps
		}

		public @CHOICE static class CMacro_Pragma_Warn extends TokenSequence
		{
			public @S(10) CMacro_Keyword WARN = new CMacro_Keyword("warn");
			public @S(20) @OPT PunctuationHyphen minus;
			public @S(30) CMacro_Number what;	// 8004 8008 8066 perhaps
		}

		public @CHOICE static class CMacro_Pragma_Warning extends TokenSequence
		{
			public @S(10) CMacro_Keyword WARNING = new CMacro_Keyword("warning");
			public @S(20) @OPT PunctuationLeftParen leftParen;
			public @S(30) CMacro_KeywordChoice DISABLE =
					new CMacro_KeywordChoice("disable", "restore", "push", "pop", "default");
			public @S(40) @OPT PunctuationColon colon;
			public @S(50) @OPT TokenList<CMacro_PragmaCode> codes;
			public @S(60) @OPT PunctuationRightParen rightParen;
			
			public static class CMacro_PragmaCode extends TokenChooser
			{
				public @CHOICE PunctuationComma comma;
				public @CHOICE CMacro_Number number;	// 1718 1501 0612 3021 4702 etc etc
				public @CHOICE CMacro_Identifier code;	// CS0618 and CS1718
			}
		}
	}
	
	@Override
	public boolean processMacro(CMacro_Preprocess preprocessor)
	{
		// Nothing to do
		return false;	// false means we didn't change anything
	}
}
