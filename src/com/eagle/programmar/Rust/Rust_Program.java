// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 20, 2019

package com.eagle.programmar.Rust;

import com.eagle.core.EagleLanguage;
import com.eagle.programmar.Rust.Terminals.Rust_Comment;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;

public class Rust_Program extends EagleLanguage
{
	public static final String RUST = "Rust";
	
	public Rust_Program()
	{
		super(RUST, new Rust_Syntax());
	}
	
	@Override
	public String getDocRoot()
	{
		return "https://doc.rust-lang.org/reference/";
	}
	
	public @S(10) TokenList<Rust_Element> elements;
	
	public static class Rust_Element extends TokenChooser
	{
	  public @CHOICE Rust_Comment comment;
	  public @CHOICE Rust_Function function;
	  public @CHOICE Rust_Module module;
	  public @CHOICE Rust_Data data;
	  public @CHOICE Rust_Use use;
	}
}