// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 19, 2016

package com.eagle.programmar.CSharp;

import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public class CSharp_Argument extends TokenChooser
{
	public @CHOICE static class CSharp_ArgumentRef extends TokenSequence
	{
		public @S(10) CSharp_Keyword REF = new CSharp_Keyword("ref");
		public @S(20) CSharp_Expression arg;
	}

	public @CHOICE static class CSharp_ArgumentOutType extends TokenSequence
	{
		public @S(10) CSharp_Keyword OUT = new CSharp_Keyword("out");
		public @S(20) CSharp_Type type;
		public @S(30) CSharp_Expression arg;
	}

	public @LAST static class CSharp_ArgumentOut extends TokenSequence
	{
		public @S(10) @OPT CSharp_Keyword OUT = new CSharp_Keyword("out");
		public @S(20) CSharp_Expression arg;
	}
}
