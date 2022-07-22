// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 21, 2022

package com.eagle.programmar.Bash;

import com.eagle.programmar.Bash.Terminals.Bash_Filename;
import com.eagle.programmar.Bash.Terminals.Bash_PunctuationChoice;
import com.eagle.tokens.TokenChooser;

public class Bash_FilenameOrLiteral extends TokenChooser
{
	public @CHOICE Bash_Filename filename;
	public @CHOICE Bash_PunctuationChoice star = new Bash_PunctuationChoice("*");
	public @LAST Bash_Expression expression;
}
