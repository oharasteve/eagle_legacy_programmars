// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 6, 2024

package com.eagle.programmar.Java;

import com.eagle.programmar.CSharp.CSharp_Namespace;
import com.eagle.programmar.CSharp.CSharp_Program.CSharp_NamespaceOrClassEntry;
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

	public static CSharp_Namespace translate(Java_Package jpackage, TokenList<CSharp_NamespaceOrClassEntry> entries)
	{
		return null;
//		CSharp_Namespace namespace = new CSharp_Namespace();
//		namespace.id = new CSharp_Identifier();
//		namespace.id.setValue(jpackage.id.getValue());
//		namespace.moreIds = new TokenList<CSharp_MoreNamespaceId>();
//		namespace.leftBrace = new PunctuationLeftBrace();
//		namespace.elems = new TokenList<CSharp_ProgramElems>();
//		namespace.rightBrace = new PunctuationRightBrace();
//
//		for (Java_MorePackageIds nxt : jpackage.moreIds._elements)
//		{
//			CSharp_MoreNamespaceId more = new CSharp_MoreNamespaceId();
//			more.id = new CSharp_Identifier();
//			more.id.setValue(nxt.id.getValue());
//			more.dot = new PunctuationPeriod();
//			namespace.moreIds.addToken(more);
//		}
//
//		CSharp_NamespaceOrClassEntry entry = new CSharp_NamespaceOrClassEntry();
//		entry.setWhich(namespace);
//		entries.addToken(entry);
//		return namespace;
	}
}
