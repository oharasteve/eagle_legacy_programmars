// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 17, 2020

package com.eagle.programmar.BNF.Terminals;

import com.eagle.programmar.Delphi.Terminals.Delphi_Comment;
import com.eagle.programmar.Delphi.Terminals.Delphi_Include;
import com.eagle.tokens.TokenChooser;

public class BNF_Comment extends TokenChooser
{
	public @CHOICE Delphi_Comment comment;
	public @CHOICE Delphi_Include include;
}
