// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 19, 2026

package com.eagle.programmar.Rust.Statements;

import com.eagle.programmar.Rust.Rust_Generator;
import com.eagle.programmar.Rust.Rust_Statement;
import com.eagle.programmar.Rust.Terminals.Rust_KeywordChoice;
import com.eagle.programmar.Rust.Terminals.Rust_Punctuation;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator.PragmaEnum;

public class Rust_Pragma extends TokenSequence
{
	// 	#[allow(unreachable_code)]
	public @S(10) @NEWLINE Rust_Punctuation pount = new Rust_Punctuation("#");
	public @S(20) @NOSPACE PunctuationLeftBracket leftBracket;
	public @S(30) @NOSPACE Rust_KeywordChoice ALLOW = new Rust_KeywordChoice("allow");
	public @S(40) @NOSPACE PunctuationLeftParen leftParen;
	public @S(50) @NOSPACE Rust_KeywordChoice UNREACH = new Rust_KeywordChoice("unreachable_code");
	public @S(60) @NOSPACE PunctuationRightParen rightParen;
	public @S(70) @NOSPACE PunctuationRightBracket rightBracket;
	
	public static Rust_Statement generatePragma(PragmaEnum prag, AbstractToken source)
	{
		String code;
		switch (prag)
		{
		case IGNORE_UNREACHABLE_CODE:
			code = "unreachable_code";
			break;
		default:
			return null;
		}
		
		Rust_Pragma stmt = new Rust_Pragma();
		stmt.leftBracket = new PunctuationLeftBracket();
		stmt.ALLOW.setValue("allow");
		stmt.leftParen = new PunctuationLeftParen();
		stmt.UNREACH.setValue(code);
		stmt.rightParen = new PunctuationRightParen();
		stmt.rightBracket = new PunctuationRightBracket();
		
		stmt.setTransformationSource(source);
		return Rust_Generator.wrapStatement(stmt);
	}
}
