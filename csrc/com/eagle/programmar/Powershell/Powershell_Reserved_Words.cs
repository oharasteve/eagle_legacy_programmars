// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 11, 2022

namespace com.eagle.programmar.Powershell
{
	public class Powershell_Reserved_Words
	{
		// From
		// https://docs.microsoft.com/en-us/powershell/module/microsoft.powershell.core/about/about_reserved_words?view=powershell-7.2
		public static readonly string[] RESERVED_WORDS = new string[] {"begin", "break", "catch", "command", "configuration", "continue", "define", "do", "dynamicparam", "else", "elseif", "end", "enum", "exit", "finally", "for", "foreach", "function", "hidden", "if", "in", "inlinescript", "interface", "module", "parallel", "private", "process", "public", "return", "sequence", "static", "switch", "throw", "trap", "try", "type", "until", "using", "where", "where-object", "while", "workflow"};

		public static readonly string[] HYPHEN_WORDS = new string[] {"eq", "ne", "gt", "ge", "lt", "le", "match", "notmatch", "in", "notin", "and", "or", "not"};
	}

}
