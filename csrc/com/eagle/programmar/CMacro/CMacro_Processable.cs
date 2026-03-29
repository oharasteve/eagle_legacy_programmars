// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 14, 2015

namespace com.eagle.programmar.CMacro
{
	using CMacro_Preprocess = com.eagle.preprocess.CMacro.CMacro_Preprocess;

	public interface CMacro_Processable
	{
		// Return true iff any changes were made.
		bool processMacro(CMacro_Preprocess preprocessor);
	}

}
