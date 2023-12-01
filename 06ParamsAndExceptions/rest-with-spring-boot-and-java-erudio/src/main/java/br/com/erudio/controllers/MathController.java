package br.com.erudio.controllers;

import java.util.concurrent.atomic.AtomicLong;
import br.com.erudio.converters.NumberConverter;
import br.com.erudio.exceptions.UnsupportedMathOperationException;
import br.com.erudio.math.SimpleMath;
import org.springframework.web.bind.annotation.*;

import static br.com.erudio.converters.NumberConverter.convertToDouble;
import static br.com.erudio.converters.NumberConverter.isNumeric;

@RestController
public class MathController {

    private SimpleMath simpleMath = new SimpleMath();

    @RequestMapping(value = "/sum/{num1}/{num2}",
        method = RequestMethod.GET)
    public Double sum(
            @PathVariable(value = "num1") String num1,
            @PathVariable(value = "num2") String num2
        ) throws Exception{

        if(!isNumeric(num1) || !isNumeric(num2)){ throw new RuntimeException();}
        return simpleMath.sum(NumberConverter.convertToDouble(num1),NumberConverter.convertToDouble(num2));
    }

    @RequestMapping(value = "/sub/{num1}/{num2}",
            method = RequestMethod.GET)
    public Double sub(
            @PathVariable(value = "num1") String num1,
            @PathVariable(value = "num2") String num2
    ) throws Exception{

        if(!isNumeric(num1) || !isNumeric(num2)){ throw new UnsupportedMathOperationException("Please set a numeric value!");}
        return simpleMath.sub(NumberConverter.convertToDouble(num1),NumberConverter.convertToDouble(num2));
    }

    @RequestMapping(value = "/mult/{num1}/{num2}",
            method = RequestMethod.GET)
    public Double mult(
            @PathVariable(value = "num1") String num1,
            @PathVariable(value = "num2") String num2
    ) throws Exception{

        if(!isNumeric(num1) || !isNumeric(num2)){ throw new UnsupportedMathOperationException("Please set a numeric value!");}
        return simpleMath.mult(NumberConverter.convertToDouble(num1),NumberConverter.convertToDouble(num2));
    }

    @RequestMapping(value = "/div/{num1}/{num2}",
            method = RequestMethod.GET)
    public Double div(
            @PathVariable(value = "num1") String num1,
            @PathVariable(value = "num2") String num2
    ) throws Exception{

        if(!isNumeric(num1) || !isNumeric(num2)){ throw new UnsupportedMathOperationException("Please set a numeric value!");}
        if(num1.equals("0")|| num2.equals("0")){throw new UnsupportedMathOperationException("Zero is not a valid number!");}
        return simpleMath.div(NumberConverter.convertToDouble(num1),NumberConverter.convertToDouble(num2));
    }

    @RequestMapping(value = "/avg/{num1}/{num2}",
            method = RequestMethod.GET)
    public Double avg(
            @PathVariable(value = "num1") String num1,
            @PathVariable(value = "num2") String num2
    ) throws Exception{

        if(!isNumeric(num1) || !isNumeric(num2)){ throw new UnsupportedMathOperationException("Please set a numeric value!");}
        return simpleMath.avg(NumberConverter.convertToDouble(num1),NumberConverter.convertToDouble(num2));
    }

    @RequestMapping(value = "/sqrt/{num1}",
            method = RequestMethod.GET)
    public Double sqrt(
            @PathVariable(value = "num1") String num1
    ) throws Exception{

        if(!isNumeric(num1)){ throw new UnsupportedMathOperationException("Please set a numeric value!");}
        double sqrtNum = NumberConverter.convertToDouble(num1);
        if(sqrtNum<0){ throw new UnsupportedMathOperationException("There is no square root for negative numbers");}

        return simpleMath.sqrt(NumberConverter.convertToDouble(num1));
    }

    @RequestMapping(value = "/pow/{num1}",
            method = RequestMethod.GET)
    public Double pow(
            @PathVariable(value = "num1") String num1
    ) throws Exception{

        if(!isNumeric(num1)){ throw new UnsupportedMathOperationException("Please set a numeric value!");}

        return simpleMath.pow(NumberConverter.convertToDouble(num1));
    }

    
}