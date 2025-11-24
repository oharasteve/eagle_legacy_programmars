// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 6, 2024

package com.eagle.programmar.Java;

import com.eagle.programmar.Java.Terminals.Java_Identifier;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Java_Package extends TokenSequence
{
	public @S(10) @BLANKLINE Java_Keyword PACKAGE = new Java_Keyword("package");
	public @S(20) Java_Identifier id;
	public @S(30) @OPT TokenList<Java_MorePackageIds> moreIds;
	public @S(40) @NOSPACE PunctuationSemicolon semicolon;

	public static class Java_MorePackageIds extends TokenSequence
	{
		public @S(10) @NOSPACE PunctuationPeriod dot;
		public @S(20) @NOSPACE Java_Identifier id;
	}

	public static Java_Package newPackage(String pkgName)
	{
		Java_Package pkg = new Java_Package();
		String[] pieces = pkgName.split("\\.");
		pkg.id = new Java_Identifier();
		pkg.id.setValue(pieces[0]);

		if (pieces.length > 1)
		{
			pkg.moreIds = new TokenList<Java_MorePackageIds>();
			pkg.moreIds.setPresent(true);
			boolean skip = true;
			for (String piece : pieces)
			{
				if (skip)
				{
					// Already did first piece
					skip = false;
					continue;
				}

				Java_MorePackageIds more = new Java_MorePackageIds();
				more.dot = new PunctuationPeriod();
				more.id = new Java_Identifier();
				more.id.setValue(piece);
				pkg.moreIds.addToken(more);
			}
		}

		pkg.semicolon = new PunctuationSemicolon();
		return pkg;
	}
}
