// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 1, 2010

package com.eagle.programmar.COBOL;

import com.eagle.core.AbstractLanguage;

/**
 * COBOL grammar
 */

public abstract class COBOL_Program extends AbstractLanguage
{
	public COBOL_Program(String name, COBOL_Syntax syntax)
	{
		super(name, syntax);
	}

	@Override
	public String getDocRoot()
	{
		return "https://www.ibm.com/support/knowledgecenter/SS6SG3_6.3.0/lr/ref/%l";
	}
}
