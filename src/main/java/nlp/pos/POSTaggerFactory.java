package nlp.pos;

import main.Config;
import nlp.tokenize.AbstractTokenizer;
import nlp.tokenize.OpennlpTokenizer;
import nlp.tokenize.TokenizerFactory;
import opennlp.MyTokenizer;
import opennlp.POStagger;

public class POSTaggerFactory {
	
	public static AbstractPOSTagger get(String language){
		try{
			if(language.equals(Config.LANG_EN)){
				return new OpennlpPOSTagger(Config.POSTAGGER_PATH_EN, 
						TokenizerFactory.get(Config.LANG_EN));
			}
			
			if(language.equals(Config.LANG_NL)){
				return new OpennlpPOSTagger(Config.TOKENIZR_PATH_NL, 
						TokenizerFactory.get(Config.LANG_NL));
			}
			
			if(language.equals(Config.LANG_GR)){
				// TODO: implement here
			}
			
			return null;
		}catch ( Exception e ){
			return null;
		}
	}
	
	
	
}