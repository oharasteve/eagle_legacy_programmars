// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Nov 24, 2019

namespace com.eagle.programmar.Rust.Terminals
{
	using Rust_Expression = com.eagle.programmar.Rust.Rust_Expression;
	using Rust_Generator = com.eagle.programmar.Rust.Rust_Generator;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TerminalLiteralToken = com.eagle.tokens.terminals.TerminalLiteralToken;

	public class Rust_Literal : TerminalLiteralToken
	{
		public Rust_Literal() : base("\"", true, '\\', false, false)
		{
		}

		public static Rust_Literal generateLiteral(string value, AbstractToken source)
		{
			Rust_Literal lit = new Rust_Literal();
			string val = '"' + value.replaceAll("\\\"", "\\\\\"").replaceAll("\n", "\\n") + '"'; lit.setValue(val); lit.setTransformationSource(source); return lit;
			}
			public static Rust_Expression generateLiteralExpression(string value, AbstractToken source) {return Rust_Generator.wrapExpression(generateLiteral(value, source));}
			}

		}
