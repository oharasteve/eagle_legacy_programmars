// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.FSharp;

import com.eagle.programmar.FSharp.Terminals.FSharp_KeywordChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.interfaces.AbstractType;

public class FSharp_Type extends TokenChooser implements AbstractType
{
	public @CHOICE FSharp_KeywordChoice XXTYPES = new FSharp_KeywordChoice("bool", "int", "string");
}
