// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 11, 2022

package com.eagle.programmar.CSharp.Directives;

import com.eagle.tokens.TokenChooser;

public class CSharp_Directive extends TokenChooser
{
	public @CHOICE CSharp_PragmaDirective XXpragmaDirective;
	public @CHOICE CSharp_RegionDirective XXregionDirective;
}
