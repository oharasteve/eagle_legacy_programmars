// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Nov 24, 2019

package com.eagle.programmar.Rust.Terminals;

import com.eagle.interpret.EagleRunnable;
import com.eagle.parsers.EagleFileReader;
import com.eagle.programmar.Rust.Terminals.Rust_Number;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.terminals.TerminalNumberToken;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Rust_Number extends TerminalNumberToken
		implements EagleRunnable, EagleTransformableExpression

{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		return genericNumber(lines, "Ee", "LlFfDd", true, true, '_');
	}

	@Override
	public String description()
	{
		return super.genericDescription("Ee", "LlFfDd", true, true, '_');
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		return generator.newNumberExpression(_numberAsText, this);
	}

	public Rust_Number generateNumber(String value, AbstractToken source)
	{
		this.setValue(value);
		this.setTransformationSource(source);
		return this;
	}
}
