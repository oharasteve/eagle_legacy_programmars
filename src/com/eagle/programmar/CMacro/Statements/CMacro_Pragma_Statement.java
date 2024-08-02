// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2011

package com.eagle.programmar.CMacro.Statements;

import com.eagle.preprocess.CMacro.CMacro_Preprocess;
import com.eagle.programmar.CMacro.CMacro_Processable;
import com.eagle.programmar.CMacro.Pragmas.CMacro_Pragma_CLang;
import com.eagle.programmar.CMacro.Pragmas.CMacro_Pragma_CodeSeg;
import com.eagle.programmar.CMacro.Pragmas.CMacro_Pragma_Comment;
import com.eagle.programmar.CMacro.Pragmas.CMacro_Pragma_Export;
import com.eagle.programmar.CMacro.Pragmas.CMacro_Pragma_Intrinsic;
import com.eagle.programmar.CMacro.Pragmas.CMacro_Pragma_Mark;
import com.eagle.programmar.CMacro.Pragmas.CMacro_Pragma_Message;
import com.eagle.programmar.CMacro.Pragmas.CMacro_Pragma_Pack;
import com.eagle.programmar.CMacro.Pragmas.CMacro_Pragma_Region;
import com.eagle.programmar.CMacro.Pragmas.CMacro_Pragma_RuntimeChecks;
import com.eagle.programmar.CMacro.Pragmas.CMacro_Pragma_STDC;
import com.eagle.programmar.CMacro.Pragmas.CMacro_Pragma_Unroll;
import com.eagle.programmar.CMacro.Pragmas.CMacro_Pragma_Warn;
import com.eagle.programmar.CMacro.Pragmas.CMacro_Pragma_Warning;
import com.eagle.programmar.CMacro.Terminals.CMacro_Keyword;
import com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

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

public class CMacro_Pragma_Statement extends TokenSequence implements CMacro_Processable
{
	public @S(10) CMacro_Punctuation pound = new CMacro_Punctuation('#');
	public @S(20) @DOC("Pragmas.html") CMacro_Keyword PRAGMA = new CMacro_Keyword("pragma");
	public @S(30) CMacro_Pragma_Type what;

	public static class CMacro_Pragma_Type extends TokenChooser
	{
		public @CHOICE CMacro_Keyword XXONCE = new CMacro_Keyword("once");

		public @CHOICE CMacro_Pragma_CLang XXclang;
		public @CHOICE CMacro_Pragma_CodeSeg XXcodeSeg;
		public @CHOICE CMacro_Pragma_Comment XXcomment;
		public @CHOICE CMacro_Pragma_Export XXexprt;
		public @CHOICE CMacro_Pragma_Intrinsic XXintrinsic;
		public @CHOICE CMacro_Pragma_Mark XXmark;
		public @CHOICE CMacro_Pragma_Message XXmessage;
		public @CHOICE CMacro_Pragma_Pack XXpack;
		public @CHOICE CMacro_Pragma_Region XXregion;
		public @CHOICE CMacro_Pragma_RuntimeChecks XXruntimeChecks;
		public @CHOICE CMacro_Pragma_STDC XXstdc;
		public @CHOICE CMacro_Pragma_Unroll XXunroll;
		public @CHOICE CMacro_Pragma_Warn XXwarn;
		public @CHOICE CMacro_Pragma_Warning XXwarning;
	}

	@Override
	public boolean processMacro(CMacro_Preprocess preprocessor)
	{
		// Nothing to do
		return false; // false means we didn't change anything
	}
}
