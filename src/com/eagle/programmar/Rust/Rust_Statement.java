// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 20, 2019

package com.eagle.programmar.Rust;

import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class Rust_Statement extends TokenChooser implements AbstractStatement {
	public static @CHOICE class Rust_Block_Statement extends TokenSequence {
		public PunctuationLeftBrace leftBrace;
		public PunctuationRightBrace rightBrace;
	}
}
