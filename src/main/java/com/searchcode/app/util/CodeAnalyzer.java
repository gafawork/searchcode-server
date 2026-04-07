package com.searchcode.app.util;


import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.FilteringTokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.core.WhitespaceTokenizer;
import org.apache.lucene.analysis.miscellaneous.ASCIIFoldingFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.snowball.SnowballFilter;
import org.apache.lucene.analysis.pattern.PatternCaptureGroupTokenFilter;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.miscellaneous.LimitTokenCountFilter;
import org.tartarus.snowball.SnowballProgram;
import com.searchcode.app.util.Properties;
import com.searchcode.app.config.Values;
import java.util.logging.Logger;

import java.util.regex.Pattern;


// TODO VERIFICAR TESTE UNITÁRIOS IndexServiceTest.testIndexReversedFilename:569
// TODO VERIFICAR QUANDO FILTRA POR REPOISTORIO
// TODO VERIFICAR BUSCAR LIETERAIS E POR FILTRO DE ARQUIVOS POM.XML

public class CodeAnalyzer extends Analyzer {
    public CodeAnalyzer() {
    }

    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
        //final Tokenizer source = new CodeTokenizer();
        final Tokenizer source = new WhitespaceTokenizer();
        //TokenStream result = new LengthFilter(source, 0, Integer.MAX_VALUE);
        TokenStream result = new LengthFilter(source, 0, 500); // should be enough I hope
        return new TokenStreamComponents(source, result);
    }

//    @Override
//    protected TokenStreamComponents createComponents(String fieldName) {
//        Tokenizer source = new StandardTokenizer();
//
//        TokenStream tokenStream = new LowerCaseFilter(source);
//
//        // Normaliza acentos (ex: conexão -> conexao)
//        tokenStream = new ASCIIFoldingFilter(tokenStream);
//
//        // Divide camelCase e snake_case (ex: minhaFuncao -> [minha, funcao])
//        tokenStream = new PatternCaptureGroupTokenFilter(
//                tokenStream,
//                true,
//                Pattern.compile("([a-z]+)([A-Z][a-z]+)"),   // camelCase
//                Pattern.compile("([a-zA-Z]+)_([a-zA-Z]+)")  // snake_case
//        );
//
//        // Stemming configurável via propriedades
////        boolean snowballEnabled = Boolean.parseBoolean(Properties.getProperties().getProperty(
////                Values.ANALYZER_SNOWBALL_ENABLED, Values.DEFAULT_ANALYZER_SNOWBALL_ENABLED));
////        if (snowballEnabled) {
////            String stemmerClassName = Properties.getProperties().getProperty(
////                    Values.ANALYZER_SNOWBALL_STEMMER_CLASS,
////                    Values.DEFAULT_ANALYZER_SNOWBALL_STEMMER_CLASS);
////            try {
////                Class<?> clazz = Class.forName(stemmerClassName);
////                if (!SnowballProgram.class.isAssignableFrom(clazz)) {
////                    Logger.getLogger(CodeAnalyzer.class.getName()).warning("Configured stemmer does not extend SnowballProgram: " + stemmerClassName + ". Falling back to default.");
////                    clazz = Class.forName(Values.DEFAULT_ANALYZER_SNOWBALL_STEMMER_CLASS);
////                }
////                SnowballProgram stemmer = (SnowballProgram) clazz.getDeclaredConstructor().newInstance();
////                tokenStream = new SnowballFilter(tokenStream, stemmer);
////            } catch (Exception e) {
////                Logger.getLogger(CodeAnalyzer.class.getName()).warning("Could not instantiate stemmer '" + stemmerClassName + "': " + e.getMessage() + ". Disabling stemming for this analyzer.");
////            }
////        }
//
//        // Limita tamanho de tokens extremos (filtro auxiliar)
//        TokenStream result = new LengthFilter(tokenStream, 0, 500);
//
//        return new TokenStreamComponents(source, result);
//    }

//
//    final class LengthFilter extends FilteringTokenFilter {
//
//        private final int min;
//        private final int max;
//
//        private final CharTermAttribute termAtt = addAttribute(CharTermAttribute.class);
//
//        /**
//         * Create a new LengthFilter. This will filter out tokens whose
//         * CharTermAttribute is either too short
//         * (< min) or too long (> max).
//         *
//         * @param in  the TokenStream to consume
//         * @param min the minimum length
//         * @param max the maximum length
//         */
//        public LengthFilter(TokenStream in, int min, int max) {
//            super(in);
//            this.min = min;
//            this.max = max;
//        }
//
//        @Override
//        public boolean accept() {
//            final int len = termAtt.length();
//            return (len >= min && len <= max);
//        }
//
//    }


}
