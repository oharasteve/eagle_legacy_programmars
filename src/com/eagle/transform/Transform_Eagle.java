// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 19, 2015

package com.eagle.transform;

import com.eagle.core.AbstractLanguage;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;

public abstract class Transform_Eagle
{
	public abstract AbstractExpression transformExpression(AbstractExpression expr);

	public abstract AbstractStatement transformStatement(AbstractStatement stmt);

	public abstract void transformFromXML(AbstractLanguage pgm, String sourceName, String targetName);
}
