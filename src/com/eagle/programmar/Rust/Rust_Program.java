// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 20, 2019

package com.eagle.programmar.Rust;

import com.eagle.core.EagleLanguage;

public class Rust_Program extends EagleLanguage
{
	public static final String NAME = "Rust";
	
	public Rust_Program()
	{
		super(NAME, new Rust_Syntax());
	}
	
	@Override
	public String getDocRoot()
	{
		return "https://doc.rust-lang.org/reference";
	}
}