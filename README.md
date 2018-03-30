# My VM Benchmarks using JMH

Some JVM performance benchmarks using JMH

Currently the tests are :

DynamicLanguages.groovy

ExceptionsBenchMark.throwRuntimeException

GeneratePoiWorkBook.generatePoiXLSWorkBook

GeneratePoiWorkBook.generatePoiXLSXWorkBook

GeneratePrimeNumbersWithForLoop.generatePrime

Jackson.jsonMashal

Jackson.jsonUnmashal

Jackson.xmlMashalJackson

Jackson.xmlMashalJacksonStatic

Jackson.xmlUnmashalJackson

Jackson.xmlUnmashalJacksonStatic

JaxbMashalUnMashal.mashal

JaxbMashalUnMashal.mashalStatic

JaxbMashalUnMashal.unmashal

JaxbMashalUnMashal.unmashalStatic

Reflection.doReflection

RegularExpressions.validateEmail

#To Build
mvn install

#To run
java -jar target/commonscenario-baseline-uber.jar
