package br.com.erudio.math;

import br.com.erudio.converters.NumberConverter;
import br.com.erudio.exceptions.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.RequestMethod;

import static br.com.erudio.converters.NumberConverter.isNumeric;

public class SimpleMath {


    public Double sum(double num1, double num2) {return (num1) + (num2);}

    public Double sub(double num1, double num2) {return (num1) - (num2);}

    public Double mult(double num1, double num2)  {return (num1) * (num2);}

    public Double div(double num1, double num2)  {return num1 / num2;}

    public Double avg(double num1, double num2)  {return ((num1) + (num2)) / 2;}

    public Double sqrt(Double num1)  {return Math.sqrt(num1);}

    public Double pow(Double num1)  {return Math.pow(num1,2);}
}
